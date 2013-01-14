/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to decrease the shooter motor speed by x.
 */
public class ShooterDecreaseMotorSpeedByCmd extends CommandWithTimeout {

	final double speed;

	public ShooterDecreaseMotorSpeedByCmd() {
		this(0.5, 0.01);
	}

	public ShooterDecreaseMotorSpeedByCmd(final double pSpeed) {
		this(0.5, pSpeed);
	}

	public ShooterDecreaseMotorSpeedByCmd(final double pTimeout, final double pSpeed) {
		super("ShooterDecreaseMotorSpeedByCmd", pTimeout);
		this.speed = pSpeed;
		this.requires(this.getShooterSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getShooterSystem().decreaseMotorSpeed(this.speed);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		return true;
	}

}
