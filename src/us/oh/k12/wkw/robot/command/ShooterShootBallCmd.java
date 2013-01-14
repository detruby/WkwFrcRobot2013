/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * 
 */
public class ShooterShootBallCmd extends CommandGroupBase {

	public ShooterShootBallCmd() {

		super("ShooterShootBallCmd");

		try {

			// this.debug("ShooterShootBallCmd()", "Started.");

			this.addSequential(new PrintMessageCmd("Turning on shooter motor."));
			this.addSequential(new ShooterTurnMotorOnCmd());

			this.addSequential(new PrintMessageCmd(
					"Pausing 1 second for motor to spin up to speed."));
			this.addSequential(new WaitCommand(1.5));

			// this.debug("ShooterShootBallCmd()", "Waiting to fire");

			this.addSequential(new PrintMessageCmd("Firing plunger."));
			this.addSequential(new ShooterFirePlungerCmd());

			this.addSequential(new PrintMessageCmd("Turning off shooter motor."));
			this.addSequential(new ShooterTurnMotorOffCmd());

			this.addSequential(new PrintMessageCmd("Pausing .1 second for ball to leave shooter."));
			// this.addSequential(new WaitCommand(0.100000));

			// this.debug("ShooterShootBallCmd()", "Ended.");

		} catch (Exception anEx) {
			this.error("ShooterShootBallCmd()", anEx);
		}

	}

}
