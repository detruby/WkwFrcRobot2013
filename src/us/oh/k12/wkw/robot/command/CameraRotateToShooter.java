/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to rotate camera servo forward to shooter.
 */
public class CameraRotateToShooter extends CommandBase {
	public CameraRotateToShooter() {
		super("CameraRotateToShooter");
		//this.requires(this.getCameraSystem());
	}

	protected void execute() {
		try {

			this.debug("execute()", "Called.");
			this.getCameraSystem().panTo(0.000000);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		return true;
	}
}
