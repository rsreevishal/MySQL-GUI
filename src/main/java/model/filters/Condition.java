package model.filters;

import java.util.ArrayList;

import model.TableRecord;

public interface Condition {
	public ArrayList<TableRecord> meetCondition(ArrayList<TableRecord> records);
}
