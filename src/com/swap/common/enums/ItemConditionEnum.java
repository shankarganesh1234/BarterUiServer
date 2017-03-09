package com.swap.common.enums;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonCreator;

public enum ItemConditionEnum {
	BRAND_NEW("BRAND_NEW"), PRE_OWNED("PRE_OWNED");
	
	private String itemConditionConstraintValue;

	private ItemConditionEnum(String itemConditionConstraintValue) {
		this.itemConditionConstraintValue = itemConditionConstraintValue;
	}

	public String getItemConditionConstraintValue() {
		return itemConditionConstraintValue;
	}

	@JsonCreator
	public static ItemConditionEnum fromValue(String str) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			return ItemConditionEnum.valueOf(str);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
