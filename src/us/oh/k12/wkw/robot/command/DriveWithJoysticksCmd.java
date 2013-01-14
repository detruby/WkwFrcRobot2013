/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to drive with both joysticks.
 */
public class DriveWithJoysticksCmd extends CommandBase {

	public DriveWithJoysticksCmd() {
		super("DriveWithJoysticksCmd");
		this.requires(this.getDriveSystem());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		try {

			this.getDriveSystem().driveWithJoysticks(this.getOI().getLeftJoystick(),
					this.getOI().getRightJoystick());

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	// drive with joystick never ends
	protected boolean isFinished() {
		return false;
	}

}
