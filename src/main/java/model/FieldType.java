package model;

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
}
