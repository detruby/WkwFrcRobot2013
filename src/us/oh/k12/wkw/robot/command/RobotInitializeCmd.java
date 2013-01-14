/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Command to reset/initialize all systems.
 */
public class RobotInitializeCmd extends CommandGroupBase {

	public RobotInitializeCmd() {

		super("RobotInitializeCmd");

		try {

			this.debug("RobotInitializeCmd()", "Started.");

			// retract the beach.
			this.addParallel(new BeachingRetractCmd());

			// turn gatherer off.
			this.addParallel(new GathererTurnOffCmd());

			// turn off the shooter motor.
			this.addParallel(new ShooterTurnMotorOffCmd());

			// make sure shooter plunger is rotated to the ready/start position.
			this.addParallel(new ShooterFirePlungerCmd());

			this.debug("RobotInitializeCmd()", "Ended.");

		} catch (Exception anEx) {
			this.error("RobotInitializeCmd()", anEx);
		}

	}

}
