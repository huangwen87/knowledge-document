package com.gw.ncps.common.enums;

/**
 * 数据字典表中数据状态
 *
 */
public enum DictionaryStateEnum {

	VALID(1), 
	
	INVALID(0);

	private int value;

	public int getValue() {
		return value;
	}

	private DictionaryStateEnum(int value) {
		this.value = value;
	}

}
