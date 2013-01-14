/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.util;

import java.util.Date;

/**
 * base class for the logger.
 */
public abstract class WkwFrcLoggerBase {

	private static final char SEPERATOR = ',';
	private static final String FORMAT = "yyyy/mm/dd_HH:mm:ss.sss";

	/**
	 * Default null constructor.
	 */
	protected WkwFrcLoggerBase() {
		super();
	}

	protected char getSeparator() {
		return WkwFrcLoggerBase.SEPERATOR;
	}

	protected String formatMessage(final Date pMessageDate, final String pClazz,
			final String pLevel, final String pMethod, final String pMessage) {
		final StringBuffer aMsg = new StringBuffer();

		aMsg.append(WkwFrcLogger.formatDate(pMessageDate));
		aMsg.append(this.getSeparator());
		aMsg.append(pLevel);
		aMsg.append(this.getSeparator());
		aMsg.append(pClazz);
		aMsg.append(this.getSeparator());
		aMsg.append(pMethod);
		aMsg.append(this.getSeparator());
		aMsg.append(pMessage);

		return aMsg.toString();
	}

	protected static String formatDate(final Date pDate) {
		final SimpleDateFormat aFormat = new SimpleDateFormat(WkwFrcLoggerBase.FORMAT);
		return (null == pDate ? "" : aFormat.format(pDate));
	}

}
