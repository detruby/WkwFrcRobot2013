/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package us.oh.k12.wkw.robot.command;

/**
 * Command to reverse the gathering systems motor.
 */
public class GathererBackwardCmd extends CommandWithTimeout {

	public GathererBackwardCmd() {
		this(3.0);
	}

	public GathererBackwardCmd(final double pTimeout) {
		super("GathererBackwardCmd", pTimeout);
		this.requires(this.getGathererSystem());
	}

	protected void execute() {
		try {

			this.debug("execute()", "Called.");
			this.getGathererSystem().turnOnBackward();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		return true;
	}

}
