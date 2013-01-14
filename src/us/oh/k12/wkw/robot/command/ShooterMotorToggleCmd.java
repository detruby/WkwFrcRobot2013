package us.oh.k12.wkw.robot.command;

import us.oh.k12.wkw.robot.subsystem.ShooterSystem;

public class ShooterMotorToggleCmd extends CommandBase {

	public ShooterMotorToggleCmd() {
		super("ShooterMotorToggleCmd");
		this.requires(this.getShooterSystem());
	}

	protected void execute() {

		final ShooterSystem aShooterSystem = this.getShooterSystem();

		final boolean isOn = aShooterSystem.isShootMotorOn();

		// this.debug("execute()", "isOn=" + isOn + ".");

		if (isOn) {

			// this.debug("execute()", "Calling off.");
			// motor is off, turn it on
			this.getShooterSystem().turnOffShootMotor();

		} else {

			// this.debug("execute()", "Calling on.");
			// motor is on, turn it off
			this.getShooterSystem().turnOnShootMotor();

		}
	}

	protected boolean isFinished() {
		return true;
	}

}
