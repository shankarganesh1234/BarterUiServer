package com.swap.common;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {

	/**
	 * Get current date
	 * @return
	 */
	public static Timestamp getCurrentDate() {
		Timestamp date = null;
		date = new Timestamp(Calendar.getInstance().getTimeInMillis());
		return date;
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public static String decodeMessage(String message) {
		if(StringUtils.isBlank(message))
			return null;
		
		return message.split(":")[1];
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public static Long decodeInterestId(String message) {
		if(StringUtils.isBlank(message))
			return null;
		
		return Long.valueOf(message.split(":")[0]);
	}
	
}
