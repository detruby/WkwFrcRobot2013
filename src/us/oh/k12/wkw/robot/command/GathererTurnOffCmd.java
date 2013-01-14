/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to turn off the gatherer sweeper/conveyer.
 */
public class GathererTurnOffCmd extends CommandBase {

	public GathererTurnOffCmd() {
		super("GathererTurnOffCmd");
		this.requires(this.getGathererSystem());
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getGathererSystem().turnOff();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
