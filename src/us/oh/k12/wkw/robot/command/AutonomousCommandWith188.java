package us.oh.k12.wkw.robot.command;

public class AutonomousCommandWith188 extends CommandGroupBase {
	public AutonomousCommandWith188() {
		super("AutonomousCommandWith188");
		// this.addSequential(new WaitCommand(4));
		this.addSequential(new PrintMessageCmd("Turning on the gathering System in reverse"));
		this.addSequential(new GatheringSystemReverse());
		this.addSequential(new PrintMessageCmd(
				"Turing off the gathering system to refresh the motor."));
		this.addSequential(new GathererTurnOffCmd());
		this.addSequential(new PrintMessageCmd(
				"Autonomously turning on the gathering system forawrd"));
		this.addSequential(new GathererTurnOnCmd());
		this.addSequential(new PrintMessageCmd("AutonomousCommandWith188 - Moving Backward."));
		this.addSequential(new AutonomousCommandMoveBackward());
		// this.addSequential(new PrintMessageCmd("AutonomousCommandWith188 - Ended."));
	}
}
