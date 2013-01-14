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
 * System to extend and retract the beaching board (device used to lower the ramp). This system
 * consists on a spike relay (forward and reverse motor controller), window motor, input switch to
 * detect that the beaching board in retracted (inside robot), input switch to detect that the
 * beaching board is extended (outside robot).
 */
public class BeachingSystem extends SystemBase {

	// private static final String SYSTEM_NAME = "Beaching";

	// the spike relay (forward and reverse motor controller)
	private Relay beachMotorRelay;
	// the input switch to detect that the beaching board is extended
	private DigitalInput beachExtendedInput;
	// the input switch to detect that the beaching board in retracted
	// private DigitalInput beachRetractedInput;
	// flag indicating that the relay has been set in the forward and on state
	private boolean extending = false;
	// flag indicating that the relay has been set in the reverse and on state
	private boolean retracting = false;
	// keeping track of the amount of time the motor is consuming power.
	private long motorOnStartTime = 0;
	private long motorTotalOnTime = 0;
	private boolean beachExtendedSwitchState = true;

	private int delay = 0;

	// private boolean beachRetractedSwitchState = true;

	/**
	 * The default null constructor.
	 */
	public BeachingSystem() {

		super("BeachingSystem");

		try {

			this.debug("BeachingSystem()", "Started.");

			this.initRelay();

			this.initExtendedSwitch();

			// this.initRetractedSwitch();

			this.debug("BeachingSystem()", "Ended.");

		} catch (AllocationException anAloEx) {
			this.error("BeachingSystem()", anAloEx);

		} catch (Exception anEx) {
			this.error("BeachingSystem()", anEx);
		}
	}

	private void initRelay() {

		// setup the relay. it goes in both forward and reverse direction.

		this.beachMotorRelay = new Relay(WkwPrefs.getBeachMotorControllerChannel(),
				Relay.Direction.kBoth);

		//LiveWindow.addActuator("BeachingSystem", "beachMotorRelay", this.beachMotorRelay);
		
		if (null == this.beachMotorRelay) {

			this.error("initRelay()", "Beach motor relay not found.", null);

		} else {

			// initialize the relay (and motor) to the off (stopped) state.

			this.beachMotorRelay.set(Relay.Value.kOff);

		}

		// initialize state variables

		this.extending = false;
		this.retracting = false;
		this.motorOnStartTime = 0;
		this.motorTotalOnTime = 0;

		// turn off the operator station beaching board extended indicator.

		WkwDashboard.setBeachExtended(false);

	}

	private void initExtendedSwitch() {

		// setup the extended input switch.

		this.beachExtendedInput = new DigitalInput(WkwPrefs.getBeachExtendedInputChannel());

		//LiveWindow.addSensor("BeachingSystem", "beachExtendedInput", this.beachExtendedInput);
		
		if (null == this.beachExtendedInput) {

			this.error("initExtendedSwitch()", "Beach extended input switch not found.", null);

		}

	}

	/*
	private void initRetractedSwitch() {

		// setup the retracted input switch.

		this.beachRetractedInput = new DigitalInput(WkwPrefs.getBeachRetractedInputChannel());

		if (null == this.beachRetractedInput) {

			this.error("initRetractedSwitch()", "Beach retracted input switch not found.", null);

		}

	}
	*/

	protected void initDefaultCommand() {

		try {

			// no implementation

		} catch (Exception anEx) {
			this.error("initDefaultCommand()", anEx);
		}
	}

	public void updateStatus() {

		try {

			WkwDashboard.setBeachExtending(this.extending);

			WkwDashboard.setBeachRetracting(this.retracting);

			if (null != this.beachExtendedInput) {
				if (this.beachExtendedSwitchState != this.beachExtendedInput.get()) {
					this.beachExtendedSwitchState = this.beachExtendedInput.get();
					WkwDashboard.setBeachExtended(this.beachExtendedSwitchState);
				}
			}

			/*
			if (null != this.beachRetractedInput) {
				if (this.beachRetractedSwitchState != this.beachRetractedInput.get()) {
					this.beachRetractedSwitchState = this.beachRetractedInput.get();
					WkwDashboard.setBeachRetracted(this.beachRetractedSwitchState);
				}
			}
			*/

			WkwDashboard.setBeachTotalOnTime(this.getMotorTotalOnTime());

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}

	}

	public void extendInit() {
		this.delay = 5;
		this.extend();
	}

	/**
	 * Extend the beaching board.
	 */
	public void extend() {

		try {

			if (null != this.beachMotorRelay) {

				// this.debug("extend()", "Extending.");

				this.beachMotorRelay.set(Relay.Value.kForward); // forward direction
				this.setExtending(true); // set flag
				this.motorOnStartTime = System.currentTimeMillis(); // store off now
			}

		} catch (Exception anEx) {
			this.error("extend()", anEx);
		}
	}

	public void stop() {

		try {

			if (null != this.beachMotorRelay) {

				this.beachMotorRelay.set(Relay.Value.kOff); // stop the motor.

				if (this.isExtending()) {
					this.setExtending(false); // reset the flag.
				}

				if (this.isRetracting()) {
					this.setRetracting(false);
				}

				this.motorTotalOnTime = (System.currentTimeMillis() - this.motorOnStartTime);

			}

		} catch (Exception anEx) {
			this.error("stop()", anEx);
		}
	}

	public void retractInit() {
		this.delay = 5;
		this.retract();
	}

	/**
	 * Retract the beaching board.
	 */
	public void retract() {

		try {

			if (null != this.beachMotorRelay) {

				// this.debug("retract()", "Starting retract.");

				this.beachMotorRelay.set(Relay.Value.kReverse); // reverse direction
				this.setRetracting(true); // set flag
				this.motorOnStartTime = System.currentTimeMillis(); // store off now

			}

		} catch (Exception anEx) {
			this.error("retract()", anEx);
		}
	}

	/**
	 * Is the board extended? This is used by the extend command to detect (isFinished method) when
	 * the forward moving board hits the extend input switch.
	 * 
	 * @return true if extend input switch is closed.
	 */
	public boolean isExtended() {
		boolean isExtended = false;

		// make sure we have the extended input object.

		if (null != this.beachExtendedInput) {

			// this.debug("isExtended()", "delay=" + this.delay + ".");

			if (this.delay-- <= 0) {

				// get the extend input switch state.

				isExtended = !this.beachExtendedInput.get();

				// System.out.println("isExtended() isExtended=" + isExtended + ".");

				// check if we are extending and if the switch is now closed.

				if (isExtended) {

					// we are extending and the switch is hit (closed).
					// this.debug("isExtended()",
					// "Forward moving beach board hit the extended switch.");
					this.beachMotorRelay.set(Relay.Value.kOff); // stop the motor.
					this.setExtending(false); // reset the flag.
					WkwDashboard.setBeachExtended(this.beachExtendedInput.get());
					this.motorTotalOnTime = (System.currentTimeMillis() - this.motorOnStartTime);

				}

			}

		}

		return isExtended;
	}

	/**
	 * Is the board retracted? This is used by the retract command to detect (isFinished method)
	 * when the reverse moving board hits the retracted input switch.
	 * 
	 * @return true if retracted input switch is closed.
	 */
	public boolean isRetracted() {
		boolean isRetracted = false;

		// make sure we have the retracted input object.

		if (null != this.beachExtendedInput) {

			// this.debug("isRetracted()", "delay=" + this.delay + ".");

			if (this.delay-- <= 0) {

				// get the retracted input state

				isRetracted = !this.beachExtendedInput.get();

				// System.out.println("isRetracted() isRetracted=" + isRetracted + ".");

				// check if we are retracting and if the switch is now closed.

				if (isRetracted) {

					// we are retracting and the switch is hit (closed).
					// this.debug("isRetracted()",
					// "Reverse moving beach board hit retracted switch.");
					this.beachMotorRelay.set(Relay.Value.kOff); // stop the motor.
					this.setRetracting(false); // reset the flag.
					WkwDashboard.setBeachRetracted(this.beachExtendedInput.get());
					this.motorTotalOnTime = (System.currentTimeMillis() - this.motorOnStartTime);

				}

			}
		}

		return isRetracted;
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		try {

			if (this.isRetracted()) {
				this.reportSelfTestState(BeachingSystem.SYSTEM_NAME, "Retracted Switch",
						"Retracted", true,
						"Breaching system retracted switch returned the retracted state. The switch is closed.");
			} else {
				isOk = false;
				this.reportSelfTestState(BeachingSystem.SYSTEM_NAME, "Retracted Switch",
						"Not Retracted", false,
						"Beaching system retracted switch did not return the retracted state. The switch should be closed.");
			}

			if (!this.isExtended()) {
				this.reportSelfTestState(BeachingSystem.SYSTEM_NAME, "Extended Switch",
						"Not Extended", true,
						"Breaching system extended switch returned the not extended state. The switch is open.");
			} else {
				isOk = false;
				this.reportSelfTestState(BeachingSystem.SYSTEM_NAME, "Extended Switch", "Extended",
						false,
						"Beaching system extended switch did not return the not extended state. The switch should be open.");
			}

		} catch (Exception anEx) {
			isOk = false;
			this.reportSelfTestState(BeachingSystem.SYSTEM_NAME, "Global", "Exception", false,
					"Beaching system caught exception name=" + anEx.getClass().getName()
							+ ", message=" + anEx.getMessage() + ".");
		}

		return isOk;
	}
	*/

	public long getMotorTotalOnTime() {
		return this.motorTotalOnTime;
	}

	private boolean isExtending() {
		return this.extending;
	}

	private void setExtending(final boolean pExtending) {
		this.extending = pExtending;
		WkwDashboard.setBeachExtending(this.extending);
	}

	private boolean isRetracting() {
		return this.retracting;
	}

	private void setRetracting(final boolean pRetracting) {
		this.retracting = pRetracting;
		WkwDashboard.setBeachRetracting(this.retracting);
	}

}
