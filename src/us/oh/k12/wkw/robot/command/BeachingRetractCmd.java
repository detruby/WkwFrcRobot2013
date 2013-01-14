/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to retract the beach board system.
 */
public class BeachingRetractCmd extends BeachingBaseCmd {

	// doing this because retract motor start
	// and then check for switch is too quick
	// and switch has not changed yet.
	// int startDelay;

	public BeachingRetractCmd() {
		super("BeachingRetractCmd");
		this.requires(this.getBeachingSystem());
	}

	protected void initialize() {

		try {

			// this.startDelay = 15;

			this.debug("initialize()", "Called.");
			this.getBeachingSystem().retractInit();

		} catch (Exception anEx) {
			this.error("initialize()", anEx);
		}

	}

	protected void execute() {
		try {

			// this.debug("execute()", "Called.");
			this.getBeachingSystem().retract();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		return this.getBeachingSystem().isRetracted();// && (this.startDelay-- <= 0);
	}

}
