package com.example.multitenancy.enums;

public enum EmployeeEnum {

	ID("id", "Long"), NAME("name", "String"), POSITION("position", "String"), SALARY("salary", "int"),
	AGE("age", "int"), ADDRESS("primaryAddress", "Address");

	private String value;
	// this is the property which is there in Job Entity. we are using this property
	// in Reflections to get respective value
	private String entityProperty;

	EmployeeEnum(String value, String entityProperty) {
		this.value = value;
		this.entityProperty = entityProperty;
	}

	public String getValue() {
		return this.value;
	}

	public String getEntityProperty() {
		return this.entityProperty;
	}

	public static EmployeeEnum getEmployeeEnum(String value) {
		EmployeeEnum keyword = null;
		for (EmployeeEnum employeeEnum : EmployeeEnum.values()) {
			if (employeeEnum.getValue().equals(value)) {
				keyword = employeeEnum;
			}
		}
		return keyword;
	}
}
