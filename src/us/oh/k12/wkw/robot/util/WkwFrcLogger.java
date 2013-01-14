/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.util;

import java.util.Date;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Utility to send log messages to SmartDashboard.
 */
public class WkwFrcLogger extends WkwFrcLoggerBase {

	private static final String NETWORK_TABLE_NAME = "WkwFrcLogger";
	private static final String NETWORK_TABLE_FIELD_NAME = "log";

	private static Object lock = new Object();
	private static WkwFrcLogger instance = null;

	/**
	 * log a debug message.
	 * 
	 * @param pClazz
	 *            class.
	 * @param pMethod
	 *            method.
	 * @param pMessage
	 *            message.
	 */
	public static void debug(final String pClazz, final String pMethod, final String pMessage) {

		WkwFrcLogger.getInstance().sendMulticast(new Date(), pClazz, "D", pMethod, pMessage);
	}

	/**
	 * log an info message.
	 * 
	 * @param pClazz
	 *            class.
	 * @param pMethod
	 *            method.
	 * @param pMessage
	 *            message.
	 */
	public static void info(final String pClazz, final String pMethod, final String pMessage) {

		WkwFrcLogger.getInstance().sendMulticast(new Date(), pClazz, "I", pMethod, pMessage);
	}

	/**
	 * log an error message.
	 * 
	 * @param pClazz
	 *            class.
	 * @param pMethod
	 *            method.
	 * @param pMessage
	 *            message.
	 * @param pEx
	 *            exception.
	 */
	public static void error(final String pClazz, final String pMethod, final String pMessage,
			final Exception pEx) {

		WkwFrcLogger.getInstance().sendMulticast(new Date(), pClazz, "E", pMethod, pMessage);
	}

	/**
	 * Initialize the logger.
	 */
	public static void initialize() {

		synchronized (WkwFrcLogger.lock) {

			WkwFrcLogger.instance = new WkwFrcLogger();

		}

	}

	protected void sendMulticast(final Date pMessageDate, final String pClazz, final String pLevel,
			final String pMethod, final String pMessage) {

		// format the log record
		final String aMsg = this.formatMessage(pMessageDate, pClazz, pLevel, pMethod, pMessage);

		// send data via NetworkTable
		NetworkTable.getTable(WkwFrcLogger.NETWORK_TABLE_NAME).putString(
				WkwFrcLogger.NETWORK_TABLE_FIELD_NAME, aMsg);

		// log locally on the robot
		System.out.println(aMsg);

	}

	/**
	 * get just the class name (without the package).
	 * 
	 * @param pClass
	 *            class
	 * @return class name.
	 */
	public static String formatClassName(final Class pClass) {
		final String aClassName = pClass.getName();
		return aClassName.substring(aClassName.lastIndexOf('.') + 1);
	}

	/**
	 * a singleton.
	 * 
	 * @return WkwFrcLogger.
	 */
	private static WkwFrcLogger getInstance() {

		synchronized (WkwFrcLogger.lock) {

			if (null == WkwFrcLogger.instance) {

				WkwFrcLogger.instance = new WkwFrcLogger();

			}

		}

		return WkwFrcLogger.instance;
	}

	/**
	 * Default constructor that is hidden (this is a singleton).
	 */

	private WkwFrcLogger() {
		super();
	}

	/**
	 * Only used for testing the logger.
	 * 
	 * @param args
	 *            pArgs.
	 */
	public static void main(final String[] pArgs) {

		WkwFrcLogger.debug(WkwFrcLogger.formatClassName(WkwFrcLogger.class), "main()",
				"Test debug " + Long.toString(System.currentTimeMillis()));

	}

}
