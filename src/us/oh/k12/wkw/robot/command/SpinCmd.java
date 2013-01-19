/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

/*Mitch Domecq was here*/

package us.oh.k12.wkw.robot.command;

/**
 * command to spin 360 degrees.
 */
public class SpinCmd extends CommandGroupBase {

	public SpinCmd() {

		super("SpinCmd");

		try {

			this.debug("SpinCmd()", "Called.");

			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveTurnCmd(1));
			this.addSequential(new DriveTurnCmd(1));

		} catch (Exception anEx) {
			this.error("SpinCmd()", anEx);
		}
	}

}
