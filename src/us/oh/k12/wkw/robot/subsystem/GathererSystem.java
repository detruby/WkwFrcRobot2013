/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * System to sweep the ball into the collector and elevate the ball up to the hopper.
 */
public class GathererSystem extends SystemBase {

	// private static final String SYSTEM_NAME = "Gatherer";

	// jaguar motor controller.
	private Jaguar gathererMotor;
	// keeping track of the amount of time the motor is consuming power.
	private long motorOnStartTime = 0;
	private long motorTotalOnTime = 0;
	private DigitalInput shooterBallPresentSwitch;
	private DigitalInput gathererBallPresentSwitch;

	private double gathererMotorSpeed = 0;
	private boolean gathererBallPresent = true;
	private boolean shooterBallPresent = true;

	/**
	 * The default null constructor.
	 */
	public GathererSystem() {

		super("GathererSystem");

		try {

			this.debug("GathererSystem()", "Started.");

			this.initMotor();

			this.initSwitches();

			this.debug("GathererSystem()", "Ended.");

		} catch (AllocationException anAloEx) {
			this.error("GathererSystem()", anAloEx);

		} catch (Exception anEx) {
			this.error("GathererSystem()", anEx);
		}
	}

	private void initMotor() {

		// initialize the jaguar motor controller.

		this.gathererMotor = new Jaguar(WkwPrefs.getGathererMotorControllerChannel());

		//LiveWindow.addActuator("GathererSystem", "gathererMotor", this.gathererMotor);
		
		if (null == this.gathererMotor) {

			this.error("initMotor()", "Gatherer motor not found.", null);

		} else {

			// initialize the motor to the stopped state.

			// this.gathererMotor.setExpiration(2.0);
			this.gathererMotor.setSafetyEnabled(false);
			this.gathererMotor.set(0);
			this.gathererMotor.stopMotor();

		}

		// initialize state variables

		this.motorOnStartTime = 0;
		this.motorTotalOnTime = 0;

		// turn off the operator station sweeper motor on indicator.

		WkwDashboard.setGathererOn(false);

	}

	private void initSwitches() {

		this.gathererBallPresentSwitch = new DigitalInput(
				WkwPrefs.getGathererBallPresentSensorChannel());

		//LiveWindow.addSensor("GathererSysten", "gathererBallPresentSwitch", this.gathererBallPresentSwitch);
		
		this.shooterBallPresentSwitch = new DigitalInput(
				WkwPrefs.getShooterBallPresentSensorChannel());

		//LiveWindow.addSensor("GathererSysten", "shooterBallPresentSwitch", this.shooterBallPresentSwitch);
	}

	protected void initDefaultCommand() {

		try {

			// no implementation

		} catch (Exception anEx) {
			this.error("initDefaultCommand()", anEx);
		}
	}

	public void updateStatus() {

		try {

			if (null != this.gathererMotor) {
				if (this.gathererMotorSpeed != this.gathererMotor.getSpeed()) {
					this.gathererMotorSpeed = this.gathererMotor.getSpeed();
					WkwDashboard.setGathererSpeed(this.gathererMotorSpeed);
				}
			}

			if (null != this.gathererBallPresentSwitch) {

				if (this.gathererBallPresent != this.gathererBallPresentSwitch.get()) {
					this.gathererBallPresent = this.gathererBallPresentSwitch.get();
					WkwDashboard.setGathererBallPresent(this.gathererBallPresent);
				}
			}

			if (null != this.shooterBallPresentSwitch) {

				if (this.shooterBallPresent != this.shooterBallPresentSwitch.get()) {
					this.shooterBallPresent = this.shooterBallPresentSwitch.get();
					WkwDashboard.setShooterBallPresent(this.shooterBallPresent);
				}
			}

			WkwDashboard.setGathererTotalOnTime(this.getMotorTotalOnTime());

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}

	}

	/**
	 * turn on the sweeper motor.
	 */
	public void turnOn() {

		try {

			// make sure we have the motor object.

			if (null != this.gathererMotor) {

				this.debug("turnOn()", "Turn on.");

				if (!this.isBothBallsPresent()) {

					this.gathererMotor.set(WkwPrefs.getGathererMotorSpeed()); // turn on
					this.motorOnStartTime = System.currentTimeMillis(); // save now

					// turn on the operator station sweeper motor on indicator.
					WkwDashboard.setGathererOn(true);

				} else {
					this.error("turnOn()", "Not turning on because both balls are present.", null);
				}

			}

		} catch (Exception anEx) {
			this.error("turnOn()", anEx);
		}
	}

	/**
	 * Changes the direction of the gathering System
	 * 
	 * @param pSpeed
	 *            negative of motor speed
	 */
	public void turnOnBackward() {

		try {

			// make sure we have the motor object.

			if (null != this.gathererMotor) {

				this.debug("turnOnBackward()", "Turn on backward.");

				this.gathererMotor.set(-WkwPrefs.getGathererMotorSpeed()); // turn on
				this.motorOnStartTime = System.currentTimeMillis(); // save now

				// turn on the operator station sweeper motor on indicator.
				WkwDashboard.setGathererOn(true);

			}

		} catch (Exception anEx) {
			this.error("turnOnBackward()", anEx);
		}

	}

	/**
	 * turn off the sweeper motor.
	 */
	public void turnOff() {

		try {

			// make suer we have the motor object.

			if (null != this.gathererMotor) {

				this.debug("turnOff()", "Turn off.");

				this.gathererMotor.set(0);
				this.gathererMotor.stopMotor();
				this.motorTotalOnTime += System.currentTimeMillis() - this.motorOnStartTime;
				this.motorOnStartTime = 0;

				// turn off the operator station sweeper motor indicator.
				WkwDashboard.setGathererOn(false);

			}

		} catch (Exception anEx) {
			this.error("turnOff()", anEx);
		}
	}

	/**
	 * is the sweeper motor on?
	 * 
	 * @return true if on.
	 */
	public boolean isOn() {
		return ((null == this.gathererMotor ? false : this.gathererMotor.getSpeed() != 0));
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		try {

			if ((null != this.gathererMotor) && (this.gathererMotor.getSpeed() == 0)) {

				this.reportSelfTestState(GathererSystem.SYSTEM_NAME, "Gatherer Motor Speed", "Off",
						true, "Gatherer motor is off.");
			} else {
				isOk = false;
				this.reportSelfTestState(GathererSystem.SYSTEM_NAME, "Gatherer Motor Speed",
						"Not Off", false, "Gatherer motor is on. It should be off.");
			}

		} catch (Exception anEx) {
			isOk = false;
			this.reportSelfTestState(GathererSystem.SYSTEM_NAME, "Global", "Exception", false,
					"Gatherer system caught exception name=" + anEx.getClass().getName()
							+ ", message=" + anEx.getMessage() + ".");
		}

		return isOk;
	}
	*/

	public long getMotorTotalOnTime() {
		return this.motorTotalOnTime;
	}

	public boolean isBothBallsPresent() {
		return (this.isShooterBallPresent() && this.isGathererBallPresent());
	}

	public boolean isShooterBallPresent() {
		boolean isPresent = false;

		if (null != this.shooterBallPresentSwitch) {

			isPresent = this.shooterBallPresentSwitch.get();

			if (isPresent) {

				// turn on the operator station ball present indicator.
				WkwDashboard.setShooterBallPresent(true);

			} else {

				// turn off the operator station ball present indicator.
				WkwDashboard.setShooterBallPresent(false);

			}

		}

		return isPresent;
	}

	public boolean isGathererBallPresent() {
		boolean isPresent = false;

		if (null != this.gathererBallPresentSwitch) {

			isPresent = this.gathererBallPresentSwitch.get();

			if (isPresent) {

				// turn on the operator station ball present indicator.
				WkwDashboard.setGathererBallPresent(true);

			} else {

				// turn off the operator station ball present indicator.
				WkwDashboard.setGathererBallPresent(false);

			}

		}

		return isPresent;
	}

}
