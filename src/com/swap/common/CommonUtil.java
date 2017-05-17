package com.swap.common;

import java.sql.Timestamp;
import java.util.Calendar;

public class CommonUtil {

	public static Timestamp getCurrentDate() {
		Timestamp date = null;
		date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		return date;
	}
}
