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
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Represents the shooter plunger.
 */
public abstract class ShooterPlunger extends ShooterAngle {

	// the spike relay (forward and reverse motor controller)
	private Relay plungerMotorRelay;
	// switch that tells us when to stop plunger motor
	private DigitalInput plungerMotorSwitch;
	// after plunger motor starts rotating,
	// the plungerMotorSwitch will close.
	private boolean haveSeenSwitchDown = false;
	// is plunger reset - so the next ball will drop in the shooter
	private boolean plungerIsReady;
	// how many times have we fired the plunger
	private int plungerFireCount = 0;

	private boolean plungerMotorSwitchState = false;

	// private double delay = -1;

	/**
	 * Null constructor - not normally used.
	 */
	protected ShooterPlunger() {
		this("ShooterPlunger");
	}

	/**
	 * The default constructor with system name.
	 * 
	 * @param pName
	 *            system name.
	 */
	protected ShooterPlunger(final String pName) {

		super(pName);

		try {

			// plunger motor relay
			this.plungerMotorRelay = new Relay(WkwPrefs.getShooterPlungerControllerChannel(),
					Relay.Direction.kReverse);

			// setup the ball plunger relay.
			this.plungerMotorSwitch = new DigitalInput(WkwPrefs.getShooterPlungerSwitchChannel());

			this.haveSeenSwitchDown = false;
			this.setPlungerIsReady(true);
			this.plungerFireCount = 0;

		} catch (AllocationException anAloEx) {
			this.error("ShooterPlunger()", anAloEx);
		} catch (Exception anEx) {
			this.error("ShooterPlunger()", anEx);
		}

	}

	public void updateStatus() {

		if (null != this.plungerMotorSwitch) {
			if (this.plungerMotorSwitchState != this.plungerMotorSwitch.get()) {
				this.plungerMotorSwitchState = this.plungerMotorSwitch.get();
				WkwDashboard.setShooterPlungerSwitch(this.plungerMotorSwitchState);
			}
		}

		super.updateStatus();

	}

	private void setPlungerIsReady(final boolean pIsReady) {
		this.plungerIsReady = pIsReady;
		WkwDashboard.setShooterPlungerReady(this.plungerIsReady);
	}

	public void initFiringCycle() {
		this.haveSeenSwitchDown = false;
	}

	/**
	 * Fire the ball plunger.
	 */
	public void startPlungerMotor() {

		try {

			// make sure we have the plunger object.

			if (null != this.plungerMotorRelay) {

				this.debug("startPlungerMotor()", "Called.");

				this.plungerMotorRelay.set(Relay.Value.kOn);

				this.setPlungerIsReady(false);
				// this.delay = -1;
				this.plungerFireCount++;
				WkwDashboard.setShooterTotalFires(this.getPlungerFireCount());
			}

		} catch (Exception anEx) {
			this.error("startPlungerMotor()", anEx);
		}
	}

	public void stopPlungerMotor() {

		try {

			this.debug("firePlunger()", "Called.");

			this.plungerMotorRelay.set(Relay.Value.kOff);

			this.setPlungerIsReady(true);

		} catch (Exception anEx) {
			this.error("stopPlungerMotor()", anEx);
		}
	}

	public boolean isStopMotor() {

		boolean isStopMotor = false;

		// if (this.delay > 0) {

		// if (--this.delay <= 0) {
		// isStopMotor = true;
		// this.delay = -1;
		// }

		// } else {

		// this.debug("isStopMotor()",
		// "this.plungerMotorSwitch.get() = " + this.plungerMotorSwitch.get());
		if (this.plungerMotorSwitch.get()) {
			this.haveSeenSwitchDown = true;
			// this.debug("isStopMotor()", "this.haveSeenSwitchDown = true");
		}
		// this.haveSeenSwitchDown && !4

		isStopMotor = !this.plungerMotorSwitch.get() && this.haveSeenSwitchDown;

		this.debug("isStopMotor()", "isStopMotor=" + isStopMotor + ".");

		if (isStopMotor) { // && (this.delay < 0)
			// this.delay = 31;
			// isStopMotor = true;
			this.stopPlungerMotor();
		}

		// }

		// this.debug("isStopMotor()", "haveSeenSwitchDown=" + this.haveSeenSwitchDown
		// + ", isStopMotor=" + isStopMotor + ", delay=" + this.delay + ".");

		return isStopMotor;
	}

	/**
	 * Is the plunger reset to the not fire position?
	 * 
	 * @return true if in position to fire.
	 */
	public boolean isPlungerReady() {
		return (null == this.plungerMotorRelay ? false : this.plungerIsReady);
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		isOk = super.selfTestInInitalState();

		if (this.isPlungerReady()) {

			this.reportSelfTestState(ShooterSystem.SYSTEM_NAME, "Ball Plunger", "Ready", true,
					"Launching system ball plunger is ready.");

		} else {

			isOk = false;
			this.reportSelfTestState(ShooterSystem.SYSTEM_NAME, "Ball Plunger", "Not Ready", false,
					"Launching system ball plunger is not ready.");

		}

		return isOk;
	}
	*/

	public int getPlungerFireCount() {
		return this.plungerFireCount;
	}

}
