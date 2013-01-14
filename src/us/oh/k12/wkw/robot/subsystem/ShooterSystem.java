/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;

/**
 * represents the ball shooter/launcher system in the robot.
 */
public class ShooterSystem extends ShooterPlunger {

	protected static final String SYSTEM_NAME = "Shooter";

	private static final double MILLIVOLTS_PER_INCH = (double) 5000.000000 / (double) 512.000000;
	private static final double INITIAL_SHOOTER_MOTOR_SPEED = (double) -0.63;

	// ball shooter jaguar and motor
	private Jaguar shootMotor;
	private boolean shootMotorIsOn = false;
	// current motor speed (when we turn it on).
	private double shootMotorSpeed;
	// tracking how long the motor has been on.
	private long shootMotorOnStartTime = 0;
	private long shootMotorTotalOnTime = 0;
	private AnalogChannel sonarSensor = null;
	private AnalogChannel gyroSensor = null;
	private AnalogChannel temperatureSensor = null;
	private double currentSonarVoltage = 0;
	private double batteryVoltage = 0;
	private double previousShootMotorSpeed = 0;

	/**
	 * Default constructor.
	 */
	public ShooterSystem() {

		super("ShooterSystem");

		try {

			this.debug("ShooterSystem()", "Started.");

			this.initMotor();

			this.initSonar();

			// TODO: wire gyro and connect - this.initGyro();

			this.debug("ShooterSystem()", "Ended.");

		} catch (Exception anEx) {
			this.error("ShooterSystem()", anEx);
		}
	}

	protected void initGyro() {

		this.gyroSensor = new AnalogChannel(WkwPrefs.getShooterGyroSensorChannel());

		if (null == this.gyroSensor) {

			this.error("initGyro()", "Gyro sensor not found.", null);

		}

		this.temperatureSensor = new AnalogChannel(WkwPrefs.getShooterTemperatureSensorChannel());

		if (null == this.temperatureSensor) {

			this.error("initGyro()", "Temperature sensor not found.", null);

		}

	}

	private void initSonar() {

		this.sonarSensor = new AnalogChannel(WkwPrefs.getShooterSonarSensorChannel());

		if (null == this.shootMotor) {

			this.error("initSonar()", "Sonar sensor not found.", null);

		}

	}

	private void initMotor() {

		// setup the ball shooter jaguar and motor.

		this.shootMotor = new Jaguar(WkwPrefs.getShooterMotorControllerChannel());

		//LiveWindow.addActuator("ShooterSystem", "shootMotor", this.shootMotor);
		
		if (null == this.shootMotor) {

			this.error("initMotor()", "Shoot motor not found.", null);

		} else {

			// initialize the motor.

			this.shootMotor.setExpiration(2.000000);
			this.shootMotor.setSafetyEnabled(false); // TODO: change to true
			this.shootMotor.set(0.000000); // make sure the motor is turned off.
			this.shootMotor.stopMotor();
			this.shootMotorIsOn = false;
			// set the initial motor speed.
			this.shootMotorSpeed = ShooterSystem.INITIAL_SHOOTER_MOTOR_SPEED;
			// reset the on time.
			this.shootMotorOnStartTime = 0;
			this.shootMotorTotalOnTime = 0;

			WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());

		}

	}

	protected void initDefaultCommand() {

		try {

			// no implementation.

		} catch (Exception anEx) {
			this.error("initDefaultCommand()", anEx);
		}
	}

	public void updateStatus() {

		try {

			if (null != this.shootMotor) {

				if (Math.abs(this.previousShootMotorSpeed - this.shootMotor.getSpeed()) > .001) {
					this.previousShootMotorSpeed = this.shootMotor.getSpeed();
					WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());
				}

			}

			if (Math.abs(this.batteryVoltage - DriverStation.getInstance().getBatteryVoltage()) > .01) {
				this.batteryVoltage = DriverStation.getInstance().getBatteryVoltage();
				WkwDashboard.setBatteryVolts(DriverStation.getInstance().getBatteryVoltage());
			}

			// }

			if (null != this.sonarSensor) {

				// LV-Maxsonar-EZ1.
				// note: any distance less than 6 inches returns 6 inches.
				// Note: maximum distance is 254 inches (21 feet).
				// Vcc = supplied voltage
				// Vi = volts / inch.
				// Vm = measured voltage.
				// Ri = range in inches.
				// Vcc / 512 = Vi example: 5 volts / 512 = 0.009766 volts.
				// Vm / Vi = Ri example: 292.98 milli volts / 9.766 milli volts = 30 inches.

				final double aSonarVoltage = this.sonarSensor.getAverageVoltage();

				if (Math.abs(this.currentSonarVoltage - aSonarVoltage) > .01) {
					this.currentSonarVoltage = aSonarVoltage;

					final double aRangeInInches = (aSonarVoltage * (double) 1000.000000)
							/ ShooterSystem.MILLIVOLTS_PER_INCH;

					WkwDashboard.setShooterSonarDistance(aRangeInInches);

				}
			}

			if (null != this.gyroSensor) {

				// ADW 22307 gyro sensor
				// 250 degrees / second.
				// normal standing still = 2.5 volts.
				// +7 milli volts / degrees / second.

				final double aGyroMilliVolts = this.gyroSensor.getAverageVoltage()
						* (double) 1000.00000;

				final double anOffset = 2500.000 - aGyroMilliVolts;

				final double aDegreesYaw = anOffset / (double) 7.000000;

				WkwDashboard.setShooterGyroDegreesYaw(aDegreesYaw);
			}

			if (null != this.temperatureSensor) {

				// ADW 22307 gyro (temperature) sensor.
				// normal @ 25 degrees centigrade = 2.5 volts.
				// +9 milli volts / degree centigrade.

				final double aTempMilliVolts = this.temperatureSensor.getAverageVoltage()
						* (double) 1000.00000;

				final double anOffset = (2500.000 - aTempMilliVolts) - (double) 25.000000;

				final double aDegreesCentigrade = anOffset / (double) 9.000000;

				final double aDegreesFahrenheit = (aDegreesCentigrade * (double) 9.00000 / (double) 5.000000)
						+ (double) 32.000000;

				WkwDashboard.setShooterTemperatureFahrenheit(aDegreesFahrenheit);
			}

			// note: not sending setShooterMotorTotalOnTime() here.

			super.updateStatus();

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}
	}

	/**
	 * Turn on the shooter motor.
	 */
	public void turnOnShootMotor() {

		try {

			// make sure we have the motor object

			if (null != this.shootMotor) {

				this.shootMotor.set(this.shootMotorSpeed);

				this.shootMotorIsOn = true;

				WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());

				this.shootMotorOnStartTime = System.currentTimeMillis();

			}

		} catch (Exception anEx) {
			this.error("turnOnShootMotor()", anEx);
		}
	}

	/**
	 * Turn off the shooter motor.
	 */
	public void turnOffShootMotor() {

		try {

			// make sure we have a motor object.

			if (null != this.shootMotor) {

				this.shootMotor.set(0.00000000); // set speed at zero

				this.shootMotor.stopMotor(); // stop the motor

				this.shootMotorIsOn = false;

				WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());

				this.shootMotorTotalOnTime += System.currentTimeMillis()
						- this.shootMotorOnStartTime;
				this.shootMotorOnStartTime = 0;

				WkwDashboard.setShooterMotorTotalOnTime(this.shootMotorTotalOnTime);

			}

		} catch (Exception anEx) {
			this.error("turnOffShootMotor()", anEx);
		}
	}

	/**
	 * Set the shoot motor speed. Note: this does NOT turn on the motor.
	 * 
	 * @param pSpeed
	 *            rpms (-1.0 to +1.0).
	 */
	public void setShootMotorSpeed(final double pSpeed) {

		try {

			double aSpeed = pSpeed;

			this.debug("setShootMotorSpeed()", "aSpeed=" + aSpeed + ".");

			if (aSpeed < -1.000000) {
				aSpeed = -1.000000;
			} else if (aSpeed > 1.000000) {
				aSpeed = 1.000000;
			}

			// save the new speed value.

			this.shootMotorSpeed = aSpeed;

			// Doesn't allow the motor to fire in reverse

			if (this.shootMotorSpeed > 0.000000) {
				this.shootMotorSpeed = 0.000000;
			}

			this.debug("setShootMotorSpeed()", "shootMotorSpeed=" + this.shootMotorSpeed + ".");

			WkwDashboard.setShooterDesiredSpeed(this.shootMotorSpeed);

			// do we have a motor object and is the motor on?

			if ((null != this.shootMotor) && (this.shootMotorIsOn)) {

				// set the new speed in the motor.
				this.shootMotor.set(this.shootMotorSpeed);
				WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());

			}

		} catch (Exception anEx) {
			this.error("setShootMotorSpeed()", anEx);
		}
	}

	/**
	 * Convenience method to increase the motor speed. This call the setShootMotorSpeed() method.
	 * 
	 * @param pSpeed
	 *            Offset from current speed.
	 */
	public void increaseMotorSpeed(final double pSpeed) {
		this.setShootMotorSpeed(this.shootMotorSpeed - pSpeed);
	}

	/**
	 * Convenience method to decrease the motor speed. This call the setShootMotorSpeed() method.
	 * 
	 * @param pSpeed
	 *            Offset from current speed.
	 */
	public void decreaseMotorSpeed(final double pSpeed) {
		this.setShootMotorSpeed(this.shootMotorSpeed + pSpeed);
	}

	/**
	 * How fast is the shoot motor turning?
	 * 
	 * @return rpms.
	 */
	public double getShootMotorSpeed() {
		return (null == this.shootMotor ? 0 : this.shootMotor.getSpeed());
	}

	/**
	 * Is the shoot motor running?
	 * 
	 * @return true if shoot motor is running.
	 */
	public boolean isShootMotorOn() {
		return this.shootMotorIsOn;
	}

	/*
	public boolean selfTestInInitalState() {
		boolean isOk = true;

		try {

			isOk = super.selfTestInInitalState();

			if ((null != this.shootMotor) && (this.shootMotor.getSpeed() == 0)) {

				this.reportSelfTestState(ShooterSystem.SYSTEM_NAME, "Shoot Motor", "Stopped", true,
						"Shooter system shoot motor is off.");

			} else {

				isOk = false;
				this.reportSelfTestState(ShooterSystem.SYSTEM_NAME, "Shoot Motor", "Not Stopped",
						false, "Shooter system shoot motor is not stopped.");

			}

		} catch (Exception anEx) {
			isOk = false;
			this.reportSelfTestState(ShooterSystem.SYSTEM_NAME, "Global", "Exception", false,
					"Shooter system caught exception name=" + anEx.getClass().getName()
							+ ", message=" + anEx.getMessage() + ".");
		}

		return isOk;
	}
	*/

	public long getShootMotorTotalOnTime() {
		return this.shootMotorTotalOnTime;
	}

}
