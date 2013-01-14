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
public class CameraTiltByCmd extends CommandBase {

	double angle;

	protected CameraTiltByCmd() {
		this(10);
	}

	public CameraTiltByCmd(final double pAngle) {
		super("CameraTiltByCmd");
		this.angle = pAngle;
		this.requires(this.getCameraSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getCameraSystem().tiltBy(this.angle);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
