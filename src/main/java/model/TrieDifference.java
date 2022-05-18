package model;

public class TrieDifference {
	private DifferenceType differenceType;
	private String tablename;
	private Field field;

	public TrieDifference(DifferenceType differenceType, String tablename, Field field) {
		super();
		this.differenceType = differenceType;
		this.tablename = tablename;
		this.field = field;
	}
	public DifferenceType getDifferenceType() {
		return differenceType;
	}
	public void setDifferenceType(DifferenceType differenceType) {
		this.differenceType = differenceType;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
}
