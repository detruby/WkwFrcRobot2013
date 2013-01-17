/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

import us.oh.k12.wkw.robot.command.DriveWithJoystickCmd;
import us.oh.k12.wkw.robot.command.DriveWithJoysticksCmd;
import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Represents the robot drive (wheels and related motors) system.
 */
public class DriveSystem extends SystemBase {

	public static final String PREF_NAME_ARCADE_DRIVE_ENABLED = "DriveModeArcade";
	private static final boolean ARCADE_DRIVE_ENABLED = false;
	private static final String LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM = "DriveSystem";
	private static final String LIVEWINDOW_NAME_LEFTDRIVE = "leftDrive";
	private static final String LIVEWINDOW_NAME_RIGHTDRIVE = "rightDrive";

	// the left drive jaguar and motor.
	private Jaguar leftDrive;
	// the right drive jaguar and motor.
	private Jaguar rightDrive;
	// the drive.
	private RobotDrive drive;

	private Joystick leftJoystick;

	private Joystick rightJoystick;

	private double leftDriveSpeed = 0;
	private double rightDriveSpeed = 0;
	private double leftJoystickX = 0;
	private double leftJoystickY = 0;
	private double rightJoystickX = 0;
	private double rightJoystickY = 0;

	/**
	 * The default null constructor.
	 */
	public DriveSystem() {

		super("DriveSystem");

		try {

			this.debug("DriveSystem()", "Started.");

			this.testIo();

			this.initDrive();

			this.debug("DriveSystem()", "Ended.");

		} catch (AllocationException anAloEx) {
			this.error("DriveSystem()", anAloEx);

		} catch (Exception anEx) {
			this.error("DriveSystem()", anEx);
		}
	}

	private void testIo() {
		// test digital side car hardware.
		// this.findChannels();
		// this.testAllGpios();
		// this.testAllRelays();
	}

	private void initDrive() {

		// setup the left drive.

		this.leftDrive = new Jaguar(WkwPrefs.getDriveLeftMotorControllerChannel());

		this.debug("initDrive()", "leftDrive=" + WkwPrefs.getDriveLeftMotorControllerChannel()
				+ ".");

		LiveWindow.addActuator(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
				DriveSystem.LIVEWINDOW_NAME_LEFTDRIVE, this.leftDrive);

		// setup the right drive.

		this.rightDrive = new Jaguar(WkwPrefs.getDriveRightMotorControllerChannel());

		this.debug("initDrive()", "rightDrive=" + WkwPrefs.getDriveRightMotorControllerChannel()
				+ ".");

		LiveWindow.addActuator(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
				DriveSystem.LIVEWINDOW_NAME_RIGHTDRIVE, this.rightDrive);

		if ((null == this.leftDrive) || (null == this.rightDrive)) {

			this.error("initDrive()", "leftDrive or rightDrive is null.", null);

		} else {

			this.leftDrive.setExpiration(WkwPrefs.getDriveWatchdogTimeout());
			this.leftDrive.setSafetyEnabled(WkwPrefs.isRobotWatchdogEnabled());

			this.rightDrive.setExpiration(WkwPrefs.getDriveWatchdogTimeout());
			this.rightDrive.setSafetyEnabled(WkwPrefs.isRobotWatchdogEnabled());

			// setup the robot drive

			this.drive = new RobotDrive(this.leftDrive, this.rightDrive);

			if (null == this.drive) {

				this.error("initDrive()", "drive is null.", null);

			} else {

				// initialize the drive.

				this.drive.setExpiration(WkwPrefs.getDriveWatchdogTimeout());
				this.drive.setSafetyEnabled(WkwPrefs.isRobotWatchdogEnabled());

			}

		}

	}

	protected void initDefaultCommand() {

		try {

			if (DriveSystem.ARCADE_DRIVE_ENABLED) {

				this.debug("initDefaultCommand()", "DriveWithJoystickCmd.");
				this.setDefaultCommand(new DriveWithJoystickCmd());

			} else {

				this.debug("initDefaultCommand()", "DriveWithJoysticksCmd.");
				this.setDefaultCommand(new DriveWithJoysticksCmd());

			}

		} catch (Exception anEx) {
			this.error("initDefaultCommand()", anEx);
		}
	}

	public void updateStatus() {

		try {

			if (null != this.leftDrive) {
				if (this.leftDriveSpeed != this.leftDrive.getSpeed()) {
					this.leftDriveSpeed = this.leftDrive.getSpeed();
					WkwDashboard.setDriveLeftMotorSpeed(this.leftDriveSpeed);
				}
			}

			if (null != this.leftJoystick) {
				if (this.leftJoystickX != this.leftJoystick.getX()) {
					this.leftJoystickX = this.leftJoystick.getX();
					WkwDashboard.setDriveLeftJoystickX(this.leftJoystickX);
				}
				if (this.leftJoystickY != this.leftJoystick.getY()) {
					this.leftJoystickY = this.leftJoystick.getY();
					WkwDashboard.setDriveLeftJoystickY(this.leftJoystickY);
				}
			}

			if (null != this.rightDrive) {
				if (this.rightDriveSpeed != this.rightDrive.getSpeed()) {
					this.rightDriveSpeed = this.rightDrive.getSpeed();
					WkwDashboard.setDriveRightMotorSpeed(this.rightDriveSpeed);
				}
			}

			if (null != this.rightJoystick) {
				if (this.rightJoystickX != this.rightJoystick.getX()) {
					this.rightJoystickX = this.rightJoystick.getX();
					WkwDashboard.setDriveRightJoystickX(this.rightJoystickX);
				}
				if (this.rightJoystickY != this.rightJoystick.getY()) {
					this.rightJoystickY = this.rightJoystick.getY();
					WkwDashboard.setDriveRightJoystickY(this.rightJoystickY);
				}
			}

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}

	}

	public void driveWithJoystick(final Joystick pJoystick) {

		try {

			// make sure we have the drive object.

			if (null != this.drive) {

				this.leftJoystick = pJoystick;

				this.drive.arcadeDrive(pJoystick);

			}

		} catch (Exception anEx) {
			this.error("driveWithJoystick()", anEx);
		}
	}

	public void driveWithJoysticks(final Joystick pLeftJoystick, final Joystick pRightJoystick) {

		try {

			// make sure we have the drive object.

			if (null != this.drive) {

				this.leftJoystick = pLeftJoystick;
				this.rightJoystick = pRightJoystick;

				this.drive.tankDrive(pLeftJoystick, pRightJoystick);

			}

		} catch (Exception anEx) {
			this.error("driveWithJoysticks()", anEx);
		}
	}

	public double getLeftDriveSpeed() {
		return (null == this.leftDrive ? 0 : this.leftDrive.getSpeed());
	}

	public double getRightDriveSpeed() {
		return (null == this.rightDrive ? 0 : this.rightDrive.getSpeed());
	}

	public void leftOn(final double pSpeed) {
		if (null != this.leftDrive) {

			this.leftDrive.set(pSpeed);
			WkwDashboard.setDriveLeftMotorSpeed(this.leftDrive.getSpeed());
		}
	}

	public void leftOff() {
		if (null != this.leftDrive) {

			this.leftDrive.set(0);
			WkwDashboard.setDriveLeftMotorSpeed(this.leftDrive.getSpeed());
		}
	}

	public void rightOn(final double pSpeed) {
		if (null != this.leftDrive) {

			this.rightDrive.set(pSpeed);
			WkwDashboard.setDriveLeftMotorSpeed(this.leftDrive.getSpeed());
		}
	}

	public void rightOff() {
		if (null != this.leftDrive) {

			this.rightDrive.set(0);
			WkwDashboard.setDriveLeftMotorSpeed(this.leftDrive.getSpeed());
		}
	}

	public void straight() {

		try {

			this.debug("straight()", "Called.");
			// sets the motor speeds to drive straight (no turn)
			if (null != this.drive) {
				this.drive.arcadeDrive(0.5, 0.5);
			}

		} catch (Exception anEx) {
			this.error("straight()", anEx);
		}
	}

	public void autonomousStraight() {

		try {

			// sets the motor speeds to drive straight (no turn)
			if (null != this.drive) {
				this.debug("autonomousStraight()", "Called.");
				this.drive.tankDrive(0.5, 0.5);
			}

		} catch (Exception anEx) {
			this.error("straight()", anEx);
		}
	}

	public void autonomousStraightBackward() {

		try {

			// sets the motor speeds to drive straight (no turn)
			if (null != this.drive) {
				this.debug("autonomousStraight()", "Called.");
				this.drive.tankDrive(-0.5, -0.5);
			}

		} catch (Exception anEx) {
			this.error("straight()", anEx);
		}
	}

	public void rotate() {

		try {

			this.debug("rotate()", "Called.");
			if (null != this.drive) {
				this.drive.arcadeDrive(-0.1, 0.1);
			}

		} catch (Exception anEx) {
			this.error("rotate()", anEx);
		}
	}

	public void stop() {

		try {

			this.debug("stop()", "Called.");
			if (null != this.drive) {
				this.drive.arcadeDrive(0.000000, 0.000000);
			}

		} catch (Exception anEx) {
			this.error("stop()", anEx);
		}
	}

	public void turnLeft90() {

		try {

			this.debug("turnLeft90()", "Called.");
			if (null != this.drive) {
				this.drive.arcadeDrive(0.0, 1.0);
			}

		} catch (Exception anEx) {
			this.error("turnLeft90()", anEx);
		}
	}

	public boolean selfTestInInitalState() {
		boolean isOk = true;

		try {

			if ((null != this.leftDrive) && (this.leftDrive.getSpeed() == 0)) {

				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
						"Left Drive", "Off", true, "Left drive motor is off.");

			} else {

				isOk = false;
				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
						"Left Drive", "Not Off", false, "Left drive motor is on. It should be off.");

			}

			if ((null != this.rightDrive) && (this.rightDrive.getSpeed() == 0)) {

				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
						"Right Drive", "Off", true, "Right drive motor is off.");

			} else {

				isOk = false;
				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM,
						"Right Drive", "Not Off", false,
						"Right drive motor is on. It should be off.");

			}

			if ((null != this.drive) && (this.drive.isAlive())) {

				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM, "Drive",
						"Alive", true, "Drive is alive.");

			} else {

				isOk = false;
				this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM, "Drive",
						"Not Alive", false, "Drive is not alive.");

			}

		} catch (Exception anEx) {
			isOk = false;
			this.reportSelfTestState(DriveSystem.LIVEWINDOW_SUBSYSTEMNAME_DRIVESYSTEM, "Global",
					"Exception", false, "Drive system caught exception name="
							+ anEx.getClass().getName() + ", message=" + anEx.getMessage() + ".");
		}

		return isOk;
	}

	protected void testAllGpios() {

		this.debug("testAllRelays()", "Testing " + DigitalInput.kDigitalChannels
				+ " GPIO channels.");

		DigitalInput aDigitalInput = null;

		for (int aChannel = 1; aChannel < DigitalInput.kDigitalChannels + 1; aChannel++) {

			try {

				this.debug("testAllGpios()", "GPIO channel=" + aChannel + ".");

				aDigitalInput = new DigitalInput(aChannel);

				this.debug("testAllGpios()", "GPIO channel=" + aChannel + ", value="
						+ aDigitalInput.get() + ".");

				Timer.delay(0.25);

				aDigitalInput.free();

			} catch (AllocationException anAloEx) {
				this.error("testAllGpios()", null);
			}
		}

	}

	protected void testAllRelays() {

		this.debug("testAllRelays()", "Testing " + Relay.kRelayChannels + " relay channels.");

		Relay aRelay = null;

		for (int aChannel = 1; aChannel < Relay.kRelayChannels + 1; aChannel++) {

			try {

				this.debug("testAllRelays()", "Relay channel=" + aChannel + ".");

				aRelay = new Relay(aChannel, Relay.Direction.kBoth);

				aRelay.set(Relay.Value.kForward);
				aRelay.set(Relay.Value.kOn);
				Timer.delay(0.5);
				aRelay.set(Relay.Value.kReverse);
				Timer.delay(0.5);
				aRelay.set(Relay.Value.kOff);
				aRelay.free();

			} catch (AllocationException anAloEx) {
				this.error("testAllRelays()", null);
			}
		}

	}

}
