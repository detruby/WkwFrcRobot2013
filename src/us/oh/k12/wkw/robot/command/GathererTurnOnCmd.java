/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.StartCommand;

/**
 * Command to turn on the gather sweeper/conveyer.
 */
public class GathererTurnOnCmd extends CommandBase {

	public GathererTurnOnCmd() {
		super("GathererTurnOnCmd");
		this.requires(this.getGathererSystem());
	}

	protected void initialize() {

		try {

			// this.debug("initialize()", "Called.");
			this.getGathererSystem().turnOn();

		} catch (Exception anEx) {
			this.error("initialize()", anEx);
		}
	}

	protected void execute() {

		try {

			this.debug("execute()", "Called.");
			this.getGathererSystem().turnOn();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}

	}

	public boolean isFinished() {
		final boolean isBothBallsPresent = this.getGathererSystem().isBothBallsPresent();

		if (isBothBallsPresent) {

			this.getGathererSystem().turnOff();
			final Command aCommand = new StartCommand(new GathererTurnOnWhenNoBallPresentCmd());
			this.debug("isFinished()", "Starting GathererTurnOnWhenNoBallPresentCmd command.");
			aCommand.start();

		}

		return isBothBallsPresent;
	}

}
