package us.oh.k12.wkw.robot.command;

public class AutonomousGathererOn extends CommandBase {

	protected void initialize() {
		this.getGathererSystem().turnOn();

	}

	protected void execute() {
		this.getGathererSystem().turnOn();
	}

	protected boolean isFinished() {
		if (this.getGathererSystem().isShooterBallPresent()) {
			return true;
		}
		return false;
	}

}
