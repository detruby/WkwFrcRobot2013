package us.oh.k12.wkw.robot.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandMoveToBridge extends CommandGroup {
	public AutonomousCommandMoveToBridge() {
		this.addSequential(new BeachingExtendCmd());
		this.addSequential(new AutonomousCommandMoveForward());
	}

}
