/*
  Constants.java

  Firefly Luciferin, very fast Java Screen Capture software designed
  for Glow Worm Luciferin firmware.

  Copyright (C) 2020  Davide Perini

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package org.dpsoftware.config;

public class Constants {

	public static final String FIREFLY_LUCIFERIN_VERSION = "1.4.2";

	// Misc
	public static final String FIREFLY_LUCIFERIN = "Firefly Luciferin";
	public static final String FULLSCREEN = "FullScreen";
	public static final String LETTERBOX = "Letterbox";
	public static final String SPAWNING_ROBOTS = "Spawning new robot for capture";
	public static final String SERIAL_PORT_IN_USE = "Serial Port in use: ";
	public static final String TURN_LED_ON = "Turn LEDs ON";
	public static final String TURN_LED_OFF = "Turn LEDs OFF";
	public static final String RED_COLOR = "RED_COLOR";
	public static final String GREEN_COLOR = "GREEN_COLOR";
	public static final String BLU_COLOR = "BLU_COLOR";
	public static final String BRIGHTNESS = "BRIGHTNESS";
	public static final String DEFAULT_COLOR_CHOOSER = "255,255,255,255";

	// Upgrade
	public static final String MINIMUM_FIRMWARE_FOR_AUTO_UPGRADE = "4.0.3";
	public static final String GITHUB_POM_URL = "https://raw.githubusercontent.com/sblantipodi/firefly_luciferin/master/pom.xml";
	public static final String GITHUB_GLOW_WORM_URL = "https://raw.githubusercontent.com/sblantipodi/glow_worm_luciferin/master/version";
	public static final String POM_PRJ_VERSION = "<project.version>";
	public static final String POM_PRJ_VERSION_CLOSE = "</project.version>";
	public static final String DOWNLOADING = "Downloading";
	public static final String SETUP_FILENAME_WINDOWS = "FireflyLuciferinSetup.exe";
	public static final String SETUP_FILENAME_LINUX_DEB = "FireflyLuciferinLinux.deb";
	public static final String SETUP_FILENAME_LINUX_RPM = "FireflyLuciferinLinux.rpm";
	public static final String GITHUB_RELEASES = "https://github.com/sblantipodi/firefly_luciferin/releases/download/v";
	public static final String GITHUB_RELEASES_FIRMWARE = "https://github.com/sblantipodi/glow_worm_luciferin/releases/download/v";
	public static final String HOME_PATH = "user.home";
	public static final String DOCUMENTS_FOLDER = "Documents";
	public static final String LUCIFERIN_PLACEHOLDER = "FireflyLuciferin";
	public static final String LUCIFERIN_FOLDER = "FireflyLuciferin";
	public static final String EXPECTED_SIZE = "Expected size: ";
	public static final String DOWNLOAD_PROGRESS_BAR = "Downloading : ";
	public static final String DOWNLOAD_COMPLETE = " download completed";
	public static final String UPGRADE_FILE = "file";
	public static final String UPGRADE_CONTENT_TYPE = "Content-Type";
	public static final String UPGRADE_MULTIPART = "multipart/form-data;boundary=";
	public static final String UPGRADE_URL = "http://-/update";

	// Native executor
	public static final String CANT_RUN_CMD = "Couldn't run command {} : {}";
	public static final String NO_OUTPUT = "Problem reading output from {}: {}";
	public static final String INTERRUPTED_WHEN_READING = "Interrupted while reading output from {}: {}";
	public static final String DPKG_CHECK_CMD = "dpkg --version";

	// Resources
	public static final String IMAGE_TRAY_PLAY = "/org/dpsoftware/gui/img/tray_play.png";
	public static final String IMAGE_TRAY_STOP = "/org/dpsoftware/gui/img/tray_stop.png";
	public static final String IMAGE_TRAY_GREY = "/org/dpsoftware/gui/img/tray_stop_grey.png";
	public static final String IMAGE_CONTROL_GREY = "/org/dpsoftware/gui/img/java_fast_screen_capture_logo_grey.png";
	public static final String IMAGE_CONTROL_PLAY = "/org/dpsoftware/gui/img/java_fast_screen_capture_logo_play.png";
	public static final String IMAGE_CONTROL_LOGO = "/org/dpsoftware/gui/img/java_fast_screen_capture_logo.png";
	public static final String FXML = ".fxml";
	public static final String FXML_SETTINGS = "settings";
	public static final String FXML_SETTINGS_LINUX = "linuxSettings";
	public static final String FXML_INFO = "info";
	public static final String CONFIG_FILENAME = "FireflyLuciferin.yaml";
	public static final String ALREADY_EXIST = "already exists";
	public static final String WAS_CREATED = "was created";
	public static final String WAS_NOT_CREATED = "was not created";
	public static final String CLEANING_OLD_CONFIG = "Cleaning old config";
	public static final String FAILED_TO_CLEAN_CONFIG = "Failed to clean old config";
	public static final String CONFIG_OK = "Configuration OK.";
	public static final String ERROR_READING_CONFIG = "Error reading config file, writing a default one.";

	// MQTT
	public static final String STATE_OFF_SOLID = "{\"state\": \"OFF\", \"effect\": \"solid\"}";
	public static final String STATE_ON_SOLID_COLOR = "{\"state\": \"ON\", \"effect\": \"solid\", \"color\": {\"r\": RED_COLOR, \"g\": GREEN_COLOR, \"b\": BLU_COLOR}, \"brightness\": BRIGHTNESS}";
	public static final String STATE_ON_GLOWWORM = "{\"state\": \"ON\", \"effect\": \"GlowWormWifi\"}";
	public static final String STATE_ON_GLOWWORMWIFI = "{\"state\": \"ON\", \"effect\": \"GlowWorm\"}";
    public static final String START_WEB_SERVER_MSG = "{\"update\":true}";
	public static final String DEFAULT_MQTT_HOST = "tcp://192.168.1.3";
	public static final String DEFAULT_MQTT_PORT = "1883";
	public static final String DEFAULT_MQTT_TOPIC = "lights/glowwormluciferin/set";
	public static final String DEFAULT_MQTT_STATE_TOPIC = "lights/glowwormluciferin";
	public static final String UPDATE_MQTT_TOPIC = "lights/glowwormluciferin/update";
	public static final String UPDATE_RESULT_MQTT_TOPIC = "lights/glowwormluciferin/update/result";
	public static final String WHOAMI = "Whoami";
	public static final String STATE_IP = "IP";
	public static final String DEVICE_VER = "ver";
	public static final String DEVICE_BOARD = "board";
	public static final String STATE = "state";
	public static final String ON = "ON";
	public static final String OFF = "OFF";
	public static final String EFFECT = "effect";
	public static final String SOLID = "solid";
	public static final String COLOR = "color";
	public static final String MQTT_BRIGHTNESS = "brightness";
	public static final String MQTT_DISABLED = "MQTT disabled.";
	public static final String MQTT_DEVICE_NAME_WIN = "FireflyLuciferin";
	public static final String MQTT_DEVICE_NAME_LIN = "FireflyLuciferinLinux";
	public static final String MQTT_CONNECTED = "Connected to MQTT Server";
	public static final String MQTT_CANT_SEND = "Cant't send MQTT msg";
	public static final String MQTT_STREAM_TOPIC = "/stream";
	public static final String MQTT_RECONNECTED = "Reconnected";
	public static final String MQTT_DISCONNECTED = "Disconnected";
	public static final String MQTT_START = "START";
	public static final String MQTT_STOP = "STOP";

	// GUI
	public static final String SAVE = "Save";
	public static final String SAVE_AND_CLOSE = "Save and close";
	public static final String SERIAL_ERROR_TITLE = "Serial Port Error";
	public static final String SERIAL_ERROR_HEADER = "No serial port available";
	public static final String SERIAL_ERROR_OPEN_HEADER = "Can't open SERIAL PORT";
	public static final String SERIAL_ERROR_CONTEXT = "Serial port is in use or there is no microcontroller available. Please connect a microcontroller or go to settings and choose MQTT Stream. Luciferin restart is required.";
	public static final String MQTT_ERROR_TITLE = "MQTT Connection Error";
	public static final String MQTT_ERROR_HEADER = "Unable to connect to the MQTT server";
	public static final String MQTT_ERROR_CONTEXT = "Luciferin is unable to connect to the MQTT server, please correct your settings and retry.";
	public static final String START = "Start";
	public static final String STOP = "Stop";
	public static final String INFO = "Info";
	public static final String SETTINGS = "Settings";
	public static final String EXIT = "Exit";
	public static final String CLICK_OK_DOWNLOAD = "Click Ok to download and install the new version.";
	public static final String CLICK_OK_DOWNLOAD_LIGHT = "Click Ok to download and install the new version. Glow Worm Luciferin LIGHT firmware must be updated manually, please check that you are running the latest firmware version.";
	public static final String CLICK_OK_DOWNLOAD_LINUX = "Click Ok to download new version in your ~/Documents/FireflyLuciferin folder. ";
	public static final String ONCE_DOWNLOAD_FINISHED = "Once the download is finished, please go to that folder and install it manually.";
	public static final String ONCE_DOWNLOAD_FINISHED_LIGHT = "Once the download is finished, please go to that folder and install it manually. Glow Worm Luciferin LIGHT firmware must be updated manually, please check that you are running the latest firmware version.";
	public static final String NEW_VERSION_AVAILABLE = "New version available!";
	public static final String UPGRADE_SUCCESS = "Upgrade success";
	public static final String DEVICEUPGRADE_SUCCESS = " has been successfully upgraded";
	public static final String NEW_FIRMWARE_AVAILABLE = "New firmware available!";
	public static final String CANT_UPGRADE_TOO_OLD = "Can't upgrade Glow Worm Luciferin device";
	public static final String MANUAL_UPGRADE = "Your device is running an old firmware that doesn't support automatic updates, please update it manually.";
	public static final String DEVICES_UPDATED = "These devices will be updated:\n";
	public static final String DEVICE_UPDATED = "This device will be updated:\n";
	public static final String UPDATE_BACKGROUND = "update process runs in background, you'll be notified when it's finished.";
	public static final String STOPPING_THREADS = "Stopping Threads...";
	public static final String CAPTURE_MODE_CHANGED = "Capture mode changed to ";
	public static final String GITHUB_URL = "https://github.com/sblantipodi/firefly_luciferin";
	public static final String SERIAL_PORT_AUTO = "AUTO";
	public static final String SERIAL_PORT_COM = "COM";
	public static final String SERIAL_PORT_TTY = "/dev/ttyUSB";
	public static final String CLOCKWISE = "Clockwise";
	public static final String ANTICLOCKWISE = "Anticlockwise";
	public static final String VERSION = "VERSION";
	public static final String BY_DAVIDE = "by Davide Perini (VERSION)";
	public static final String PRODUCING = "Producing @ ";
	public static final String CONSUMING = "Consuming @ ";
	public static final String FPS = "FPS";
	public static final String PERCENT = "%";
	public static final String GAMMA_DEFAULT = "2.2";
	public static final String USB_DEVICE = "USB device";
	public static final String ESP8266 = "ESP8266";
	public static final String ESP32 = "ESP32";
	public static final String DASH = "-";
	public static final String UPDATE_FILENAME = "GlowWormLuciferinFULL_board_firmware.bin";

	// Tooltips
	public static final String TOOLTIP_TOPLED = "# of LEDs in the top row";
	public static final String TOOLTIP_LEFTLED = "# of LEDs in the left column";
	public static final String TOOLTIP_RIGHTLED = "# of LEDs in the right column";
	public static final String TOOLTIP_BOTTOMLEFTLED = "# of LEDs in bottom left row";
    public static final String TOOLTIP_BOTTOMRIGHTLED = "# of LEDs in the bottom right row";
    public static final String TOOLTIP_ORIENTATION = "Orientation of your LED strip";
    public static final String TOOLTIP_SCREENWIDTH = "Monitor resolution";
    public static final String TOOLTIP_SCREENHEIGHT = "Monitor resolution";
    public static final String TOOLTIP_SCALING = "OS scaling feature, you should not change this setting";
    public static final String TOOLTIP_GAMMA = "Smaller values results in brighter LEDs but less accurate colors. 2.2 is generally good for SDR contents, 6.0 is generally good for HDR contents.";
    public static final String TOOLTIP_CAPTUREMETHOD = "If you have a GPU, Desktop Duplication API (DDUPL) is faster than other methods";
    public static final String TOOLTIP_LINUXCAPTUREMETHOD = "If you have a GPU, Desktop Duplication API (DDUPL) is faster than other methods";
    public static final String TOOLTIP_NUMBEROFTHREADS = "1 thread is enough when using DDUPL, 3 or more threads are recommended for other capture methods";
    public static final String TOOLTIP_SERIALPORT = "AUTO detects first serial port available, change it if you have more than one serial port available";
    public static final String TOOLTIP_ASPECTRATIO = "LetterBox is recommended for films, you can change this option later";
	public static final String TOOLTIP_FRAMERATE = "30 FPS IS THE RECOMMENDED FRAMERATE, use at your own risk.";
	public static final String TOOLTIP_MQTTHOST = "OPTIONAL: MQTT protocol://host";
    public static final String TOOLTIP_MQTTPORT = "OPTIONAL: MQTT port";
    public static final String TOOLTIP_MQTTTOPIC = "OPTIONAL: MQTT topic, used to start/stop capturing. Don't change it if you want to use Glow Worm Luciferin Firmware.";
    public static final String TOOLTIP_MQTTUSER = "OPTIONAL: MQTT username";
    public static final String TOOLTIP_MQTTPWD = "OPTIONAL: MQTT password";
	public static final String TOOLTIP_MQTTENABLE = "FULL firmware requires MQTT";
	public static final String TOOLTIP_EYE_CARE = "If enabled LEDs will never turn off in black scenes, a soft and gentle light is used instead.";
	public static final String TOOLTIP_AUTOSTART = "Start capture on Firefly Luciferin startup";
	public static final String TOOLTIP_MQTTSTREAM = "Prefer wireless stream over serial port (USB cable). This option is ignored if MQTT is disabled. Enable this option if you don't have the possibility to use a USB cable.";
	public static final String TOOLTIP_CHECK_UPDATES = "Set and forget it to update Firefly Luciferin and Glow Worm Luciferin when updates are available. Automatic firmware upgrade is available on FULL version only";
	public static final String TOOLTIP_PLAYBUTTON_NULL = "Please configure and save before capturing";
	public static final String TOOLTIP_BRIGHTNESS = "Set the brightness of the LED strip";
    public static final String TOOLTIP_SAVELEDBUTTON_NULL = "You can change this options later";
    public static final String TOOLTIP_SAVEMQTTBUTTON_NULL = "You can change this options later";
	public static final String TOOLTIP_SAVESETTINGSBUTTON_NULL = "You can change this options later";
	public static final String TOOLTIP_SAVEDEVICEBUTTON_NULL = "You can change this options later";
	public static final String TOOLTIP_PLAYBUTTON = "START/STOP capturing";
	public static final String TOOLTIP_SAVELEDBUTTON = "Changes will take effect the next time you launch the app";
	public static final String TOOLTIP_SAVEMQTTBUTTON = "Changes will take effect the next time you launch the app";
	public static final String TOOLTIP_SAVESETTINGSBUTTON = "Changes will take effect the next time you launch the app";
	public static final String TOOLTIP_SAVEDEVICEBUTTON = "Changes will take effect the next time you launch the app";
	public static final String TOOLTIP_SHOWTESTIMAGEBUTTON = "Show a test image, useful to check for LED alignment behind the monitor";

	// Grabber
	public static final String EMIT_SIGNALS = "emit-signals";
	public static final String GSTREAMER_PIPELINE = "video/x-raw,pixel-aspect-ratio=1/1,use-damage=0,sync=false,";
	public static final String BYTE_ORDER_BGR = "format=BGRx";
	public static final String BYTE_ORDER_RGB = "format=xRGB";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String GSTREAMER_PATH = "/gstreamer/1.0/mingw_x86_64/bin";
	public static final String PATH = "path";
	public static final String JNA_LIB_PATH = "jna.library.path";
	public static final String SCREEN_GRABBER = "ScreenGrabber";
	public static final String GSTREAMER_PIPELINE_WINDOWS = "dxgiscreencapsrc ! videoscale ! videoconvert";
	public static final String GSTREAMER_PIPELINE_LINUX = "ximagesrc ! videoscale ! videoconvert";
	public static final String FRAMERATE_PLACEHOLDER = "framerate=FRAMERATE_PLACEHOLDER/1,";
	public static final String UNLOCKED = "UNLOCKED";

	// Exceptions
	public static final String WIN32_EXCEPTION = "Win32 Exception.";
	public static final String SELECT_OBJ_EXCEPTION = "SelectObject Exception.";
	public static final String DELETE_OBJ_EXCEPTION = "DeleteObject Exception.";
	public static final String DELETE_DC_EXCEPTION = "Delete DC Exception.";
	public static final String DEVICE_CONTEXT_RELEASE_EXCEPTION = "GlowWormDevice context did not release properly.";
	public static final String WINDOWS_EXCEPTION = "Window width and/or height were 0 even though GetWindowRect did not appear to fail.";
	public static final String CANT_FIND_GSTREAMER = "Cant' find GStreamer";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong.";

	// Image processor
	public static final String FAT_JAR_NAME = "FireflyLuciferin-jar-with-dependencies.jar";
	public static final String CLASSES = "classes";
	public static final String TARGET = "target";
	public static final String MAIN_RES = "src/main/resources";
	public static final String GSTREAMER_PATH_IN_USE = "GStreamer path in use=";

}