/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to stop the shooter plunger motor.
 */
public class ShooterStopPlungerMotorCmd extends CommandBase {

	public ShooterStopPlungerMotorCmd() {
		super("ShooterStopPlungerMotorCmd");
		this.requires(this.getShooterSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getShooterSystem().stopPlungerMotor();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	protected boolean isFinished() {
		return true;
	}

}
