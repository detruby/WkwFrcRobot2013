/**
 * 
 */
package us.oh.k12.wkw.robot.subsystem;

import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * @author Team4145
 * 
 */
public class LaunchFrisbeeSystem extends SystemBase {

	protected static final String SYSTEM_NAME = "Launcher";

	private static final String NETWORKTABLE_NAME_KINECT_COORDINATES = "BFR_COORDINATES";
	private static final String NETWORKTABLE_NAME_KINECT_DISTANCE = "Distance";

	private static final double INITIAL_SHOOTER_MOTOR_SPEED = (double) -0.63;

	// ball shooter jaguar and motor
	private Jaguar shootMotor;
	private boolean shootMotorIsOn = false;
	// current motor speed (when we turn it on).
	private double shootMotorSpeed;
	// tracking how long the motor has been on.
	private long shootMotorOnStartTime = 0;
	private long shootMotorTotalOnTime = 0;
	private double previousShootMotorSpeed = 0;
	private double batteryVoltage = 0;

	private double targetCenterX = 0;
	private double targetCenterY = 0;

	public LaunchFrisbeeSystem() {
		super("LauncherSystem");

		try {

			this.debug("LaunchFrisbeeSystem()", "Started.");

			this.initMotor();

			// this.initSonar();

			// TODO: wire gyro and connect - this.initGyro();

			this.debug("LaunchFrisbeeSystem()", "Ended.");

		} catch (Exception anEx) {
			this.error("LaunchFrisbeeSystem()", anEx);
		}
	}

	private void initMotor() {

		// setup the ball shooter jaguar and motor.

		this.shootMotor = new Jaguar(
				WkwPrefs.getShooterMotorControllerChannel());

		// LiveWindow.addActuator("ShooterSystem", "shootMotor",
		// this.shootMotor);

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
			this.shootMotorSpeed = LaunchFrisbeeSystem.INITIAL_SHOOTER_MOTOR_SPEED;
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

				if (Math.abs(this.previousShootMotorSpeed
						- this.shootMotor.getSpeed()) > .001) {
					this.previousShootMotorSpeed = this.shootMotor.getSpeed();
					WkwDashboard.setShooterSpeed(this.shootMotor.getSpeed());
				}

			}

			if (Math.abs(this.batteryVoltage
					- DriverStation.getInstance().getBatteryVoltage()) > .01) {
				this.batteryVoltage = DriverStation.getInstance()
						.getBatteryVoltage();
				WkwDashboard.setBatteryVolts(DriverStation.getInstance()
						.getBatteryVoltage());
			}

			// note: not sending setShooterMotorTotalOnTime() here.

			this.calculateTargetCenter();

			// super.updateStatus();

		} catch (Exception anEx) {
			this.error("updateStatus()", anEx);
		}
	}

	private void calculateTargetCenter() {

		// ITable aTable =
		// NetworkTable.getTable(LaunchFrisbeeSystem.NETWORKTABLE_NAME_KINECT_COORDINATES);
		// aTable.retrieveValue(LaunchFrisbeeSystem.NETWORKTABLE_NAME_KINECT_COORDINATES,
		// arg1)
		// double aTopLeftX
		// ITable aTable = NetworkTable
		// .getTable(LaunchFrisbeeSystem.NETWORKTABLE_NAME_KINECT_COORDINATES);

		// if (null != aTable) {

		// this.debug("calculateTargetCenter()", "aTable=" + aTable.toString()
		// + ".");

		// }

		ITable aDashboardTable = NetworkTable.getTable("SmartDashboard");

		if (null != aDashboardTable) {

			Object aDistanceValue = aDashboardTable.getValue("Distance");

			this.debug("calculateTargetCenter()", "aDistanceValue="
					+ aDistanceValue.getClass().getName() + ", aDistanceValue="
					+ aDistanceValue + ".");

			Object aCoordValue = aDashboardTable.getValue("target_coord");

			this.debug("calculateTargetCenter()", "aCoordValue="
					+ aCoordValue.getClass().getName() + ", aCoordValue="
					+ aCoordValue + ".");
			/*
			 * // Object anObject = null; Double[] anObject = null;
			 * aDashboardTable.retrieveValue("target_coord", anObject);
			 * 
			 * this.debug("calculateTargetCenter()", "anObject=" +
			 * anObject.getClass().getName() + ", anObject=" +
			 * anObject.toString() + ".");
			 * 
			 * try {
			 * 
			 * NumberArray aNumberArray = (NumberArray) aCoordValue;
			 * 
			 * this.debug("calculateTargetCenter()", "aNumberArray=" +
			 * aNumberArray.getClass().getName() + ", aNumberArray=" +
			 * aNumberArray + ".");
			 * 
			 * } catch (ClassCastException aCcEx) {
			 * this.error("calculateTargetCenter()", aCcEx); }
			 */
			double aTopLeftX = aDashboardTable.getNumber("tlx");
			double aTopLeftY = aDashboardTable.getNumber("tly");
			double aTopRightX = aDashboardTable.getNumber("trx");
			double aTopRightY = aDashboardTable.getNumber("try");

			double aBottomLeftX = aDashboardTable.getNumber("blx");
			double aBottomLeftY = aDashboardTable.getNumber("bly");
			double aBottomRightX = aDashboardTable.getNumber("brx");
			double aBottomRightY = aDashboardTable.getNumber("bry");

			this.debug("calculateTargetCenter()", "aTopLeftX=" + aTopLeftX
					+ ", aTopLeftY=" + aTopLeftY + ".");
			this.debug("calculateTargetCenter()", "aTopRightX=" + aTopRightX
					+ ", aBottomLeftY=" + aBottomLeftY + ".");
			this.debug("calculateTargetCenter()", "aBottomLeftX="
					+ aBottomLeftX + ", aTopLeftX=" + aTopLeftX + ".");
			this.debug("calculateTargetCenter()", "aBottomRightX="
					+ aBottomRightX + ", aBottomRightY=" + aBottomRightY + ".");

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

				WkwDashboard
						.setShooterMotorTotalOnTime(this.shootMotorTotalOnTime);

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

			this.debug("setShootMotorSpeed()", "shootMotorSpeed="
					+ this.shootMotorSpeed + ".");

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
	 * Convenience method to increase the motor speed. This call the
	 * setShootMotorSpeed() method.
	 * 
	 * @param pSpeed
	 *            Offset from current speed.
	 */
	public void increaseMotorSpeed(final double pSpeed) {
		this.setShootMotorSpeed(this.shootMotorSpeed - pSpeed);
	}

	/**
	 * Convenience method to decrease the motor speed. This call the
	 * setShootMotorSpeed() method.
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

	public long getShootMotorTotalOnTime() {
		return this.shootMotorTotalOnTime;
	}

}
