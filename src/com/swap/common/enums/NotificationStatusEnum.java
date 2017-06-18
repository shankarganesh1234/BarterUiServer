package com.swap.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum NotificationStatusEnum {

	UNREAD, READ;
	
	/**
	 * Returns the enum from string
	 * @param val
	 * @return
	 */
	public static NotificationStatusEnum fromValue(String val) {
		
		if(StringUtils.isBlank(val))
			return null;
		
		NotificationStatusEnum enumVal = null;
		try {
			enumVal = NotificationStatusEnum.valueOf(val);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return enumVal;
	}
}
