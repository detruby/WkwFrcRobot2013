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
 * Command to watch for one of the switches to not have a ball present, then start the gatherer turn
 * on command.
 */
public class GathererTurnOnWhenNoBallPresentCmd extends CommandBase {

	public GathererTurnOnWhenNoBallPresentCmd() {
		super("GathererTurnOnWhenNoBallPresentCmd");
		this.requires(this.getGathererSystem());
	}

	protected void execute() {

		try {

			// this.debug("execute()", "Called.");

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	public boolean isFinished() {

		final boolean aCanStartMotor = !this.getGathererSystem().isBothBallsPresent();

		if (aCanStartMotor) {

			final Command aCommand = new StartCommand(new GathererTurnOnCmd());
			this.debug("isFinished()", "Starting GathererTurnOnCmd command.");
			aCommand.start();

		}

		return aCanStartMotor;
	}
}
