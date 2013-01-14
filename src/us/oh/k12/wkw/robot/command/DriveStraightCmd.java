/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to drive in a straight line.
 */
public class DriveStraightCmd extends CommandWithTimeout {

	protected DriveStraightCmd() {
		this(1.0);
	}

	public DriveStraightCmd(final double pTimeout) {
		super("DriveStraightCmd", pTimeout);
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getDriveSystem().straight();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
