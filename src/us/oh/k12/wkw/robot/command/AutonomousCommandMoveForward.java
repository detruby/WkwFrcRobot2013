package us.oh.k12.wkw.robot.command;

public class AutonomousCommandMoveForward extends CommandWithTimeout {

	private final static double TIMEOUT = 7.0;

	public AutonomousCommandMoveForward() {
		super("AutonomousCommandMoveForward", AutonomousCommandMoveForward.TIMEOUT);
		this.requires(this.getDriveSystem());
		this.debug("AutonomousCommandMoveForward()", "Called, timeout="
				+ AutonomousCommandMoveForward.TIMEOUT + ".");
	}

	protected void initialize() {

		try {

			super.initialize();

			this.debug("initialize()", "Called.");
			this.getDriveSystem().autonomousStraight();

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
