/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

import us.oh.k12.wkw.robot.operator.OperatorInterface;
import us.oh.k12.wkw.robot.subsystem.BeachingSystem;
import us.oh.k12.wkw.robot.subsystem.CameraSystem;
import us.oh.k12.wkw.robot.subsystem.DriveSystem;
import us.oh.k12.wkw.robot.subsystem.GathererSystem;
import us.oh.k12.wkw.robot.subsystem.LaunchFrisbeeSystem;
import us.oh.k12.wkw.robot.subsystem.ShooterSystem;
import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwFrcLogger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase creates and stores each control system.
 */
public abstract class CommandBase extends Command {

	// operator interface
	// NOTE: this is initialized in the init() method below.
	// and NOT here.
	private static OperatorInterface oi;

	// Create a single static instance of all of your subsystems

	// drive system
	// NOTE: this system must be initialized first because it checks all sensors
	// - and we need to do
	// this before the other systems allocate (lock) them.
	private static DriveSystem driveSystem = new DriveSystem();

	private static LaunchFrisbeeSystem launchSystem = new LaunchFrisbeeSystem();

	// beaching system
	// private static BeachingSystem beachingSystem = new BeachingSystem();

	// gatherer system
	// private static GathererSystem gathererSystem = new GathererSystem();

	// ball shooter system
	// private static ShooterSystem shooterSystem = new ShooterSystem();

	// camera system
	// private static CameraSystem cameraSystem = new CameraSystem();

	public static void init() {
		// This MUST be here. If the OI creates Commands (which it very likely
		// will), constructing it during the construction of CommandBase (from
		// which commands extend), subsystems are not guaranteed to be
		// yet. Thus, their requires() statements may grab null pointers. Bad
		// news. Don't move it.
		CommandBase.oi = new OperatorInterface();

		// Show what command your subsystem is running on the SmartDashboard
		WkwDashboard.putData(CommandBase.driveSystem);
		WkwDashboard.putData(CommandBase.launchSystem);
		// WkwDashboard.putData(CommandBase.beachingSystem);
		// WkwDashboard.putData(CommandBase.gathererSystem);
		// WkwDashboard.putData(CommandBase.shooterSystem);
		// WkwDashboard.putData(CommandBase.cameraSystem);

	}

	public static void updateStatus() {
		try {

			CommandBase.driveSystem.updateStatus();
			CommandBase.launchSystem.updateStatus();
			// CommandBase.beachingSystem.updateStatus();
			// CommandBase.gathererSystem.updateStatus();
			// CommandBase.shooterSystem.updateStatus();
			// CommandBase.cameraSystem.updateStatus();

		} catch (Exception anEx) {
			WkwFrcLogger.error(CommandBase.class.getName(), "updateStatus()",
					"Exception name=" + anEx.getClass().getName()
							+ ", message=" + anEx.getMessage() + ".", anEx);
		}
	}

	protected CommandBase() {
		super();
	}

	protected CommandBase(final String pName) {
		super(pName);
	}

	protected void initialize() {
		// no implementation
	}

	protected boolean isFinished() {
		return true; // the default implementation if to end the command
						// immediately.
	}

	protected void end() {
		// no implementation
	}

	protected void interrupted() {
		// no implementation
	}

	protected OperatorInterface getOI() {
		return CommandBase.oi;
	}

	protected BeachingSystem getBeachingSystem() {
		return null; // CommandBase.beachingSystem;
	}

	protected DriveSystem getDriveSystem() {
		return CommandBase.driveSystem;
	}

	protected LaunchFrisbeeSystem getLaunchSystem() {
		return CommandBase.launchSystem;
	}

	protected GathererSystem getGathererSystem() {
		return null; // CommandBase.gathererSystem;
	}

	protected ShooterSystem getShooterSystem() {
		return null; // CommandBase.shooterSystem;
	}

	protected CameraSystem getCameraSystem() {
		return null; // CommandBase.cameraSystem;
	}

	protected boolean isTimedOut() {
		boolean isTimedOut = super.isTimedOut();
		if (isTimedOut) {
			this.reportCommandTimeout();
		}
		return isTimedOut;
	}

	/*
	 * 
	 * support methods.
	 */

	protected void debug(final String pMethod, final String pMessage) {
		WkwFrcLogger.debug(this.getClassName(), pMethod, pMessage);
	}

	protected void info(final String pMethod, final String pMessage) {
		WkwFrcLogger.info(this.getClassName(), pMethod, pMessage);
	}

	protected void reportCommandTimeout() {
		WkwFrcLogger
				.info(this.getClassName(),
						"reportCommandTimeout()",
						" System:"
								+ this.getName()
								+ ", Sensor:N/A, State:N/A, Passed:false, Message:Command timed out.");
	}

	private String formatException(final Exception pEx) {
		return (null == pEx ? "." : pEx.getClass().getName() + ", message="
				+ pEx.getMessage() + ".");
	}

	protected void error(final String pMethod, final Exception pEx) {
		this.error(pMethod, this.formatException(pEx), pEx);
	}

	protected void error(final String pMethod, final String pMessage,
			final Exception pEx) {
		WkwFrcLogger.error(this.getClassName(), pMethod, pMessage, pEx);
		if (null != pEx) {
			pEx.printStackTrace();
		}
	}

	protected String getClassName() {
		String aClassName = this.getClass().getName();
		return aClassName.substring(aClassName.lastIndexOf('.') + 1);
	}

}
