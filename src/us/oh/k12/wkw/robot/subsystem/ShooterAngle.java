/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

/**
 * Represents the shooter angle (elevation).
 */
public abstract class ShooterAngle extends SystemBase { // TODO: change to extends PIDSystemBase

	private static final double ELEVATION_POT_MAX_VOLTAGE = 5.0;
	private static final double ELEVATION_POT_MIN_VOLTAGE = 0.0;
	private static final double ELEVATION_MIN_ANGLE = 0;

	/*
	 * TODO:
	private static final int ELEVATION_MOTOR_CHANNEL = ShooterAngle.getPreference(
			"ElevationMotorControllerChannel", 6);
	private static final int ELEVATION_POT_CHANNEL = ShooterAngle.getPreference(
			"ElevationPotentiometerSensorChannel", 2);
	private static final double ELEVATION_START_VOLTAGE = ShooterAngle.getPreference(
			"ElevationPotentiometerSensorStartVoltage", ShooterAngle.ELEVATION_POT_MIN_VOLTAGE);
	private static final double ELEVATION_PID_PROPORTIONAL = ShooterAngle.getPreference(
			"ElevationPotentiometerPidProportional", (double) 2.3);
	private static final double ELEVATION_PID_INTEGRAL = ShooterAngle.getPreference(
			"ElevationPotentiometerPidIntegral", (double) 0);
	private static final double ELEVATION_PID_DERIVATIVE = ShooterAngle.getPreference(
			"ElevationPotentiometerPidDerivative", (double) 0);
	*/

	// TODO: private Jaguar elevationMotor;
	// TODO: private AnalogChannel elevationPot;

	/**
	 * Default constructor.
	 */
	protected ShooterAngle() {
		this("ShooterAngle");
	}

	/**
	 * The default constructor with system name.
	 * 
	 * @param pName
	 *            system name.
	 */
	protected ShooterAngle(final String pName) {

		super(pName);

		// TODO: super("ShooterSystem", ShooterSystem.ELEVATION_PID_PROPORTIONAL,
		// ShooterSystem.ELEVATION_PID_INTEGRAL, ShooterSystem.ELEVATION_PID_DERIVATIVE);

		/*
		try {
			this.elevationMotor = new Jaguar(ShooterSystem.ELEVATION_MOTOR_CHANNEL);
			if (null == this.elevationMotor) {
				this.error("ShooterSystem()", "Elevation motor not found.", null);
			} else {
				this.elevationMotor.setExpiration(2.0);
				this.elevationMotor.setSafetyEnabled(false); // TODO: change to true
			}
		} catch (AllocationException anAloEx) {
			this.error("ShooterSystem()", anAloEx);
		}

		try {
			this.elevationPot = new AnalogChannel(ShooterSystem.ELEVATION_POT_CHANNEL);
		} catch (AllocationException anAloEx) {
			this.error("ShooterSystem()", anAloEx);
		}
		aDriverStation.setDigitalOut(ShooterSystem.ELEVATION_SET_INDICATOR_PORT, false);
		*/

		// this.setSetpointRange(ShooterSystem.ELEVATION_POT_MAX_VOLTAGE,
		// ShooterSystem.ELEVATION_POT_MIN_VOLTAGE);

		// this.setSetpoint(ShooterSystem.ELEVATION_START_VOLTAGE);

		// enable the elevation PIDController
		// when the robot is enabled, the PIDController
		// will keep the elevation at the set value.
		// this.enable();

	}

	public void updateStatus() {

		// nothing

	}

	/**
	 * PID controller method.
	 * 
	 * @return current pot value (represents angle)
	 */
	protected double returnPIDInput() {
		double anInput = 0;
		/*
		if (null != this.elevationPot) {
			anInput = this.elevationPot.getAverageVoltage()
					/ ShooterSystem.ELEVATION_POT_MAX_VOLTAGE;
			// this.debug("returnPIDInput()", "Called, anInput=" + anInput + ".");
		}
		*/
		/*
		if (this.isAtExpectedElevation()) {
			DriverStation.getInstance().setDigitalOut(ShooterSystem.ELEVATION_SET_INDICATOR_PORT, true);
		} else {
			DriverStation.getInstance().setDigitalOut(ShooterSystem.ELEVATION_SET_INDICATOR_PORT, false);
		}
		*/

		return anInput;
	}

	/**
	 * PID controller method.
	 * 
	 * @param pOutput
	 *            new value to set in the motor.
	 */
	protected void usePIDOutput(double pOutput) {
		// this.debug("usePIDOutput()", "Called, pOutput=" + pOutput + ".");
		/*
		if (null != this.elevationMotor) {
			this.elevationMotor.set(pOutput);
		}
		*/
	}

	/**
	 * Set the expected/desired angle of the shooter.
	 * 
	 * @param pAngle
	 *            angle in degrees.
	 */
	public void setElevationAngle(final double pAngle) {

		try {

			this.setElevationVoltage(this.convertElevationAngleToPotVoltage(pAngle));

		} catch (Exception anEx) {
			this.error("setElevationAngle()", anEx);
		}
	}

	/**
	 * Increase the shooter angle.
	 * 
	 * @param pAngle
	 *            angle in degrees to add.
	 */
	public void increaseElevationAngle(final double pAngle) {
		this.setElevationAngle(this.getElevationAngle() + pAngle);
	}

	/**
	 * Decrease the shooter angle.
	 * 
	 * @param pAngle
	 *            angle in degrees to subtract.
	 */
	public void decreaseElevationAngle(final double pAngle) {
		this.setElevationAngle(this.getElevationAngle() - pAngle);
	}

	/**
	 * lower the shooter to the lowest position.
	 */
	public void resetElevationAngle() {
		this.setElevationAngle(0);
	}

	public void setElevationVoltage(final double pVoltage) {

		try {

			double aVoltage = pVoltage;

			/*
			if (aVoltage > ShooterAngle.ELEVATION_POT_MAX_VOLTAGE) {

				aVoltage = ShooterAngle.ELEVATION_POT_MAX_VOLTAGE;

			} else if (aVoltage < ShooterAngle.ELEVATION_POT_MIN_VOLTAGE) {

				aVoltage = ShooterAngle.ELEVATION_POT_MIN_VOLTAGE;

			}
			*/

			this.debug("setElevationVoltage()", "Called, aVoltage=" + aVoltage + ".");

			// TODO: this.setSetpoint(aVoltage);

		} catch (Exception anEx) {
			this.error("setElevationVoltage()", anEx);
		}
	}

	public boolean isAtExpectedElevation() {
		return true; // TODO: (this.getPosition() == this.getSetpoint());
	}

	/**
	 * @return angle of elevation (0 to 90).
	 */
	public double getElevationAngle() {
		return 0;// TODO: this.convertElevationPotVoltageToAngle(this.getPosition());
	}

	/*
	 * 
	 * helper methods.
	 * 
	 */

	/**
	 * convert angle (0 to 90) to servo position (0.0 to 1.0).
	 * 
	 * @param pAngle
	 *            angle (0 to 90).
	 * @return servo position.
	 */
	private double convertElevationAngleToPotVoltage(final double pAngle) {
		double aVoltage = 0;

		final double aFactor = ShooterAngle.ELEVATION_POT_MAX_VOLTAGE
				/ ShooterAngle.ELEVATION_POT_MIN_VOLTAGE;

		aVoltage = (pAngle - ShooterAngle.ELEVATION_MIN_ANGLE) * aFactor;

		this.debug("convertElevationAngleToPotVoltage()", "pAngle=" + pAngle + ", aVoltage="
				+ aVoltage + ", aFactor=" + aFactor + ", min="
				+ ShooterAngle.ELEVATION_POT_MIN_VOLTAGE + ", max="
				+ ShooterAngle.ELEVATION_POT_MAX_VOLTAGE + ".");

		return aVoltage;
	}

	protected double convertElevationPotVoltageToAngle(final double pVoltage) {
		double anAngle = 0;

		final double aFactor = ShooterAngle.ELEVATION_POT_MAX_VOLTAGE
				/ ShooterAngle.ELEVATION_POT_MIN_VOLTAGE;

		anAngle = pVoltage * aFactor;

		this.debug("convertElevationPotVoltageToAngle()", "pVoltage=" + pVoltage + ", anAngle="
				+ anAngle + ", aFactor=" + aFactor + ", min="
				+ ShooterAngle.ELEVATION_POT_MIN_VOLTAGE + ", max="
				+ ShooterAngle.ELEVATION_POT_MAX_VOLTAGE + ".");

		return anAngle + ShooterAngle.ELEVATION_MIN_ANGLE;
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		// TODO:

		return isOk;
	}
	*/

}
