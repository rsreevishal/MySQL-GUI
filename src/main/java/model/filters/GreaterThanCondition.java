package model.filters;

import java.util.ArrayList;

import model.FieldType;
import model.TableRecord;
import model.TableRecordField;

public class GreaterThanCondition implements Condition{
	public String field;
	public int condition;
	
	public GreaterThanCondition(String field, int condition) {
		this.field = field;
		this.condition = condition;
	}
	
	public ArrayList<TableRecord> meetCondition(ArrayList<TableRecord> records) {
		ArrayList<TableRecord> filtered = new ArrayList<TableRecord>();
		for(TableRecord record: records) {
			for(TableRecordField rfield: record.getFields()) {
				if(rfield.getFieldName().equals(this.field) && rfield.getFieldType() == FieldType.INT && Integer.parseInt(rfield.getFieldValue()) > this.condition) {
					filtered.add(record);
				}
			}
		}
		return filtered;
	}
}
