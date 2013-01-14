/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to turn the right drive off.
 */
public class DriveTurnRightOffCmd extends CommandBase {

	protected DriveTurnRightOffCmd() {
		super("DriveTurnRightOffCmd");
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getDriveSystem().rightOff();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
