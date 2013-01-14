package us.oh.k12.wkw.robot.command;

public class AutonomousCommandFeedBalls extends CommandGroupBase {

	public AutonomousCommandFeedBalls() {

		this.addSequential(new PrintMessageCmd("Turning on the gathering System in reverse"));
		this.addSequential(new GatheringSystemReverse());
		//this.addSequential(new PrintMessageCmd(
		//		"Turing off the gathering system to refresh the motor."));
		//this.addSequential(new GathererTurnOffCmd());
		//this.addSequential(new PrintMessageCmd(
		//		"Autonomously turning on the gathering system forward"));
		//this.addSequential(new GathererTurnOnCmd());
		//this.addSequential(new PrintMessageCmd("AutonomousCommandWith188 - Moving Backward."));
		//this.addSequential(new AutonomousCommandMoveBackward());
	}

}
