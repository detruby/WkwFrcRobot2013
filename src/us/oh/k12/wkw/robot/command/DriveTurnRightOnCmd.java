/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to turn the right drive on.
 */
public class DriveTurnRightOnCmd extends CommandBase {

	private double speed;

	protected DriveTurnRightOnCmd() {
		this(1.0);
	}

	public DriveTurnRightOnCmd(final double pSpeed) {
		super("DriveTurnRightOnCmd");
		this.speed = pSpeed;
		this.requires(this.getDriveSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getDriveSystem().rightOn(this.speed);

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
