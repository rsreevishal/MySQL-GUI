package model.filters;

import java.util.ArrayList;
import model.TableRecord;
import model.TableRecordField;

public class LesserThanCondition implements Condition{
	public String field;
	public int condition;
	
	public LesserThanCondition(String field, int condition) {
		this.field = field;
		this.condition = condition;
	}
	
	public ArrayList<TableRecord> meetCondition(ArrayList<TableRecord> records) {
		ArrayList<TableRecord> filtered = new ArrayList<TableRecord>();
		for(TableRecord record: records) {
			for(TableRecordField rfield: record.getFields()) {
				try {
					if(rfield.getFieldName().equals(this.field) && Integer.parseInt(rfield.getFieldValue()) < this.condition) {
						filtered.add(record);
					}
				} catch(NumberFormatException e) {
					continue;
				}
				
			}
		}
		return filtered;
	}
}
