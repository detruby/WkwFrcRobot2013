/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved.  */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * self test drive sub system.
 */
public class DriveSelfTestCmd extends CommandBase {

	public DriveSelfTestCmd() {
		super("DriveSelfTestCmd");
		this.debug("DriveSelfTestCmd()", "Called.");
		this.requires(this.getDriveSystem());
	}

	/**
	 * @param pName
	 */
	public DriveSelfTestCmd(final String pName) {
		super(pName);
		this.debug("DriveSelfTestCmd()", "Called.");
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Started.");

			boolean isOk = this.getDriveSystem().selfTestInInitalState();

			this.debug("execute()", "Ended, isOk=" + isOk + ".");

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
