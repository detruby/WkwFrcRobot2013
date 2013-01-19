/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved.  */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * 
 */
public class AutonomousCameraTrackTarget extends CommandWithTimeout {

	/**
	 * 
	 */
	public AutonomousCameraTrackTarget() {
		super("AutonomousCameraTrackTargetCmd", 30.000000);
	//	this.requires(this.getCameraSystem());
	}

	protected void initialize() {

		this.debug("initialize()", "Called.");

		this.getCameraSystem().startFindingTargets();

	}

	protected void execute() {
		// nothing
	}

	protected boolean isFinished() {
		boolean isFinnished = super.isFinished();

		if (isFinnished) {
			this.getCameraSystem().startFindingTargets();
		}

		return isFinnished;
	}

}
