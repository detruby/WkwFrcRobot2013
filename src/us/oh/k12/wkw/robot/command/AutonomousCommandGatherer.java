package us.oh.k12.wkw.robot.command;

public class AutonomousCommandGatherer extends CommandGroupBase {
	public AutonomousCommandGatherer() {
		super("AutonomousCommandGatherer");
		try {
			this.addSequential(new BeachingExtendCmd());
			// we might need to cause this to last longer or run shorter depending on the timeout
			this.addSequential(new GathererTurnOnCmd());
		} catch (Exception anEx) {

		}
	}
}
