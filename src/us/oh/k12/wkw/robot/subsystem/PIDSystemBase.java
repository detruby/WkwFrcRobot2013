/*----------------------------------------------------------------------------*/
/* Copyright (c) 2012 Worthington Kilbourne Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.subsystem;

import java.util.Date;

import us.oh.k12.wkw.robot.util.SimpleDateFormat;
import us.oh.k12.wkw.robot.util.WkwFrcLogger;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Base class for all systems that have long running actions.
 */
public abstract class PIDSystemBase extends PIDSubsystem {

	protected PIDSystemBase() {
		this("", 0, 0, 0);
	}

	protected PIDSystemBase(final String pName) {
		this(pName, 0, 0, 0);
	}

	/**
	 * @param name
	 * @param p
	 * @param i
	 * @param d
	 */
	public PIDSystemBase(final String pName, final double pProportional, final double pIntegral,
			final double pDerivative) {
		super(pName, pProportional, pIntegral, pDerivative);
	}

	/**
	 * @param name
	 * @param p
	 * @param i
	 * @param d
	 * @param period
	 */
	public PIDSystemBase(final String pName, final double pProportional, final double pIntegral,
			final double pDerivative, final double pPeriod) {
		super(pName, pProportional, pIntegral, pDerivative, pPeriod);
	}

	/*
	 * 
	 * support methods.
	 * 
	 */

	protected static boolean getPreference(final String pName, final boolean pDefault) {
		final Preferences aPreferences = Preferences.getInstance();
		boolean aValue = aPreferences.getBoolean(pName, pDefault);
		aPreferences.putBoolean(pName, aValue);
		aPreferences.save();
		return aValue;
	}

	protected static double getPreference(final String pName, final double pDefault) {
		final Preferences aPreferences = Preferences.getInstance();
		double aValue = aPreferences.getDouble(pName, pDefault);
		if (aValue == 0) {
			aValue = pDefault;
			aPreferences.putDouble(pName, aValue);
			aPreferences.save();
		}
		return aValue;
	}

	protected static int getPreference(final String pName, final int pDefault) {
		final Preferences aPreferences = Preferences.getInstance();
		int aValue = aPreferences.getInt(pName, pDefault);
		if (aValue == 0) {
			aValue = pDefault;
			aPreferences.putInt(pName, aValue);
			aPreferences.save();
		}
		return aValue;
	}

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
