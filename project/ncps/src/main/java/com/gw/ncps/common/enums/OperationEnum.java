package com.gw.ncps.common.enums;

public enum OperationEnum {

	SAVE("add"),

	UPDATE("update"),

	DELETE("delete"),

	SEARCH("search");

	private String value;

	public String getValue() {
		return value;
	}

	private OperationEnum(String value) {
		this.value = value;
	}

}
