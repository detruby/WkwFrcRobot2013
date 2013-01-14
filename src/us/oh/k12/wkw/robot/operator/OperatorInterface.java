/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.operator;

import java.util.Date;

import us.oh.k12.wkw.robot.command.BeachingExtendCmd;
import us.oh.k12.wkw.robot.command.BeachingRetractCmd;
import us.oh.k12.wkw.robot.command.BeachingStartMotorCmd;
import us.oh.k12.wkw.robot.command.BeachingStopMotorCmd;
import us.oh.k12.wkw.robot.command.CameraPanByCmd;
import us.oh.k12.wkw.robot.command.CameraRotateToGatherer;
import us.oh.k12.wkw.robot.command.CameraRotateToShooter;
import us.oh.k12.wkw.robot.command.CameraTiltByCmd;
import us.oh.k12.wkw.robot.command.GathererBackwardCmd;
import us.oh.k12.wkw.robot.command.GathererTurnOffCmd;
import us.oh.k12.wkw.robot.command.GathererTurnOnCmd;
import us.oh.k12.wkw.robot.command.RobotInitializeCmd;
import us.oh.k12.wkw.robot.command.ShooterDecreaseMotorSpeedByCmd;
import us.oh.k12.wkw.robot.command.ShooterFirePlungerCmd;
import us.oh.k12.wkw.robot.command.ShooterIncreaseMotorSpeedByCmd;
import us.oh.k12.wkw.robot.command.ShooterMotorToggleCmd;
import us.oh.k12.wkw.robot.command.ShooterShootBallCmd;
import us.oh.k12.wkw.robot.command.ShooterStartPlungerMotorCmd;
import us.oh.k12.wkw.robot.command.ShooterStopPlungerMotorCmd;
import us.oh.k12.wkw.robot.command.ShooterTurnMotorOffCmd;
import us.oh.k12.wkw.robot.command.ShooterTurnMotorOnCmd;
import us.oh.k12.wkw.robot.util.SimpleDateFormat;
import us.oh.k12.wkw.robot.util.WkwFrcLogger;
import us.oh.k12.wkw.robot.util.WkwPrefs;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Represents an operator interface.<br>
 * <br>
 * The joystick buttons:<br
 * 1 = trigger<br>
 * 2 = top rear<br>
 * 3 = top center<br>
 * 4 = top left<br>
 * 5 = top right<br>
 * 6, 7 = base left side<br>
 * 8, 9 - base back side<br>
 * 10, 11 - base right side.
 * 
 */
public class OperatorInterface {

	private Joystick leftJoystick;
	private Joystick rightJoystick;
	private Joystick shooterControler;
	private InternalButton fireButton;
	private InternalButton cameraPanLeftButton;
	private InternalButton cameraPanRightButton;
	private InternalButton cameraTiltUpButton;
	private InternalButton cameraTiltDownButton;
	private InternalButton cameraRotateForwardButton;
	private InternalButton cameraRotateBackwardButton;
	private InternalButton shooterMotorTurnOnButton;
	private InternalButton shooterMotorTurnOffButton;
	private InternalButton gathererMotorTurnOnButton;
	private InternalButton gathererMotorTurnOffButton;
	private InternalButton shooterPlungerTurnOnButton;
	private InternalButton shooterPlungerTurnOffButton;
	private InternalButton beachingMotorExtendButton;
	private InternalButton beachingMotorRetractButton;
	private InternalButton beachingMotorTurnOffButton;

	public OperatorInterface() {

		super();

		try {

			this.setupLeftJoystick();

			this.setupRightJoystick();

			this.setupGamepad();

			this.setupInternalButtons();

		} catch (Exception anEx) {
			this.error("OperatorInterface()", anEx);
		}
	}

	private void setupInternalButtons() {

		try {

			this.fireButton = new InternalButton();
			SmartDashboard.putData("Fire Button", this.fireButton);
			this.fireButton.whenPressed(new ShooterShootBallCmd());

			this.cameraPanLeftButton = new InternalButton();
			SmartDashboard.putData("Camera Pan Left Button", this.cameraPanLeftButton);
			this.cameraPanLeftButton.whenPressed(new CameraPanByCmd(-2.000000));

			this.cameraPanRightButton = new InternalButton();
			SmartDashboard.putData("Camera Pan Right Button", this.cameraPanRightButton);
			this.cameraPanRightButton.whenPressed(new CameraPanByCmd(2.000000));

			this.cameraTiltUpButton = new InternalButton();
			SmartDashboard.putData("Camera Tilt Up Button", this.cameraTiltUpButton);
			this.cameraTiltUpButton.whenPressed(new CameraTiltByCmd(2.000000));

			this.cameraTiltDownButton = new InternalButton();
			SmartDashboard.putData("Camera Tilt Down Button", this.cameraTiltDownButton);
			this.cameraTiltDownButton.whenPressed(new CameraTiltByCmd(-2.000000));

			this.cameraRotateForwardButton = new InternalButton();
			SmartDashboard.putData("Camera Rotate To Shooter Button",
					this.cameraRotateForwardButton);
			this.cameraRotateForwardButton.whenPressed(new CameraRotateToShooter());

			this.cameraRotateBackwardButton = new InternalButton();
			SmartDashboard.putData("Camera Rotate To Gatherer Button",
					this.cameraRotateBackwardButton);
			this.cameraRotateBackwardButton.whenPressed(new CameraRotateToGatherer());

			this.shooterMotorTurnOnButton = new InternalButton();
			SmartDashboard.putData("Shooter Motor Turn On Button", this.shooterMotorTurnOnButton);
			this.shooterMotorTurnOnButton.whenPressed(new ShooterTurnMotorOnCmd());

			this.shooterMotorTurnOffButton = new InternalButton();
			SmartDashboard.putData("Shooter Motor Turn Off Button", this.shooterMotorTurnOffButton);
			this.shooterMotorTurnOffButton.whenPressed(new ShooterTurnMotorOffCmd());

			this.gathererMotorTurnOnButton = new InternalButton();
			SmartDashboard.putData("Gatherer Motor Turn On Button", this.gathererMotorTurnOnButton);
			this.gathererMotorTurnOnButton.whenPressed(new GathererTurnOnCmd());

			this.gathererMotorTurnOffButton = new InternalButton();
			SmartDashboard.putData("Gatherer Motor Turn Off Button",
					this.gathererMotorTurnOffButton);
			this.gathererMotorTurnOffButton.whenPressed(new GathererTurnOffCmd());

			this.shooterPlungerTurnOnButton = new InternalButton();
			SmartDashboard.putData("Shooter Plunger Motor Turn On Button",
					this.shooterPlungerTurnOnButton);
			this.shooterPlungerTurnOnButton.whenPressed(new ShooterStartPlungerMotorCmd());

			this.shooterPlungerTurnOffButton = new InternalButton();
			SmartDashboard.putData("Shooter Plunger Motor Turn Off Button",
					this.shooterPlungerTurnOffButton);
			this.shooterPlungerTurnOffButton.whenPressed(new ShooterStopPlungerMotorCmd());

			this.beachingMotorExtendButton = new InternalButton();
			SmartDashboard.putData("Beaching Motor Start Extend Button",
					this.beachingMotorExtendButton);
			this.beachingMotorExtendButton.whenPressed(new BeachingStartMotorCmd(true));

			this.beachingMotorRetractButton = new InternalButton();
			SmartDashboard.putData("Beaching Motor Start Retract Button",
					this.beachingMotorRetractButton);
			this.beachingMotorRetractButton.whenPressed(new BeachingStartMotorCmd(false));

			this.beachingMotorTurnOffButton = new InternalButton();
			SmartDashboard.putData("Beaching Motor Turn Off Button",
					this.beachingMotorTurnOffButton);
			this.beachingMotorTurnOffButton.whenPressed(new BeachingStopMotorCmd());

		} catch (Exception anEx) {
			this.error("setupInternalButtons()", anEx);
		}
	}

	private void setupGamepad() {

		try {

			this.shooterControler = new Joystick(WkwPrefs.getGamepadPort());

			/*
			 * 
			 * 
			 * Controller
			 * 
			 * 
			 */

			if (null != this.shooterControler) {

				this.debug("setupGamepad()", "Right button 5=Shoot motor toggle");
				new JoystickButton(this.shooterControler, 5)
						.whenPressed(new ShooterMotorToggleCmd());

				this.debug("setupGamepad()", "Right button 6=ShooterShootBallCmd.");
				new JoystickButton(this.shooterControler, 6).whenPressed(new ShooterShootBallCmd());

				this.debug("setupGamepad()", "Right button 7=ShooterDecreaseMotorSpeedByCmd.");
				new JoystickButton(this.shooterControler, 7)
						.whenPressed(new ShooterDecreaseMotorSpeedByCmd(0.010000));

				this.debug("setupGamepad()", "Right button 8=ShooterIncreaseMotorSpeedByCmd.");
				new JoystickButton(this.shooterControler, 8)
						.whenPressed(new ShooterIncreaseMotorSpeedByCmd(0.010000));

			}

		} catch (Exception anEx) {
			this.error("setupGamepad()", anEx);
		}
	}

	private void setupRightJoystick() {

		try {

			this.rightJoystick = new Joystick(WkwPrefs.getJoystickRightPort());

			/*
			 * 
			 * 
			 * right joy stick
			 * 
			 * 
			 */

			if (null != this.rightJoystick) {

				this.debug("setupRightJoystick()", "Right trigger=BeachingExtendCmd.");
				new JoystickButton(this.rightJoystick, 1).whenPressed(new BeachingExtendCmd());

				this.debug("setupRightJoystick", "Right button 2=CameraRotateToGatherer.");
				new JoystickButton(this.rightJoystick, 2).whenPressed(new CameraRotateToGatherer());

				this.debug("setupRightJoystick()", "Right button 3=ShooterStopPlungerMotorCmd +.");
				new JoystickButton(this.rightJoystick, 3)
						.whenPressed(new ShooterStopPlungerMotorCmd());

				this.debug("setupRightJoystick()", "Right button 4=GathererBackwardCmd +.");
				new JoystickButton(this.rightJoystick, 4).whenPressed(new GathererBackwardCmd());

				this.debug("setupRightJoystick()", "Right button 5=BeachingStopMotorCmd +.");
				new JoystickButton(this.rightJoystick, 5).whenPressed(new BeachingStopMotorCmd());

				this.debug("setupRightJoystick()", "Right button 6=CameraPanByCmd +.");
				new JoystickButton(this.rightJoystick, 6).whenPressed(new CameraPanByCmd(1));

				this.debug("setupRightJoystick()", "Right button 7=CameraPanByCmd -.");
				new JoystickButton(this.rightJoystick, 7).whenPressed(new CameraPanByCmd(-1));

				this.debug("setupRightJoystick()", "Right button 8=RobotInitializeCmd.");
				new JoystickButton(this.rightJoystick, 8).whenPressed(new RobotInitializeCmd());

				this.debug("setupRightJoystick()", "Right button 9=ShooterFirePlungerCmd.");
				new JoystickButton(this.rightJoystick, 9).whenPressed(new ShooterFirePlungerCmd());

				this.debug("setupRightJoystick()", "Right button 10=CameraTiltByCmd -.");
				new JoystickButton(this.rightJoystick, 10).whenPressed(new CameraTiltByCmd(-1));

				this.debug("setupRightJoystick()", "Right button 11=CameraTiltByCmd +.");
				new JoystickButton(this.rightJoystick, 11).whenPressed(new CameraTiltByCmd(1));

			}

		} catch (Exception anEx) {
			this.error("setupRightJoystick()", anEx);
		}
	}

	private void setupLeftJoystick() {

		try {

			this.leftJoystick = new Joystick(WkwPrefs.getJoystickLeftPort());

			/*
			 * 
			 * 
			 * left joystick
			 * 
			 * 
			 */

			if (null != this.leftJoystick) {

				this.debug("setupLeftJoystick()", "Left trigger=BeachingRetractCmd.");
				new JoystickButton(this.leftJoystick, 1).whenPressed(new BeachingRetractCmd());

				this.debug("setupLeftJoystick", "Left button 2=CameraRotateToShooter.");
				new JoystickButton(this.leftJoystick, 2).whenPressed(new CameraRotateToShooter());

				this.debug("setupLeftJoystick()", "Left button 4=ShooterTurnMotorOnCmd.");
				new JoystickButton(this.leftJoystick, 4).whenPressed(new ShooterTurnMotorOnCmd());

				this.debug("setupLeftJoystick()", "Left button 5=ShooterTurnMotorOffCmd.");
				new JoystickButton(this.leftJoystick, 5).whenPressed(new ShooterTurnMotorOffCmd());

				this.debug("setupLeftJoystick()", "Left button 6=GathererTurnOnCmd.");
				new JoystickButton(this.leftJoystick, 6).whenPressed(new GathererTurnOnCmd());

				this.debug("setupLeftJoystick()", "Left button 7=GathererTurnOffCmd.");
				new JoystickButton(this.leftJoystick, 7).whenPressed(new GathererTurnOffCmd());

			}

		} catch (Exception anEx) {
			this.error("setupLeftJoystick()", anEx);
		}
	}

	public Joystick getLeftJoystick() {
		return this.leftJoystick;
	}

	public Joystick getRightJoystick() {
		return this.rightJoystick;
	}

	/*
	 * 
	 * 
	 * helper methods.
	 * 
	 */

	protected void debug(final String pMethod, final String pMessage) {
		WkwFrcLogger.debug(this.getClassName(), pMethod, pMessage);
	}

	protected void info(final String pMethod, final String pMessage) {
		WkwFrcLogger.info(this.getClassName(), pMethod, pMessage);
	}

	private String formatException(final Exception pEx) {
		return (null == pEx ? "." : pEx.getClass().getName() + ", message=" + pEx.getMessage()
				+ ".");
	}

	protected void error(final String pMethod, final Exception pEx) {
		this.error(pMethod, this.formatException(pEx), pEx);
	}

	protected void error(final String pMethod, final String pMessage, final Exception pEx) {
		WkwFrcLogger.error(this.getClassName(), pMethod, pMessage, pEx);
		if (null != pEx) {
			pEx.printStackTrace();
		}
	}

	protected String getDateTimeFormatted() {
		final SimpleDateFormat aformat = new SimpleDateFormat();
		return aformat.format(new Date());
	}

	protected String getClassName() {
		String aClassName = this.getClass().getName();
		return aClassName.substring(aClassName.lastIndexOf('.') + 1);
	}

}
