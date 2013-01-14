/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to drive the robot with the operator joy sticks.
 */
public class DriveWithJoystickCmd extends CommandBase {

	public DriveWithJoystickCmd() {
		super();
		this.requires(this.getDriveSystem());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		try {

			this.getDriveSystem().driveWithJoystick(this.getOI().getLeftJoystick());

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	// drive with joystick never ends
	protected boolean isFinished() {
		return false;
	}

}
