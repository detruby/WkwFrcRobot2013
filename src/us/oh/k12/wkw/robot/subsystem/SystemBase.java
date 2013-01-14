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
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.AllocationException;

/**
 * Base class for all systems.
 */
public abstract class SystemBase extends Subsystem {

	protected SystemBase() {
		super();
	}

	/**
	 * @param name
	 */
	public SystemBase(final String pName) {
		super(pName);
	}

	protected void dumpJaguar(final int pModule, final Jaguar pJaguar) {
		if (null == pJaguar) {
			this.debug("dumpJaguar()", pModule + " is not present.");
		} else {
			this.debug("dumpJaguar()", "Channel=" + pJaguar.getChannel() + ".");
			this.debug("dumpJaguar()", "ModuleNumber=" + pJaguar.getModuleNumber() + ".");
			this.debug("dumpJaguar()", "Position=" + pJaguar.getPosition() + ".");
			this.debug("dumpJaguar()", "Description=" + pJaguar.getDescription() + ".");
			this.debug("dumpJaguar()", "Expiration=" + pJaguar.getExpiration() + ".");
			this.debug("dumpJaguar()", "get=" + pJaguar.get() + ".");
			this.debug("dumpJaguar()", "Raw=" + pJaguar.getRaw() + ".");
			this.debug("dumpJaguar()", "Speed=" + pJaguar.getSpeed() + ".");
			this.debug("dumpJaguar()", "isAlive=" + pJaguar.isAlive() + ".");
			this.debug("dumpJaguar()", "isSafetyEnabled=" + pJaguar.isSafetyEnabled() + ".");
		}
	}

	protected void findChannels() {
		Jaguar aJaguar = null;

		this.debug("findChannels()", "Started.");

		for (int idx = 1; idx < 11; idx++) {

			try {

				this.debug("findChannels()", "Trying channel=" + idx + ".");
				aJaguar = new Jaguar(idx);
				this.dumpJaguar(idx, aJaguar);
				aJaguar.set(1.0);
				this.debug("findChannels()", "get=" + aJaguar.get() + ".");

			} catch (AllocationException anAloEx) {
				this.error("findChannels()", "AllocationException.", null);
			}

		}

		this.debug("findChannels()", "Ended.");
	}

	/*
	 * 
	 * support methods.
	 * 
	 */

	protected void debug(final String pMethod, final String pMessage) {
		WkwFrcLogger.debug(this.getClassName(), pMethod, pMessage);
	}

	protected void info(final String pMethod, final String pMessage) {
		WkwFrcLogger.info(this.getClassName(), pMethod, pMessage);
	}

	protected void reportSelfTestState(final String pSystem, final String pSensor,
			final String pState, final boolean pPassed, final String pMessage) {

		if (pPassed) {

			WkwFrcLogger.info(this.getClassName(), "reportSelfTestState()", "System:" + pSystem
					+ ", Sensor:" + pSensor + ", State:" + pState + ", Passed:" + pPassed
					+ ", Message:" + pMessage + ".");

		} else {

			WkwFrcLogger.error(this.getClassName(), "reportSelfTestState()", "System:" + pSystem
					+ ", Sensor:" + pSensor + ", State:" + pState + ", Passed:" + pPassed
					+ ", Message:" + pMessage + ".", null);

		}

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
