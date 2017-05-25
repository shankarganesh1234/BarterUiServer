package com.swap.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum NotificationTypeEnum {
	MY_INTERESTS, MY_OFFERS;

	/**
	 * 
	 * @param val
	 * @return
	 */
	public static NotificationTypeEnum fromValue(String val) {

		if (StringUtils.isBlank(val))
			return null;

		NotificationTypeEnum enumVal = null;
		try {
			enumVal = NotificationTypeEnum.valueOf(val);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return enumVal;
	}
}
