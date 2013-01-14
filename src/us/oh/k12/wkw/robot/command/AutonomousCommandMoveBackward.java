package us.oh.k12.wkw.robot.command;

public class AutonomousCommandMoveBackward extends CommandWithTimeout {

	public AutonomousCommandMoveBackward() {
		super("AutonomousCommandMoveForward", 5);
		this.requires(this.getDriveSystem());
		this.debug("AutonomousCommandMoveForward()", "Called, timeout = " + 5 + ".");
	}

	protected void initialize() {

		try {

			super.initialize();

			this.debug("initialize()", "Called.");
			this.getDriveSystem().autonomousStraightBackward();

		} catch (Exception anEx) {
			this.error("initialize()", anEx);
		}
	}

	protected void execute() {

		try {

			this.getDriveSystem().autonomousStraight();

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

	protected boolean isFinished() {
		boolean isFinished = this.isTimedOut();
		this.debug("isFinished()", "isFinished=" + isFinished + ".");
		return isFinished;
	}
}