/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

/**
 * base class for beaching commands.
 */
public abstract class BeachingBaseCmd extends CommandBase {

	/**
	 * default constructor.
	 */
	public BeachingBaseCmd() {
		this("BeachingBaseCmd");
	}

	public BeachingBaseCmd(final String pName) {
		super(pName);
		this.requires(this.getBeachingSystem());
	}

}
