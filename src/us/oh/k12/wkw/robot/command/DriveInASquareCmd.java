/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to drive in a square.
 */
public class DriveInASquareCmd extends CommandGroupBase {

	public DriveInASquareCmd() {

		super("DriveInASquareCmd");

		try {

			this.debug("DriveInASquareCmd()", "Called.");

			this.addSequential(new DriveStraightCmd(1));
			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveStraightCmd(1));
			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveStraightCmd(1));
			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveStraightCmd(1));

		} catch (Exception anEx) {
			this.error("DriveInASquareCmd()", anEx);
		}
	}

}
