package model;

import java.util.ArrayList;

import expression.InputType;

public enum FieldType {
	INT,
	VARCHAR
	;
	public static String filter(FieldType type) {
		switch(type) {
		case INT: return "INT";
		case VARCHAR: return "VARCHAR(50)";
		default: return "";
		}
	}
	
	public static FieldType fromInputType(InputType type) {
		switch(type) {
		case TEXT:
		case TEXTAREA:
		case EMAIL:
		case RADIO:
		case CHECKBOX:
		case PASSWORD: return FieldType.VARCHAR;
		case NUMBER: return FieldType.INT;
		default: return FieldType.VARCHAR;
		}
	}
}
