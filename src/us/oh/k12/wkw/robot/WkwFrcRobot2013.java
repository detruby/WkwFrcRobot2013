/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot;

import java.util.Date;

import us.oh.k12.wkw.robot.command.AutonomousCommandShooter;
import us.oh.k12.wkw.robot.command.CommandBase;
import us.oh.k12.wkw.robot.util.SimpleDateFormat;
import us.oh.k12.wkw.robot.util.WkwDashboard;
import us.oh.k12.wkw.robot.util.WkwFrcLogger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This is the main robot class. The cRIO VM (operating system) is configured to automatically run
 * this class, and to call the functions corresponding to each mode, as described in the
 * IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class WkwFrcRobot2013 extends IterativeRobot {

	private static final String VERSION = "3.0.0"; // program version

	private Command autonomousCommand;

	private boolean autonomousMode = false;
	private boolean teleopMode = false;
	private boolean testMode = false;

	public WkwFrcRobot2013() {
		super();

	}

	/**
	 * Robot-wide initialization code should go here. Robot-wide initialization which will be called
	 * when the robot is first powered on. It will be called exactly 1 time.
	 */
	public void robotInit() {

		try {

			//
			// initialize the logger - enables viewing of log on a remote computer
			// see project WkwFrcLogClient
			//
			WkwFrcLogger.initialize();

			this.debug("robotInit()", ".");
			this.debug("robotInit()", "Started.");
			this.debug("robotInit()", ".");

			this.debug("robotInit()", "Initializing systems.");

			//
			// Initialize all subsystems
			//
			CommandBase.init();

			this.debug("robotInit()", "Initializing autonomous comand.");

			//
			// instantiate the command used for the autonomous period
			//
			this.autonomousCommand = new AutonomousCommandShooter();
			// this.autonomousCommand = new DoNothingCmd();

			this.initIo();

			//
			// initialize the smart dashboard data
			//
			WkwDashboard.putData(Scheduler.getInstance());

			WkwDashboard.setRobotInitialized(this.getDateTimeFormatted());

			WkwDashboard.setRobotVersion(WkwFrcRobot2013.getVersion());

			this.debug("robotInit()", ".");
			this.debug("robotInit()", "Ended.");
			this.debug("robotInit()", ".");

		} catch (Exception anEx) {
			// catch all exceptions so we can gracefully log
			// and figure out what happened
			// instead of the robot just crashing
			this.error("robotInit()", anEx);
		}

	}

	private void initIo() {

		/*
		this.debug("robotInit()", "DigitalInput kAnalogChannels="
				+ DigitalInput.kAnalogChannels + ".");
		this.debug("robotInit()", "DigitalInput kPwmChannels=" + DigitalInput.kPwmChannels
				+ ".");
		this.debug("robotInit()", "DigitalInput kRelayChannels=" + DigitalInput.kRelayChannels
				+ ".");
		this.debug("robotInit()", "DigitalInput kSolenoidChannels="
				+ DigitalInput.kSolenoidChannels + ".");
		this.debug("robotInit()", "DigitalInput kDigitalChannels="
				+ DigitalInput.kDigitalChannels + ".");
		*/

	}

	/*
	 * 
	 * disabled mode methods.
	 * 
	 */

	/**
	 * Initialization code for disabled mode should go here - initialization code which will be
	 * called each time the robot enters disabled mode.
	 */
	public void disabledInit() {

		try {

			this.debug("disabledInit()", "Started.");

			// reset teleop mode state
			if (this.teleopMode) {
				this.teleopMode = false;
				WkwDashboard.setTeleopStateChange(false);
			}

			// reset autonomous mode state
			if (this.autonomousMode) {
				this.autonomousMode = false;
				WkwDashboard.setAutonomousStateChange(false);
			}

			// reset autonomous mode state
			if (this.testMode) {
				this.testMode = false;
				WkwDashboard.setTestStateChange(false);
			}

			// publish state change
			WkwDashboard.setDisabledStateChange(true);

			// publish data to the dashboard
			this.updateStatus();

			this.delayCounter = WkwFrcRobot2013.DELAY_COUNT_START;

			this.debug("disabledInit()", "Ended.");

		} catch (Exception anEx) {
			this.error("disabledInit()", anEx);
		}
	}

	private int delayCounter = 0;
	private static final int DELAY_COUNT_START = 10;

	/**
	 * Periodic code for disabled mode should go here - will be called periodically at a regular
	 * rate while the robot is in disabled mode.
	 */
	public void disabledPeriodic() {

		try {

			if (this.delayCounter-- < 0) {
				// we don't need to publish often during disabled.
				this.delayCounter = WkwFrcRobot2013.DELAY_COUNT_START;
				// publish data to the dashboard
				this.updateStatus();
			}

		} catch (Exception anEx) {
			this.error("disabledPeriodic()", anEx);
		}
	}

	/**
	 * Continuous code for disabled mode should go here - will be called repeatedly as frequently as
	 * possible while the robot is in disabled mode. In disabled mode - this method is called in a
	 * tight loop.
	 */
	public void disabledContinuous() {

		// no implementation here

	}

	/*
	 * 
	 * autonomous mode methods.
	 * 
	 */

	public void autonomousInit() {

		try {

			this.debug("autonomousInit()", "Started.");

			WkwDashboard.setDisabledStateChange(false);
			// publish state change
			this.autonomousMode = true;
			WkwDashboard.setAutonomousStateChange(false);
			this.teleopMode = false;
			WkwDashboard.setTeleopStateChange(true);
			this.testMode = false;
			WkwDashboard.setTestStateChange(false);

			// schedule the autonomous command
			this.autonomousCommand.start();

			// publish data to the dashboard
			this.updateStatus();

			this.debug("autonomousInit()", "Ended.");

		} catch (Exception anEx) {
			this.error("autonomousInit()", anEx);
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

		try {

			// feed the watchdog timer
			this.getWatchdog().feed();

			// run the command scheduler
			// has five stages:
			// 1. Poll the buttons
			// 2. Execute/Remove the Commands.
			// 3. Send values to SmartDashboard.
			// 4. Add Commands.
			// 5. Add Defaults.
			// TODO: why gets nullpointerexception Scheduler.getInstance().run();

			// publish data to the dashboard
			this.updateStatus();

		} catch (Exception anEx) {
			this.error("autonomousPeriodic()", anEx);
		}
	}

	/**
	 * Continuous code for autonomous mode should go here - will be called repeatedly as frequently
	 * as possible while the robot is in autonomous mode. In autonomous mode - this method is called
	 * in a tight loop.
	 */
	public void autonomousContinuous() {

		// no implementation here

	}

	/*
	 * 
	 * teleop mode methods.
	 * 
	 */

	public void teleopInit() {

		try {

			this.debug("teleopInit()", "Called.");

			// This makes sure that the autonomous stops running when
			// teleop starts running. If you want the autonomous to
			// continue until interrupted by another command, remove
			// this line or comment it out.
			this.autonomousCommand.cancel();

			// publish state change
			WkwDashboard.setDisabledStateChange(false);
			this.autonomousMode = false;
			WkwDashboard.setAutonomousStateChange(false);
			this.teleopMode = true;
			WkwDashboard.setTeleopStateChange(true);
			this.testMode = false;
			WkwDashboard.setTestStateChange(false);

			// publish data to the dashboard
			this.updateStatus();

		} catch (Exception anEx) {
			this.error("teleopInit()", anEx);
		}
	}

	/**
	 * This function is called periodically during telelop (operator) control
	 */
	public void teleopPeriodic() {

		try {

			this.debug("teleopPeriodic()", "Called.");

			// feed the watchdog timer
			this.getWatchdog().feed();

			// run the command scheduler
			// has five stages:
			// 1. Poll the buttons
			// 2. Execute/Remove the Commands.
			// 3. Send values to SmartDashboard.
			// 4. Add Commands.
			// 5. Add Defaults.
			// TODO: why gets nullpointerexception Scheduler.getInstance().run();

			// publish data to the dashboard
			this.updateStatus();

		} catch (Exception anEx) {
			this.error("teleopPeriodic()", anEx);
		}
	}

	/**
	 * Continuous code for teleop mode should go here - will be called repeatedly as frequently as
	 * possible while the robot is in teleop mode. In teleop mode - this method is called in a tight
	 * loop.
	 */
	public void teleopContinuous() {

		// no implementation here

	}

	public void testInit() {

		try {

			this.debug("testInit()", "Called.");

			// publish state change
			WkwDashboard.setDisabledStateChange(false);
			this.autonomousMode = false;
			WkwDashboard.setAutonomousStateChange(false);
			this.teleopMode = false;
			WkwDashboard.setTeleopStateChange(false);
			this.testMode = true;
			WkwDashboard.setTestStateChange(true);

		} catch (Exception anEx) {
			this.error("testInit()", anEx);
		}
	}

	public void testPeriodic() {

		try {

			this.debug("testPeriodic()", "Called.");

			LiveWindow.run();

		} catch (Exception anEx) {
			this.error("testPeriodic()", anEx);
		}
	}

	/*
	 * 
	 * misc methods.
	 * 
	 */

	/**
	 * Get the robot program version.
	 * 
	 * @return version.
	 */
	public static String getVersion() {
		return WkwFrcRobot2013.VERSION;
	}

	/*
	 * 
	 * support methods.
	 * 
	 */

	protected void updateStatus() {

		// have all commands publish data to the dashboard
		CommandBase.updateStatus();

	}

	private void debug(final String pMethod, final String pMessage) {
		WkwFrcLogger.debug(this.getClassName(), pMethod, pMessage);
	}

	protected void info(final String pMethod, final String pMessage) {
		WkwFrcLogger.info(this.getClassName(), pMethod, pMessage);
	}

	private String formatException(final Exception pEx) {
		return (null == pEx ? "." : pEx.getClass().getName() + ", message=" + pEx.getMessage()
				+ ".");
	}

	private void error(final String pMethod, final Exception pEx) {
		this.error(pMethod, this.formatException(pEx), pEx);
	}

	private void error(final String pMethod, final String pMessage, final Exception pEx) {
		WkwFrcLogger.error(this.getClassName(), pMethod, pMessage, pEx);
	}

	private String getDateTimeFormatted() {
		final SimpleDateFormat aformat = new SimpleDateFormat();
		return aformat.format(new Date());
	}

	private String getClassName() {
		String aClassName = this.getClass().getName();
		return aClassName.substring(aClassName.lastIndexOf('.') + 1);
	}

}
