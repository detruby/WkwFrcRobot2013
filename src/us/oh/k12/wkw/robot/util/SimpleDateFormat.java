/*----------------------------------------------------------------------------*/
/* Copyright (c) 2013 Worthington Robot Club. All Rights Reserved.   */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package us.oh.k12.wkw.robot.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Format a date/time.
 */
public class SimpleDateFormat {

	private String format;

	public SimpleDateFormat() {
		super();
		this.format = "yyyy/MM/dd_HH:mm:ss.sss";
	}

	public SimpleDateFormat(final String pFormat) {
		super();
		this.format = pFormat;
	}

	public String format(final Date pDate) {
		final Calendar aDate = Calendar.getInstance();
		aDate.setTime(pDate);
		return aDate.get(Calendar.YEAR) + "/" + this.padField(aDate.get(Calendar.MONTH) + 1) + "/"
				+ this.padField(aDate.get(Calendar.DAY_OF_MONTH)) + "_"
				+ this.padField(aDate.get(Calendar.HOUR_OF_DAY)) + ":"
				+ this.padField(aDate.get(Calendar.MINUTE)) + ":"
				+ this.padField(aDate.get(Calendar.SECOND)) + "."
				+ this.padFieldMilliseconds(aDate.get(Calendar.MILLISECOND));
	}

	protected String getFormat() {
		return this.format;
	}

	private String padField(final int pField) {
		return (pField < 10 ? "0" + Integer.toString(pField) : Integer.toString(pField));
	}

	private String padFieldMilliseconds(final int pField) {
		return (pField < 10 ? "00" + Integer.toString(pField) : (pField < 100 ? "0"
				+ Integer.toString(pField) : Integer.toString(pField)));
	}

}
