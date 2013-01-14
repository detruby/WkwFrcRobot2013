/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * 
 */
public class BeachingStartMotorCmd extends CommandBase {

	private boolean extend = false;

	public BeachingStartMotorCmd(final boolean pExtend) {
		super("BeachingStartMotorCmd");
		this.extend = pExtend;
		this.requires(this.getBeachingSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			if (this.extend) {
				this.getBeachingSystem().extend();
			} else {
				this.getBeachingSystem().retract();
			}

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	protected boolean isFinished() {
		return false;
	}

}
