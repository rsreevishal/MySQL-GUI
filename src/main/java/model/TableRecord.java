package model;

import java.util.ArrayList;

public class TableRecord {
	private PrimaryKey key;
	private String tablename;
	private ArrayList<TableRecordField> fields;
	
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public ArrayList<TableRecordField> getFields() {
		return fields;
	}
	public void setFields(ArrayList<TableRecordField> fields) {
		this.fields = fields;
	}
	public PrimaryKey getKey() {
		return key;
	}
	public void setKey(PrimaryKey key) {
		this.key = key;
	}
}
