package us.oh.k12.wkw.robot.command;


public class AutonomousCommandShooter extends CommandGroupBase {

	public AutonomousCommandShooter() {
		super("AutonomousCommandShooter");

		try {

			// this.debug("AutonomousCommandRun()", "Called.");
			// this.addSequential(new
			// PrintMessageCmd("AutonomousCommandShooter - Turning on gatherer."));
			// this.addSequential(new GathererTurnOnCmd());
			this.addSequential(new ShooterShootBallCmd());
			this.addSequential(new AutonomousGathererOn());
			this.addSequential(new ShooterShootBallCmd());

			// this.addSequential(new PrintMessageCmd("Starting shooting"));
			// this.addSequential(new GathererTurnOnCmd());
			// this.addParallel(new BeachingExtendCmd());
			// this.addSequential(new ShooterShootBallCmd());
			// this.addSequential(new
			// PrintMessageCmd("AutonomousCommandShooter - Extending Beach."));
			// this.addSequential(new BeachingExtendCmd());
			// this.addSequential(new
			// PrintMessageCmd("AutonomousCommandShooter - Moving forward."));
			// this.addSequential(new AutonomousCommandMoveForward());
			// this.addSequential(new PrintMessageCmd("AutonomousCommandShooter - Ended."));

		} catch (Exception anEx) {

			this.error("AutonomousCommandShooter()", anEx);

		}
	}
}
