/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * Base class to run a command with a timeout.
 */
public abstract class CommandWithTimeout extends CommandBase {

	private double timeout;

	protected CommandWithTimeout() {
		this("None", 1.0);
	}

	/**
	 * @param pName
	 */
	protected CommandWithTimeout(final String pName, final double pTimeout) {
		super(pName);
		this.timeout = pTimeout;
	}

	protected void initialize() {
		this.setTimeout(this.timeout);
	}

	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
