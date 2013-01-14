/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to extend the beach board system.
 */
public class BeachingExtendCmd extends BeachingBaseCmd {

	// int startDelay;

	public BeachingExtendCmd() {
		super("BeachingExtendCmd");
		this.requires(this.getBeachingSystem());
	}

	protected void initialize() {

		try {

			// this.startDelay = 15;

			this.debug("initialize()", "Called.");
			this.getBeachingSystem().extendInit();

		} catch (Exception anEx) {
			this.error("initialize()", anEx);
		}
	}

	protected void execute() {
		try {

			this.getBeachingSystem().extend();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {

		// this.debug("isFinished()", "isExtended= " + isExtended + ", isFinished=" + isFinished +
		// ".");

		return this.getBeachingSystem().isExtended();// && (this.startDelay-- <= 0);
	}

}
