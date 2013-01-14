/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Find the target from the camera.
 */
public class CameraFindTargetCmd extends CommandWithTimeout {

	public CameraFindTargetCmd() {
		this(10.0);
	}

	public CameraFindTargetCmd(final double pTimeout) {
		super("CameraFindTargetCmd", pTimeout);
		this.requires(this.getCameraSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			// this.getCameraSystem().findTarget();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	protected boolean isFinished() {
		return true;
	}

}
