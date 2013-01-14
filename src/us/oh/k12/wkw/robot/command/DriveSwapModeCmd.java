/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.command;

import us.oh.k12.wkw.robot.subsystem.DriveSystem;
import edu.wpi.first.wpilibj.Preferences;

/**
 * Command to swap the drive mode (arcade | tank).
 */
public class DriveSwapModeCmd extends CommandBase {

	public DriveSwapModeCmd() {
		super("DriveSwapModeCmd");
		this.requires(this.getDriveSystem());
		this.debug("DriveSwapModeCmd()", "Called.");
	}

	protected void execute() {

		try {

			this.debug("execute()", "Started.");

			final Preferences aPreferences = Preferences.getInstance();

			aPreferences.putBoolean(DriveSystem.PREF_NAME_ARCADE_DRIVE_ENABLED,
					!aPreferences.getBoolean(DriveSystem.PREF_NAME_ARCADE_DRIVE_ENABLED, true));

			// aPreferences.putBoolean("~S A V E~", true);

			aPreferences.save();

			this.debug("execute()", "Ended.");

		} catch (Exception anEx) {
			this.error("execute()", anEx);
		}
	}

}
