/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * rotate robot (drive) until camera finds target then shoot ball.
 */
public class AutonomousFindAndShootCmd extends CommandWithTimeout {

	public AutonomousFindAndShootCmd() {
		super("AutonomousFindAndShootCmd", 30.000000);
	//	this.requires(this.getCameraSystem());
		this.requires(this.getShooterSystem());
		this.requires(this.getDriveSystem());
	}

	protected void initialize() {

		this.debug("initialize()", "Called.");

		this.getCameraSystem().panTo(90);
		this.getDriveSystem().rotate();

	}

	protected void execute() {

		try {

			// this.debug("execute()", "Called.");

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	protected boolean isFinished() {
		boolean isFinnished = this.getCameraSystem().isFoundTarget() || super.isFinished();

		if (isFinnished) {
			this.getDriveSystem().stop();
		}

		return isFinnished;
	}

}
