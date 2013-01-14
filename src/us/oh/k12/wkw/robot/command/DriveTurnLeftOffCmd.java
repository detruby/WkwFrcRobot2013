/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to turn the left drive off.
 */
public class DriveTurnLeftOffCmd extends CommandBase {

	protected DriveTurnLeftOffCmd() {
		super("DriveTurnLeftOffCmd");
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getDriveSystem().leftOff();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
