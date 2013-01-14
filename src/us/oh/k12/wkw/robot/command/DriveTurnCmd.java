/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to make a 90 degree left turn.
 */
public class DriveTurnCmd extends CommandWithTimeout {

	protected DriveTurnCmd() {
		this(1.0);
	}

	/**
	 * @param pName
	 */
	public DriveTurnCmd(final double pTimeout) {
		super("DriveTurnCmd", pTimeout);
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getDriveSystem().turnLeft90();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
