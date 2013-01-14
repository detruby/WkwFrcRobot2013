/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to start the shooter plunger motor.
 */
public class ShooterStartPlungerMotorCmd extends CommandBase {

	public ShooterStartPlungerMotorCmd() {
		super("ShooterStartPlungerMotorCmd");
		this.requires(this.getShooterSystem());
	}

	protected void initialize() {
		this.debug("initialize()", "Called.");
		this.getShooterSystem().initFiringCycle();
		this.getShooterSystem().startPlungerMotor();
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getShooterSystem().startPlungerMotor();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	protected boolean isFinished() {
		return true;
	}

}
