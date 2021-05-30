package model.filters;

import java.util.ArrayList;

import model.TableRecord;
import model.TableRecordField;

public class EqualsCondition implements Condition{
	public String field;
	public String condition;
	
	public EqualsCondition(String field, String condition) {
		this.field = field;
		this.condition = condition;
	}
	
	public ArrayList<TableRecord> meetCondition(ArrayList<TableRecord> records) {
		ArrayList<TableRecord> filtered = new ArrayList<TableRecord>();
		for(TableRecord record: records) {
			for(TableRecordField rfield: record.getFields()) {
				if(rfield.getFieldName().equals(this.field) && rfield.getFieldValue().equals(this.condition)) {
					filtered.add(record);
				}
			}
		}
		return filtered;
	}
}
