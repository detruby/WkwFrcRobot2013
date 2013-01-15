/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.util;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * wrapper class for SmartDashboard.
 */
public class WkwDashboard {

	private static final String STATUS_NAME_ROBOT_INITALIZED_AT = "Robot Initialized At";
	private static final String STATUS_NAME_ROBOT_PROGRAM_VERSION = "Robot Program Version";

	private static final String STATUS_NAME_ROBOT_STATE_DISABLED = "Robot Disabled State";
	private static final String STATUS_NAME_ROBOT_STATE_AUTONOMOUS = "Robot Autonomous State";
	private static final String STATUS_NAME_ROBOT_STATE_TELEOP = "Robot Teleop State";
	private static final String STATUS_NAME_ROBOT_STATE_TEST = "Robot Test State";

	private static final String STATUS_NAME_DRIVE_LEFT_MOTOR_SPEED = "Drive Left Motor Speed";
	private static final String STATUS_NAME_DRIVE_RIGHT_MOTOR_SPEED = "Drive Right Motor Speed";
	private static final String STATUS_NAME_DRIVE_LEFT_JOYSTICK_X = "Drive Left Joystick X";
	private static final String STATUS_NAME_DRIVE_LEFT_JOYSTICK_Y = "Drive Left Joystick Y";
	private static final String STATUS_NAME_DRIVE_RIGHT_JOYSTICK_X = "Drive Right Joystick X";
	private static final String STATUS_NAME_DRIVE_RIGHT_JOYSTICK_Y = "Drive Right Joystick Y";
	private static final String STATUS_NAME_SHOOTER_BALL_PRESENT = "Shooter Ball Present";
	private static final String STATUS_NAME_GATHERER_BALL_PRESENT = "Gatherer Ball Present";
	private static final String STATUS_NAME_SHOOTER_MOTOR_TOTAL_ON_TIME = "Shooter Motor Total On Time";
	private static final String STATUS_NAME_SHOOTER_SPEED = "Shooter Speed";
	private static final String STATUS_NAME_SHOOTER_DESIRED_SPEED = "Shooter Desired Speed";
	private static final String STATUS_NAME_SHOOTER_SONAR_DISTANCE = "Shooter Sonar Distance";
	private static final String STATUS_NAME_SHOOTER_GYRO_DEGREES_YAW = "Shooter Gyro Degrees Yaw";
	private static final String STATUS_NAME_SHOOTER_TEMPERATURE_FAHRENHEIT = "Shooter Temerature Fahrenheit";
	private static final String STATUS_NAME_SHOOTER_PLUNGER_READY = "Shooter Plunger Ready";
	private static final String STATUS_NAME_SHOOTER_PLUNGER_SWITCH = "Shooter Plunger Switch";
	private static final String STATUS_NAME_SHOOTER_TOTAL_FIRES = "Shooter Total Fires";
	private static final String STATUS_NAME_BEACH_EXTENDED = "Beach Extended";
	private static final String STATUS_NAME_BEACH_EXTENDING = "Beach Extending";
	private static final String STATUS_NAME_BEACH_RETRACTED = "Beach Retracted";
	private static final String STATUS_NAME_BEACH_RETRACTING = "Beach Retracting";
	private static final String STATUS_NAME_BEACH_TOTAL_ON_TIME = "Beach Total On Time";
	private static final String STATUS_NAME_GATHERER_ON = "Gatherer On";
	private static final String STATUS_NAME_GATHERER_SPEED = "Gatherer Speed";
	private static final String STATUS_NAME_GATHERER_TOTAL_ON_TIME = "Gatherer Total On Time";
	private static final String STATUS_NAME_CAMERA_PAN_ANGLE = "Camera Pan Angle";
	private static final String STATUS_NAME_CAMERA_TILT_ANGLE = "Camera Tilt Angle";
	private static final String STATUS_NAME_BATTERY_VOLTS = "Battery Volts";

	private static Object lock = new Object();

	private static WkwDashboard instance = null;

	/**
	 * hidden null constructor.
	 */
	private WkwDashboard() {
		super();
	}

	private static WkwDashboard getInstance() {

		synchronized (WkwDashboard.lock) {

			if (null == WkwDashboard.instance) {
				WkwDashboard.instance = new WkwDashboard();
			}
		}

		return WkwDashboard.instance;
	}

	private void setStatus(final String pName, final String pValue) {
		SmartDashboard.putString(pName, pValue);
	}

	private void setStatus(final String pName, final double pValue) {
		SmartDashboard.putNumber(pName, pValue);
	}

	private void setStatus(final String pName, final int pValue) {
		SmartDashboard.putNumber(pName, pValue);
	}

	private void setStatus(final String pName, final boolean pValue) {
		SmartDashboard.putBoolean(pName, pValue);
	}

	public static void putData(final NamedSendable pValue) {
		SmartDashboard.putData(pValue);
	}

	/*
	 * 
	 * 
	 * convenience methods.
	 * 
	 */

	public static void setRobotInitialized(final String pDateTime) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_INITALIZED_AT,
				pDateTime);
	}

	public static void setRobotVersion(final String pVersion) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_PROGRAM_VERSION,
				pVersion);
	}

	public static void setDisabledStateChange(final boolean pState) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_STATE_DISABLED, pState);
	}

	public static void setAutonomousStateChange(final boolean pState) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_STATE_AUTONOMOUS,
				pState);
	}

	public static void setTeleopStateChange(final boolean pState) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_STATE_TELEOP, pState);
	}

	public static void setTestStateChange(final boolean pState) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_ROBOT_STATE_TEST, pState);
	}

	public static void setDriveLeftMotorSpeed(final double pSpeed) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_LEFT_MOTOR_SPEED,
				pSpeed);
	}

	public static void setDriveRightMotorSpeed(final double pSpeed) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_RIGHT_MOTOR_SPEED,
				pSpeed);
	}

	public static void setDriveLeftJoystickX(final double pX) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_LEFT_JOYSTICK_X, pX);
	}

	public static void setDriveLeftJoystickY(final double pY) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_LEFT_JOYSTICK_Y, pY);
	}

	public static void setDriveRightJoystickX(final double pX) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_RIGHT_JOYSTICK_X, pX);
	}

	public static void setDriveRightJoystickY(final double pY) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_DRIVE_RIGHT_JOYSTICK_Y, pY);
	}

	public static void setShooterBallPresent(final boolean pPresent) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_BALL_PRESENT,
				pPresent);
	}

	public static void setGathererBallPresent(final boolean pPresent) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_GATHERER_BALL_PRESENT,
				pPresent);
	}

	public static void setShooterMotorTotalOnTime(final long pTime) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_MOTOR_TOTAL_ON_TIME,
				(int) pTime);
	}

	public static void setBeachExtended(final boolean pExtended) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_BEACH_EXTENDED, pExtended);
	}

	public static void setBeachExtending(final boolean pExtending) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_BEACH_EXTENDING, pExtending);
	}

	public static void setBeachRetracted(final boolean pRetracted) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_BEACH_RETRACTED, pRetracted);
	}

	public static void setBeachRetracting(final boolean pRetracting) {
		WkwDashboard.getInstance()
				.setStatus(WkwDashboard.STATUS_NAME_BEACH_RETRACTING, pRetracting);
	}

	public static void setBeachTotalOnTime(final long pTime) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_BEACH_TOTAL_ON_TIME,
				(int) pTime);
	}

	public static void setGathererOn(final boolean pOn) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_GATHERER_ON, pOn);
	}

	public static void setGathererTotalOnTime(final long pTime) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_GATHERER_TOTAL_ON_TIME,
				(int) pTime);
	}

	public static void setGathererSpeed(final double pSpeed) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_GATHERER_SPEED, pSpeed);
	}

	public static void setShooterSpeed(final double pSpeed) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_SPEED, pSpeed);
	}

	public static void setShooterPlungerSwitch(final boolean pState) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_PLUNGER_SWITCH,
				pState);
	}

	public static void setShooterDesiredSpeed(final double pSpeed) {
		WkwDashboard.getInstance()
				.setStatus(WkwDashboard.STATUS_NAME_SHOOTER_DESIRED_SPEED, pSpeed);
	}

	public static void setShooterSonarDistance(final double pInches) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_SONAR_DISTANCE,
				pInches);
	}

	public static void setShooterGyroDegreesYaw(final double pVolts) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_GYRO_DEGREES_YAW,
				pVolts);
	}

	public static void setShooterTemperatureFahrenheit(final double pVolts) {
		WkwDashboard.getInstance().setStatus(
				WkwDashboard.STATUS_NAME_SHOOTER_TEMPERATURE_FAHRENHEIT, pVolts);
	}

	public static void setShooterPlungerReady(final boolean pReady) {
		WkwDashboard.getInstance()
				.setStatus(WkwDashboard.STATUS_NAME_SHOOTER_PLUNGER_READY, pReady);
	}

	public static void setShooterTotalFires(final int pCount) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_SHOOTER_TOTAL_FIRES, pCount);
	}

	public static void setCameraPanAngle(final double pAngle) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_CAMERA_PAN_ANGLE, pAngle);
	}

	public static void setCameraTiltAngle(final double pAngle) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_CAMERA_TILT_ANGLE, pAngle);
	}

	public static void setBatteryVolts(final double pVolts) {
		WkwDashboard.getInstance().setStatus(WkwDashboard.STATUS_NAME_BATTERY_VOLTS, pVolts);
	}

}
