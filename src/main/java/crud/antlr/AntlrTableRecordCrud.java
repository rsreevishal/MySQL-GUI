package crud.antlr;
import java.util.ArrayList;
import crud.TableCrud;
import crud.TableRecordCrud;
import expression.AddExpr;
import expression.ColViewAllExpr;
import expression.ColViewExpr;
import expression.DeleteExpr;
import expression.StoreColViewExpr;
import expression.UpdateExpr;
import expression.ViewAllExpr;
import expression.ViewExpr;
import model.Field;
import model.FieldConstraint;
import model.PrimaryKey;
import model.Table;
import model.TableRecord;
import model.TableRecordField;

public class AntlrTableRecordCrud {
	private TableCrud tableCrud;
	private TableRecordCrud tableRecordCrud;

	public AntlrTableRecordCrud() {
		tableCrud = new TableCrud();
		tableRecordCrud = new TableRecordCrud();
	}
	
	public String create(AddExpr expr) {
		TableRecord tableRecord = new TableRecord();
		tableRecord.setTablename(expr.idToken.id);
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecordField> tableRecordFields = new ArrayList<TableRecordField>();
		// add if all values are provided expect PRIMARY_KEY
		if(table.getFields().size() - 1 == expr.listToken.values.size()) {
			String values = "";
			int valCounter = 0;
			for(int i=0; i<table.getFields().size(); i++) {
				// skip PRIMARY_KEY
				if(!table.getFields().get(i).getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
					TableRecordField tableRecordField = new TableRecordField();
					tableRecordField.setFieldName(table.getFields().get(i).getName());
					tableRecordField.setFieldType(table.getFields().get(i).getFieldType());
					String value = expr.listToken.values.get(valCounter);
					values += (value + ",");
					tableRecordField.setFieldValue(value.substring(1, value.length() - 1)); // remove ' ' from start and end
					tableRecordFields.add(tableRecordField);
					valCounter += 1;
				}
			}
			tableRecord.setFields(tableRecordFields);
			tableRecordCrud.create(tableRecord);
			return String.format("<p><span style='color: #4287f5;'>Record: %s </span> <span style='color: green;'>inserted</span> successfully!</p>", values);
		}
		return "<p style='color:red;'>Can't able to insert record</p>";
	}
	
	public String update(UpdateExpr expr) {
		TableRecord tableRecord = new TableRecord();
		tableRecord.setTablename(expr.idToken.id);
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecordField> tableRecordFields = new ArrayList<TableRecordField>();
		// add if all values are provided expect PRIMARY_KEY
		if(table.getFields().size() - 1 == expr.uListToken.values.size()) {
			int valCounter = 0;
			PrimaryKey pk = new PrimaryKey();
			for(int i=0; i<table.getFields().size(); i++) {
				// skip PRIMARY_KEY
				if(!table.getFields().get(i).getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
					TableRecordField tableRecordField = new TableRecordField();
					tableRecordField.setFieldName(table.getFields().get(i).getName());
					tableRecordField.setFieldType(table.getFields().get(i).getFieldType());
					String value = expr.uListToken.values.get(valCounter);
					tableRecordField.setFieldValue(value.substring(1, value.length() - 1)); // remove ' ' from start and end
					tableRecordFields.add(tableRecordField);
					valCounter += 1;
				} else {
					pk.setKey(table.getFields().get(i).getName());
					pk.setValue(expr.uListToken.uidToken.uid + "");
				}
			}
			tableRecord.setFields(tableRecordFields);
			tableRecordCrud.update(tableRecord, pk);
			return String.format("<p><span style='color: #4287f5;'>Record: %d </span> <span style='color: green;'>updated</span> successfully!</p>", expr.uListToken.uidToken.uid);
		}
		return "<p style='color: red;'>Can't able to insert record<p>";
	}
	
	public String delete(DeleteExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		String pKey;
		PrimaryKey pk = new PrimaryKey();
		for(Field f: table.getFields()) {
			if(f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid+"");
				break;
			}
		}
		tableRecordCrud.delete(table.getName(), pk);
		return String.format("<p><span style='color: #4287f5;'>Record: %d </span> <span style='color: red;'>deleted</span> successfully!</p>", expr.uidToken.uid);
	}
	
	public String viewAll(ViewAllExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table);
		if(records.size() > 0) {
			String heading = "", values = "";
			for(TableRecordField trf: records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for(TableRecord record: records) {
				String row = "";
				for(TableRecordField trf: record.getFields()) {
					row += String.format("<td>%s</td>", trf.getFieldValue());
				}
				values += String.format("<tr>%s</tr>", row);
			}
			String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
			return tableStr;
		}
		return "<p style='color: red;'>No records<p>";
	}
	
	public String viewAll(ColViewAllExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table, expr.colToken.id);
		if(records.size() > 0) {
			String heading = "", values = "";
			for(TableRecordField trf: records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for(TableRecord record: records) {
				String row = "";
				for(TableRecordField trf: record.getFields()) {
					row += String.format("<td>%s</td>", trf.getFieldValue());
				}
				values += String.format("<tr>%s</tr>", row);
			}
			String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
			return tableStr;
		}
		return "<p style='color: red;'>No records<p>";
	}
	
	public String view(ViewExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		String pKey;
		PrimaryKey pk = new PrimaryKey();
		for(Field f: table.getFields()) {
			if(f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid+"");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk);
		String heading = "", values = "";
		for(TableRecordField trf: record.getFields()) {
			heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
		}
		String row = "";
		for(TableRecordField trf: record.getFields()) {
			row += String.format("<td>%s</td>", trf.getFieldValue());
		}
		values += String.format("<tr>%s</tr>", row);
		
		String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
		return tableStr;
	}
	
	public String view(ColViewExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		String pKey;
		PrimaryKey pk = new PrimaryKey();
		for(Field f: table.getFields()) {
			if(f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid+"");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk, expr.colToken.id);
		String heading = "", values = "";
		for(TableRecordField trf: record.getFields()) {
			heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
		}
		String row = "";
		for(TableRecordField trf: record.getFields()) {
			row += String.format("<td>%s</td>", trf.getFieldValue());
		}
		values += String.format("<tr>%s</tr>", row);
		
		String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
		return tableStr;
	}
	
	public String getValue(StoreColViewExpr expr) {
		Table table = tableCrud.get(expr.colViewExpr.idToken.id);
		String pKey;
		PrimaryKey pk = new PrimaryKey();
		for(Field f: table.getFields()) {
			if(f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.colViewExpr.uidToken.uid+"");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk, expr.colViewExpr.colToken.id);
		return record.getFields().get(0).getFieldValue();
	}
}
