/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to rotate camera servo to point back to the gatherer.
 */
public class CameraRotateToGatherer extends CommandBase {

	public CameraRotateToGatherer() {
		super("CameraRotateToGatherer");
	//	this.requires(this.getCameraSystem());
	}

	protected void execute() {
		try {

			this.debug("execute()", "Called.");
			this.getCameraSystem().panTo(160.000000);
			this.getCameraSystem().tiltTo(90);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		return true;
	}

}
