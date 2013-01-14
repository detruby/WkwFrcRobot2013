/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to fire shooter plunger.
 */
public class ShooterFirePlungerCmd extends CommandWithTimeout {

	public ShooterFirePlungerCmd() {
		super("ShooterFirePlungerCmd", 4.000000);
		this.requires(this.getShooterSystem());
	}

	protected void initialize() {
		this.debug("initialize()", "Called.");
		this.getShooterSystem().initFiringCycle();
		this.getShooterSystem().startPlungerMotor();
	}

	protected void execute() {

		try {
			this.debug("execute()", "firing the plunger");
			this.getShooterSystem().startPlungerMotor();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		boolean isFinished = this.getShooterSystem().isStopMotor();

		if (isFinished) {

			this.debug("isFinished()", "isFinished.");

			this.getShooterSystem().stopPlungerMotor();

		} else if (super.isFinished()) {

			this.debug("isFinished()", "Shoot fire plunger command ");

			this.debug("isFinished()", "Timed out before completing rotation.");
			isFinished = true;
			this.getShooterSystem().stopPlungerMotor();

		}

		return isFinished;
	}

}
