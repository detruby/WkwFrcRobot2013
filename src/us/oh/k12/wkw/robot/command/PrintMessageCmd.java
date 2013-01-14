/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * command to print a message to the log.
 */
public class PrintMessageCmd extends CommandBase {

	private String message;

	public PrintMessageCmd(final String pMessage) {
		super("PrintMessageCmd");
		this.message = pMessage;
	}

	protected void execute() {
		this.debug("execute()", this.message);
	}

	protected boolean isFinished() {
		return true;
	}

}
