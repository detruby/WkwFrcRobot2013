package us.oh.k12.wkw.robot.command;

import edu.wpi.first.wpilibj.command.Command;

// R=(V^2sin(2x))/g

//V = 2SpiRM/S

public class CalcShooterMotorSpeed extends Command {
	public double initV;
	protected double SHOOTER_ANGLE;
	protected double GRAVITY;
	protected double RADIUS;
	final double range;

	public CalcShooterMotorSpeed(final int pRange) {
		super("CalcShooterMotorSpeed");
		this.range = pRange;
		this.initV = 0;
		this.SHOOTER_ANGLE = Math.toRadians(45);
		this.GRAVITY = 9.80;
		this.RADIUS = 5.5;
	}

	protected void initialize() {

	}

	protected void execute() {
		initV = Math.sqrt((Math.sin(2 * SHOOTER_ANGLE)) / (range * GRAVITY));
		// Math.PI*this.RADIUS*
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
