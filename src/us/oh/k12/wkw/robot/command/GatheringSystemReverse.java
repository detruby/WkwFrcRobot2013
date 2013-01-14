package us.oh.k12.wkw.robot.command;

public class GatheringSystemReverse extends CommandWithTimeout {
	public GatheringSystemReverse() {
		super("GatheringSystemReverse", 12);
	}

	protected void initialize() {
		this.getGathererSystem().turnOnBackward();
	}

	protected void execute() {
		this.getGathererSystem().turnOnBackward();

	}

}
