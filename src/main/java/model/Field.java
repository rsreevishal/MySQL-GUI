package model;

import java.util.ArrayList;

public class Field {
	private String name;
	private FieldType fieldType;
	private ArrayList<FieldConstraint> fieldConstraint;
	private Table foreignTable;
	
	public FieldType getFieldType() {
		return fieldType;
	}
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<FieldConstraint> getFieldConstraint() {
		return fieldConstraint;
	}
	public void setFieldConstraint(ArrayList<FieldConstraint> fieldConstraint) {
		this.fieldConstraint = fieldConstraint;
	}
	public Table getForeignTable() {
		return foreignTable;
	}
	public void setForeignTable(Table foreignTable) {
		this.foreignTable = foreignTable;
	}
	public String getFieldConstraintString() {
		StringBuilder result = new StringBuilder("");
		for(FieldConstraint fc: this.fieldConstraint) {
			result.append(fc).append(", ");
		}
		return result.toString().substring(0, result.length() - 2);
	}
}
