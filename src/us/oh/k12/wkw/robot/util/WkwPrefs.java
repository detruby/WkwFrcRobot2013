/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.util;

import edu.wpi.first.wpilibj.Preferences;

/**
 * wrapper class for preferences/configuration settings.
 */
public class WkwPrefs {

	// NOTE:
	// if you change the default values,
	// or add a new preference,
	// increase the following int by one.
	// this causes all of the preferences to be reset to thier default values.
	// Note: all of these preferences are changable from the driver station
	// smart dashboard.

	private static final int PREF_NAME_VERSION_VALUE = 1;

	private static final String PREF_NAME_VERSION = "z_WkwPrefVersion";

	/*
	 * 
	 * 
	 * operator ports.
	 * 
	 */

	public static final String PREF_NAME_GAMEPAD_PORT = "Operator-GamepadPort";
	public static final int PREF_NAME_GAMEPAD_PORT_DEFAULT = 1;

	public static final String PREF_NAME_JOYSTICK_RIGHT_PORT = "Operator-JoystickRightPort";
	public static final int PREF_NAME_JOYSTICK_RIGHT_PORT_DEFAULT = 2;

	public static final String PREF_NAME_JOYSTICK_LEFT_PORT = "Operator-JoystickLeftPort";
	public static final int PREF_NAME_JOYSTICK_LEFT_PORT_DEFAULT = 3;

	/*
	 * 
	 * 
	 * jaguars on pwm ports.
	 * 
	 */

	public static final String PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL = "Pwm-Channel-DriveLeftMotorControllerChannel";
	public static final int PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL_DEFAULT = 1;

	public static final String PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL = "Pwm-Channel-DriveRightMotorControllerChannel";
	public static final int PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL_DEFAULT = 2;

	public static final String PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL = "Pwm-Channel-ShooterMotorControllerChannel";
	public static final int PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL_DEFAULT = 4;

	public static final String PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL = "Pwm-Channel-GathererMotorController";
	public static final int PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL_DEFAULT = 3;

	/*
	 * 
	 * 
	 * camera pan and tilt servos on pwm ports.
	 * 
	 */

	public static final String PREF_NAME_CAMERA_PAN_SERVO_CHANNEL = "Pwm-Channel-CameraPanServo";
	// PWM port - must have 2 pin jumper
	public static final int PREF_NAME_CAMERA_PAN_SERVO_CHANNEL_DEFAULT = 9;

	public static final String PREF_NAME_CAMERA_TILT_SERVO_CHANNEL = "Pwm-Channel-CameraTiltServo";
	// PWM port - must have 2 pin jumper
	public static final int PREF_NAME_CAMERA_TILT_SERVO_CHANNEL_DEFAULT = 10;

	/*
	 * 
	 * 
	 * relay ports.
	 * 
	 */

	public static final String PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL = "Relay-Channel-PlungerController";
	// relay port 1
	public static final int PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL_DEFAULT = 5;

	public static final String PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL = "Relay-Channel-BeachMotorControllerChannel";
	// relay port 4
	public static final int PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL_DEFAULT = 4;

	/*
	 * 
	 * 
	 * digital io ports.
	 * 
	 */

	public static final String PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL = "DigitalIo-Channel-BeachRetractedInput";
	public static final int PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL_DEFAULT = 3;

	public static final String PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL = "DigitalIo-Channel-BeachExtendedInput";
	public static final int PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL_DEFAULT = 7;

	public static final String PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL = "DigitalIo-Channel-PlungerSwitchSensor";
	public static final int PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL_DEFAULT = 9;

	public static final String PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL = "DigitalIo-Channel-GathererBallPresentSensor";
	public static final int PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT = 11;

	public static final String PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL = "DigitalIo-Channel-ShooterBallPresentSensor";
	public static final int PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT = 13;

	/*
	 * 
	 * 
	 * analog break out input ports. Note: channel 8 is reserved for the battery voltage sensor.
	 * 
	 */

	public static final String PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL = "Analog-Channel-ShooterGyroSensor";
	public static final int PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL_DEFAULT = 1;

	public static final String PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL = "Analog-Channel-ShooterTemperatureSensor";
	public static final int PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL_DEFAULT = 2;

	public static final String PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL = "Analog-Channel-ShooterSonarSensor";
	public static final int PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL_DEFAULT = 3;

	/*
	 * 
	 * misc
	 * 
	 */

	public static final String PREF_NAME_BEACH_COMMAND_TIMEOUT = "Timeout-BeachCommand";
	public static final double PREF_NAME_BEACH_COMMAND_TIMEOUT_DEFAULT = 10.0;

	public static final String PREF_NAME_GATHERER_MOTOR_SPEED = "Speed-GathererMotor";
	public static final double PREF_NAME_GATHERER_MOTOR_SPEED_DEFAULT = .8;

	public static final String PREF_NAME_DRIVE_WATCHDOG_TIMEOUT = "Timeout-DriveWatchdog";
	public static final double PREF_NAME_DRIVE_WATCHDOG_TIMEOUT_DEFAULT = 2.0;

	public static final String PREF_NAME_ROBOT_WATCHDOG_ENABLED = "Status-RobotWatchdogEnabled";
	public static final boolean PREF_NAME_ROBOT_WATCHDOG_ENABLED_DEFAULT = false; // TODO: change

	private static Object lock = new Object();

	private static WkwPrefs instance = null;

	/**
	 * hidden null constructor (singleton).
	 */
	private WkwPrefs() {
		super();

		final Preferences aPreferences = Preferences.getInstance();

		int aPrefVersion = aPreferences.getInt(WkwPrefs.PREF_NAME_VERSION, 0);

		// is this preference version in the preference file?
		if (aPrefVersion != WkwPrefs.PREF_NAME_VERSION_VALUE) {

			// no, initialize all preference default values

			aPreferences.putInt(WkwPrefs.PREF_NAME_GAMEPAD_PORT,
					WkwPrefs.PREF_NAME_GAMEPAD_PORT_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_JOYSTICK_RIGHT_PORT,
					WkwPrefs.PREF_NAME_JOYSTICK_RIGHT_PORT_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_JOYSTICK_LEFT_PORT,
					WkwPrefs.PREF_NAME_JOYSTICK_LEFT_PORT_DEFAULT);

			aPreferences.putInt(WkwPrefs.PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL_DEFAULT);

			aPreferences.putInt(WkwPrefs.PREF_NAME_CAMERA_PAN_SERVO_CHANNEL,
					WkwPrefs.PREF_NAME_CAMERA_PAN_SERVO_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_CAMERA_TILT_SERVO_CHANNEL,
					WkwPrefs.PREF_NAME_CAMERA_TILT_SERVO_CHANNEL_DEFAULT);

			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL,
					WkwPrefs.PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL_DEFAULT);

			aPreferences.putInt(WkwPrefs.PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL,
					WkwPrefs.PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL,
					WkwPrefs.PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT);

			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL_DEFAULT);
			aPreferences.putInt(WkwPrefs.PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL,
					WkwPrefs.PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL_DEFAULT);

			aPreferences.putDouble(WkwPrefs.PREF_NAME_BEACH_COMMAND_TIMEOUT,
					WkwPrefs.PREF_NAME_BEACH_COMMAND_TIMEOUT_DEFAULT);
			aPreferences.putDouble(WkwPrefs.PREF_NAME_GATHERER_MOTOR_SPEED,
					WkwPrefs.PREF_NAME_GATHERER_MOTOR_SPEED_DEFAULT);
			aPreferences.putDouble(WkwPrefs.PREF_NAME_DRIVE_WATCHDOG_TIMEOUT,
					WkwPrefs.PREF_NAME_DRIVE_WATCHDOG_TIMEOUT_DEFAULT);
			aPreferences.putBoolean(WkwPrefs.PREF_NAME_ROBOT_WATCHDOG_ENABLED,
					WkwPrefs.PREF_NAME_ROBOT_WATCHDOG_ENABLED_DEFAULT);

			// set the new version
			aPreferences.putInt(WkwPrefs.PREF_NAME_VERSION, WkwPrefs.PREF_NAME_VERSION_VALUE);
			// save in the file
			aPreferences.save();
		}

	}

	public static WkwPrefs getInstance() {

		synchronized (WkwPrefs.lock) {

			if (null == WkwPrefs.instance) {
				WkwPrefs.instance = new WkwPrefs();
			}
		}

		return WkwPrefs.instance;
	}

	public boolean getPreference(final String pName, final boolean pDefault) {
		return Preferences.getInstance().getBoolean(pName, pDefault);
	}

	public double getPreference(final String pName, final double pDefault) {
		return Preferences.getInstance().getDouble(pName, pDefault);
	}

	public int getPreference(final String pName, final int pDefault) {
		return Preferences.getInstance().getInt(pName, pDefault);
	}

	public String getPreference(final String pName, final String pDefault) {
		return Preferences.getInstance().getString(pName, pDefault);
	}

	public void putString(final String pName, final String pValue) {
		final Preferences aPreferences = Preferences.getInstance();
		aPreferences.putString(pName, pValue);
		aPreferences.save();
	}

	/*
	 * 
	 * 
	 * convenience methods.
	 */

	public static boolean isRobotWatchdogEnabled() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_ROBOT_WATCHDOG_ENABLED,
				WkwPrefs.PREF_NAME_ROBOT_WATCHDOG_ENABLED_DEFAULT);
	}

	public static int getJoystickLeftPort() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_JOYSTICK_LEFT_PORT,
				WkwPrefs.PREF_NAME_JOYSTICK_LEFT_PORT_DEFAULT);
	}

	public static int getJoystickRightPort() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_JOYSTICK_RIGHT_PORT,
				WkwPrefs.PREF_NAME_JOYSTICK_RIGHT_PORT_DEFAULT);
	}

	public static int getGamepadPort() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_GAMEPAD_PORT,
				WkwPrefs.PREF_NAME_GAMEPAD_PORT_DEFAULT);
	}

	public static int getDriveLeftMotorControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_DRIVE_LEFT_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static int getDriveRightMotorControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_DRIVE_RIGHT_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static double getDriveWatchdogTimeout() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_DRIVE_WATCHDOG_TIMEOUT,
				WkwPrefs.PREF_NAME_DRIVE_WATCHDOG_TIMEOUT_DEFAULT);
	}

	public static int getShooterBallPresentSensorChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT);
	}

	public static int getGathererBallPresentSensorChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_GATHERER_BALL_PRESENT_SENSOR_CHANNEL_DEFAULT);
	}

	public static double getGathererMotorSpeed() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_GATHERER_MOTOR_SPEED,
				WkwPrefs.PREF_NAME_GATHERER_MOTOR_SPEED_DEFAULT);
	}

	public static int getBeachMotorControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_BEACH_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static int getBeachExtendedInputChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL,
				WkwPrefs.PREF_NAME_BEACH_EXTENDED_INPUT_CHANNEL_DEFAULT);
	}

	public static int getBeachRetractedInputChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL,
				WkwPrefs.PREF_NAME_BEACH_RETRACTED_INPUT_CHANNEL_DEFAULT);
	}

	public static double getBeachCommandTimeout() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_BEACH_COMMAND_TIMEOUT,
				WkwPrefs.PREF_NAME_BEACH_COMMAND_TIMEOUT_DEFAULT);
	}

	public static int getGathererMotorControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_GATHERER_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static int getShooterMotorControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_MOTOR_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static int getShooterSonarSensorChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_SONAR_SENSOR_CHANNEL_DEFAULT);
	}

	public static int getShooterGyroSensorChannel() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_GYRO_SENSOR_CHANNEL_DEFAULT);
	}

	public static int getShooterTemperatureSensorChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_TEMPERATURE_SENSOR_CHANNEL_DEFAULT);
	}

	public static int getShooterPlungerControllerChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL,
				WkwPrefs.PREF_NAME_SHOOTER_PLUNGER_CONTROLLER_CHANNEL_DEFAULT);
	}

	public static int getShooterPlungerSwitchChannel() {
		return WkwPrefs.getInstance().getPreference(
				WkwPrefs.PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL,
				WkwPrefs.PREF_NAME_PLUNGER_SWITCH_SENSOR_CHANNEL_DEFAULT);
	}

	public static int getCameraPanServoChannel() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_CAMERA_PAN_SERVO_CHANNEL,
				WkwPrefs.PREF_NAME_CAMERA_PAN_SERVO_CHANNEL_DEFAULT);
	}

	public static int getCameraTiltServoChannel() {
		return WkwPrefs.getInstance().getPreference(WkwPrefs.PREF_NAME_CAMERA_TILT_SERVO_CHANNEL,
				WkwPrefs.PREF_NAME_CAMERA_TILT_SERVO_CHANNEL_DEFAULT);
	}

}
