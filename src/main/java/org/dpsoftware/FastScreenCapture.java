/*
  FastScreenCapture.java

  Copyright (C) 2020  Davide Perini

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  You should have received a copy of the MIT License along with this program.
  If not, see <https://opensource.org/licenses/MIT/>.
*/

package org.dpsoftware;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.*;


/**
 * Fast Screen Capture for PC Ambilight
 * (https://github.com/sblantipodi/pc_ambilight)
 */
public class FastScreenCapture {

    // Number of CPU Threads to use, this app is heavy multithreaded,
    // high cpu cores equals to higher framerate but big CPU usage
    // 4 Threads are enough for 24FPS on an Intel i7 5930K@4.2GHz
    private int threadPoolNumber;
    private int executorNumber;

    // Calculate Screen Capture Framerate and how fast your microcontroller can consume it
    public static float FPS_CONSUMER;
    public static float FPS_PRODUCER;

    // Serial output stream
    private SerialPort serial;
    private OutputStream output;

    // LED strip, monitor and microcontroller config
    private Configuration config;

    // Start and Stop threads
    public static boolean RUNNING = true;

    // LED Matrix Map
    private Map<Integer, LEDCoordinate> ledMatrix;

    // Screen capture rectangle
    private Rectangle rect;

    // This queue orders elements FIFO. Producer offers some data, consumer throws data to the Serial port
    private BlockingQueue sharedQueue;


    /**
     * Constructor
     */
    public FastScreenCapture() {

        loadConfigurationYaml();
        sharedQueue = new LinkedBlockingQueue<Color[]>(100);
        ledMatrix = config.getLedMatrix();
        rect = new Rectangle(new Dimension((config.getScreenResX()*100)/config.getOsScaling(), (config.getScreenResY()*100)/config.getOsScaling()));
        initSerial();
        initOutputStream();
        initThreadPool();

    }

    /**
     * Create one fast consumer and many producers.
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        FastScreenCapture fscapture = new FastScreenCapture();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(fscapture.threadPoolNumber);

        Robot robot = null;

        // Run producers
        for (int i = 0; i < fscapture.executorNumber; i++) {
            // One AWT Robot instance every 3 threads seems to be the sweet spot for performance/memory.
            if (i%3 == 0) {
                robot = new Robot();
                System.out.println("Spawning new robot for capture");
            }
            Robot finalRobot = robot;
            // No need for completablefuture here, we wrote the queue with a producer and we forget it
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (RUNNING) {
                        fscapture.producerTask(finalRobot);
                    }
                }
            }, 0, 25, TimeUnit.MILLISECONDS);
        }

        // Run a very fast consumer
        CompletableFuture.supplyAsync(() -> {
            try {
                fscapture.consume();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
            return "Something went wrong.";
        }, scheduledExecutorService).thenAcceptAsync(s -> {
            System.out.println(s);
        }).exceptionally(e -> {
            fscapture.clean();
            scheduledExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
            return null;
        });

        // Manage tray icon and framerate dialog
        GUIManager tim = new GUIManager();
        tim.initTray();
        fscapture.getFPS(tim);

    }

    /**
     * Load config yaml and create a default config if not present
     */
    void loadConfigurationYaml() {

        StorageManager sm = new StorageManager();
        config = sm.readConfig();

    }

    /**
     * Calculate Screen Capture Framerate and how fast your microcontroller can consume it
     */
    void getFPS(GUIManager tim) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // Create a task that runs every 5 seconds
        Runnable framerateTask = () -> {
            if (FPS_PRODUCER > 0 || FPS_CONSUMER > 0) {
                float framerateProducer = FPS_PRODUCER / 5;
                float framerateConsumer = FPS_CONSUMER / 5;
                System.out.print(" --* Producing @ " + framerateProducer + " FPS *-- ");
                System.out.print(" --* Consuming @ " + framerateConsumer + " FPS *-- ");
                System.out.println(" | " + new Date() + " | ");
                tim.getFramerateLabel().setText("Producing @ " + framerateProducer + " FPS " + " |  Consuming @ " + framerateConsumer + " FPS");
                FPS_CONSUMER = FPS_PRODUCER = 0;
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(framerateTask, 0, 5, TimeUnit.SECONDS);

    }

    /**
     * Initialize Serial communication
     */
    private void initSerial() {

        CommPortIdentifier serialPortId = null;
        Enumeration enumComm = CommPortIdentifier.getPortIdentifiers();
        while (enumComm.hasMoreElements() && serialPortId == null) {
            CommPortIdentifier serialPortAvailable = (CommPortIdentifier) enumComm.nextElement();
            if (config.getSerialPort().equals(serialPortAvailable.getName()) || config.getSerialPort().equals("AUTO")) {
                serialPortId = serialPortAvailable;
            }
        }
        try {
            System.out.print("Serial Port in use: ");
            System.out.println(serialPortId.getName());
            serial = (SerialPort) serialPortId.open(this.getClass().getName(), config.getTimeout());
            serial.setSerialPortParams(config.getDataRate(), SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (PortInUseException | UnsupportedCommOperationException | NullPointerException e) {
            System.out.println("Can't open SERIAL PORT");
            JOptionPane.showMessageDialog(null, "Can't open SERIAL PORT", "Fast Screen Capture", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }

    }

    /**
     * Initialize how many Threads to use in the ThreadPool and how many Executor to use
     */
    private void initThreadPool() {

        int numberOfCPUThreads = config.getNumberOfCPUThreads();
        threadPoolNumber = numberOfCPUThreads * 2;
        if (numberOfCPUThreads > 1) {
            executorNumber = numberOfCPUThreads * 3;
        } else {
            executorNumber = numberOfCPUThreads;
        }

    }

    /**
     * Initialize OutputStream
     */
    private void initOutputStream() {

        try {
            output = serial.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Screen Capture and analysis
     *
     * @param robot an AWT Robot instance for screen capture.
     *              One instance every three threads seems to be the hot spot for performance.
     * @return array of LEDs containing the avg color to be displayed on the LED strip
     */
    private Color[] getColors(Robot robot) {

        BufferedImage screen = robot.createScreenCapture(rect);

        int osScaling = config.getOsScaling();
        int ledOffset = config.getLedOffset();
        Color[] leds = new Color[ledMatrix.size()];

        // Stream is faster than standards iterations, we need an ordered collection so no parallelStream here
        ledMatrix.entrySet().stream().forEach(entry -> {
            leds[entry.getKey()-1] = getAverageColor(screen, entry.getValue(), osScaling, ledOffset);
        });

        return leds;

    }

    /**
     * Get the average color from the screen buffer section
     *
     * @param screen a little portion of the screen
     * @param ledCoordinate led X,Y coordinates
     * @param osScaling OS scaling percentage
     * @param ledOffset Offset from the LED X,Y
     * @return the average color
     */
    private Color getAverageColor(BufferedImage screen, LEDCoordinate ledCoordinate, int osScaling, int ledOffset) {

        int r = 0, g = 0, b = 0;
        int skipPixel = 5;
        // 6 pixel for X axis and 6 pixel for Y axis
        int pixelToUse = 6;
        int pickNumber = 0;
        int width = screen.getWidth()-(skipPixel*pixelToUse);
        int height = screen.getHeight()-(skipPixel*pixelToUse);

        // We start with a negative offset
        for (int x = 0; x < pixelToUse; x++) {
            for (int y = 0; y < pixelToUse; y++) {
                int offsetX = (((ledCoordinate.getX() * 100) / osScaling) + ledOffset + (skipPixel*x));
                int offsetY = (((ledCoordinate.getY() * 100) / osScaling) + ledOffset + (skipPixel*y));
                int rgb = screen.getRGB((offsetX < width) ? offsetX : width, (offsetY < height) ? offsetY : height);
                Color color = new Color(rgb);
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
                pickNumber++;
            }
        }
        r = (r / pickNumber);
        g = (g / pickNumber);
        b = (b / pickNumber);

        return new Color(r, g, b);

    }

    /**
     * Write Serial Stream to the Serial Output
     *
     * @param leds array of LEDs containing the average color to display on the LED
     */
    private void sendColors(Color[] leds) throws IOException {

        int ledNum = ledMatrix.size();
        output.write(0xff);
        for (int i = 0; i < ledNum; i++) {
            output.write(leds[i].getRed()); //output.write(0);
            output.write(leds[i].getGreen()); //output.write(0);
            output.write(leds[i].getBlue()); //output.write(255);
        }
        FPS_CONSUMER++;

    }

    /**
     * Write Serial Stream to the Serial Output
     *
     * @param robot an AWT Robot instance for screen capture.
     *              One instance every three threads seems to be the hot spot for performance.
     */
    private void producerTask(Robot robot) {

        FPS_PRODUCER++;
        sharedQueue.offer(getColors(robot));

    }

    /**
     * Print the average FPS number we are able to capture
     */
    int consume() throws InterruptedException, IOException {

        while (true) {
            Color[] num = (Color[]) sharedQueue.take();
            if (RUNNING) {
                if (num.length == ledMatrix.size()) {
                    sendColors(num);
                    TimeUnit.MILLISECONDS.sleep(5);
                }
            }
        }

    }

    /**
     * Clean and Close Serial Output Stream
     */
    private void clean() {

        if(output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(serial != null) {
            serial.close();
        }

    }

}

