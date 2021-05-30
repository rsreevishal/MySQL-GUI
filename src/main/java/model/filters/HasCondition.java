package model.filters;

import java.util.ArrayList;
import java.util.Arrays;
import model.TableRecord;
import model.TableRecordField;

public class HasCondition implements Condition{
	public String field;
	public String condition;
	
	public HasCondition(String field, String condition) {
		this.field = field;
		this.condition = condition;
	}
	
	public ArrayList<TableRecord> meetCondition(ArrayList<TableRecord> records) {
		ArrayList<TableRecord> filtered = new ArrayList<TableRecord>();
		for(TableRecord record: records) {
			for(TableRecordField rfield: record.getFields()) {
				ArrayList<String> values = new ArrayList<String>(Arrays.asList(rfield.getFieldValue().split(",")));
				if(rfield.getFieldName().equals(this.field) && values.contains(this.condition)) {
					filtered.add(record);
				}
			}
		}
		return filtered;
	}
}
