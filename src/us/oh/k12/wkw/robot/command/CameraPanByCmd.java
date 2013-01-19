/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * 
 */
public class CameraPanByCmd extends CommandBase {

	double angle;

	protected CameraPanByCmd() {
		this(10);
	}

	public CameraPanByCmd(final double pAngle) {
		super("CameraPanByCmd");
		this.angle = pAngle;
//		this.requires(this.getCameraSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getCameraSystem().panBy(this.angle);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
