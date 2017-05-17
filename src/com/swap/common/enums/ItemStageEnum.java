package com.swap.common.enums;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonCreator;

public enum ItemStageEnum {
	INITIALIZED("INITIALIZED"), PENDING("PENDING"), COMPLETED("COMPLETED"), PUBLISHED("PUBLISHED");

	private String itemStageValue;

	private ItemStageEnum(String itemStageValue) {
		this.itemStageValue = itemStageValue;
	}

	public String getItemStageValue() {
		return itemStageValue;
	}

	@JsonCreator
	public static ItemStageEnum fromValue(String str) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			return ItemStageEnum.valueOf(str);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}