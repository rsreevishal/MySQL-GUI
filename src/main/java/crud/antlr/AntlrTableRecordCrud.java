package crud.antlr;

import java.util.ArrayList;
import crud.TableCrud;
import crud.TableRecordCrud;
import expression.*;
import model.Field;
import model.FieldConstraint;
import model.FieldType;
import model.PrimaryKey;
import model.Table;
import model.TableRecord;
import model.TableRecordField;
import model.filters.Condition;
import model.filters.EqualsCondition;
import model.filters.GreaterThanCondition;
import model.filters.HasCondition;
import model.filters.LesserThanCondition;

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
		if (table.getFields().size() - 1 == expr.listToken.values.size()) {
			String values = "";
			int valCounter = 0;
			for (int i = 0; i < table.getFields().size(); i++) {
				// skip PRIMARY_KEY
				if (!table.getFields().get(i).getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
					TableRecordField tableRecordField = new TableRecordField();
					tableRecordField.setFieldName(table.getFields().get(i).getName());
					tableRecordField.setFieldType(table.getFields().get(i).getFieldType());
					String value = expr.listToken.values.get(valCounter);
					values += (value + ",");
					tableRecordField.setFieldValue(value.substring(1, value.length() - 1)); // remove ' ' from start and
																							// end
					tableRecordFields.add(tableRecordField);
					valCounter += 1;
				}
			}
			tableRecord.setFields(tableRecordFields);
			tableRecordCrud.create(tableRecord);
			return String.format(
					"<p><span style='color: #4287f5;'>Record: %s </span> <span style='color: green;'>inserted</span> successfully!</p>",
					values);
		}
		return "<p style='color:red;'>Can't able to insert record</p>";
	}

	public String update(UpdateExpr expr) {
		TableRecord tableRecord = new TableRecord();
		tableRecord.setTablename(expr.idToken.id);
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecordField> tableRecordFields = new ArrayList<TableRecordField>();
		// add if all values are provided expect PRIMARY_KEY
		if (table.getFields().size() - 1 == expr.uListToken.values.size()) {
			int valCounter = 0;
			PrimaryKey pk = new PrimaryKey();
			for (int i = 0; i < table.getFields().size(); i++) {
				// skip PRIMARY_KEY
				if (!table.getFields().get(i).getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
					TableRecordField tableRecordField = new TableRecordField();
					tableRecordField.setFieldName(table.getFields().get(i).getName());
					tableRecordField.setFieldType(table.getFields().get(i).getFieldType());
					String value = expr.uListToken.values.get(valCounter);
					tableRecordField.setFieldValue(value.substring(1, value.length() - 1)); // remove ' ' from start and
																							// end
					tableRecordFields.add(tableRecordField);
					valCounter += 1;
				} else {
					pk.setKey(table.getFields().get(i).getName());
					pk.setValue(expr.uListToken.uidToken.uid + "");
				}
			}
			tableRecord.setFields(tableRecordFields);
			tableRecordCrud.update(tableRecord, pk);
			return String.format(
					"<p><span style='color: #4287f5;'>Record: %d </span> <span style='color: green;'>updated</span> successfully!</p>",
					expr.uListToken.uidToken.uid);
		}
		return "<p style='color: red;'>Can't able to insert record<p>";
	}

	public String delete(DeleteExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		String pKey;
		PrimaryKey pk = new PrimaryKey();
		for (Field f : table.getFields()) {
			if (f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid + "");
				break;
			}
		}
		tableRecordCrud.delete(table.getName(), pk);
		return String.format(
				"<p><span style='color: #4287f5;'>Record: %d </span> <span style='color: red;'>deleted</span> successfully!</p>",
				expr.uidToken.uid);
	}

	public String viewAll(ViewAllExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table);
		if (records.size() > 0) {
			String heading = "", values = "";
			for (TableRecordField trf : records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for (TableRecord record : records) {
				String row = "";
				for (TableRecordField trf : record.getFields()) {
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
		if (records.size() > 0) {
			String heading = "", values = "";
			for (TableRecordField trf : records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for (TableRecord record : records) {
				String row = "";
				for (TableRecordField trf : record.getFields()) {
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
		for (Field f : table.getFields()) {
			if (f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid + "");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk);
		String heading = "", values = "";
		for (TableRecordField trf : record.getFields()) {
			heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
		}
		String row = "";
		for (TableRecordField trf : record.getFields()) {
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
		for (Field f : table.getFields()) {
			if (f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.uidToken.uid + "");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk, expr.colToken.id);
		String heading = "", values = "";
		for (TableRecordField trf : record.getFields()) {
			heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
		}
		String row = "";
		for (TableRecordField trf : record.getFields()) {
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
		for (Field f : table.getFields()) {
			if (f.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
				pKey = f.getName();
				pk.setKey(pKey);
				pk.setValue(expr.colViewExpr.uidToken.uid + "");
				break;
			}
		}
		TableRecord record = tableRecordCrud.get(table, pk, expr.colViewExpr.colToken.id);
		return record.getFields().get(0).getFieldValue();
	}

	public String createFormTable(FormExpr expr) {
		Table table = new Table();
		table.setName(expr.idToken.id);
		ArrayList<Field> fields = new ArrayList<Field>();
		Field pk = new Field();
		pk.setName("id");
		pk.setFieldType(FieldType.INT);
		ArrayList<FieldConstraint> pkConstraints = new ArrayList<FieldConstraint>();
		pkConstraints.add(FieldConstraint.PRIMARY_KEY);
		pk.setFieldConstraint(pkConstraints);
		fields.add(pk);
		for (FormInputExpr fie : expr.formInputs) {
			Field field = new Field();
			field.setName(fie.idToken.id);
			field.setFieldType(FieldType.fromInputType(fie.inputType));
			ArrayList<FieldConstraint> constraints = new ArrayList<FieldConstraint>();
			constraints.add(FieldConstraint.NOT_NULL);
			field.setFieldConstraint(constraints);
			fields.add(field);
		}
		table.setFields(fields);
		Table result = tableCrud.create(table);
		String form = formExprToHTMLForm(expr, result);
		tableCrud.createForm(result.getId(), form);
		return "<p style='color: green;'>Successfully created form<p>";
	}

	public String formExprToHTMLForm(FormExpr expr, Table table) {
		String inputs = "";
		int cbCount = 0;
		System.out.println(String.format("Input size: %d, Field size: %d", expr.formInputs.size(), table.getFields().size()));
		for (int i=0; i<expr.formInputs.size(); i++) {
			FormInputExpr fie = expr.formInputs.get(i);
			Field field = table.getFields().get(i+1); // skipping PRIMARY_KEY;
			String formGroup = "";
			formGroup += String.format("<input type='hidden' name='fieldName' value='%s'/><input type='hidden' name='fieldType' value='%s' />",
					field.getName(), field.getFieldType().toString());
			switch (fie.inputType) {
			case EMAIL:
			case PASSWORD:
			case TEXT: {
				formGroup += String.format("<label>%s</label>", fie.idToken.id);
				formGroup += String.format(
						"<input class='form-control' type='%s' minlength='%s' maxlength='%s' name='fieldValue' required/>",
						fie.inputType.toString().toLowerCase(), fie.args.get(0), fie.args.get(1), fie.idToken.id);
				break;
			}
			case TEXTAREA: {
				formGroup += String.format("<label>%s</label>", fie.idToken.id);
				formGroup += String.format(
						"<textarea class='form-control' minlength='%s' maxlength='%s' name='fieldValue' required></textarea>",
						fie.args.get(0), fie.args.get(1), fie.idToken.id);
				break;
			}
			case CHECKBOX: {
				for(String val: fie.args) {
					formGroup += String.format("<input type='%s' name='fieldValue' value='cb%d_%s' />",
							fie.inputType.toString().toLowerCase(), cbCount, val);
					formGroup += String.format("<label>%s</label>", val);
				}
				cbCount += 1;
				break;
			}
			case RADIO: {
				for(String val: fie.args) {
					formGroup += String.format("<input type='%s' name='radio%d' value='%s'/>",
							fie.inputType.toString().toLowerCase(), i, val);
					formGroup += String.format("<label>%s</label>", val);
				}
				break;
			}
			case NUMBER: {
				formGroup += String.format("<label>%s</label>", fie.idToken.id);
				formGroup += String.format(
						"<input class='form-control' type='%s' min='%s' max='%s' name='fieldValue' required/>",
						fie.inputType.toString().toLowerCase(), fie.args.get(0), fie.args.get(1), fie.idToken.id);
				break;
			}
			default: {}
			}
			inputs += String.format("<div class='form-group'>%s</div>", formGroup);
		}
		return String.format("<form class='form' id='table_form' method='post' action='/mysqlgui/InsertTableRecord'><h3>%s</h3><input type='hidden' name='tab' value='tab2'/><input type='hidden' name='tablename' value='%s'/>%s<button class='btn btn-success' type='submit'>Insert record</button></form>",
				expr.idToken.id, table.getName(), inputs);
	}
	
	public String createReport(CreateFormReportExpr expr) {
		Table table = tableCrud.get(expr.table.id);
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table);
		records = filter(records, expr);
		if (records.size() > 0) {
			String heading = "", values = "";
			for (TableRecordField trf : records.get(0).getFields()) {
				heading += String.format("<th scope='cols'>%s</th>", trf.getFieldName());
			}
			for (TableRecord record : records) {
				String row = "";
				for (TableRecordField trf : record.getFields()) {
					row += String.format("<td>%s</td>", trf.getFieldValue());
				}
				values += String.format("<tr>%s</tr>", row);
			}
			String tableStr = String.format("<table class='table'><tr>%s</tr>%s</table>", heading, values);
			return tableStr;
		}
		return "<p style='color: red;'>No records<p>";
	}
	
	public ArrayList<TableRecord> filter(ArrayList<TableRecord> record, CreateFormReportExpr expr) {
		ArrayList<Condition> conditions = new ArrayList<Condition>();
		for(ConditionExpr condition: expr.conditions) {
			if(condition.operatorExpr.operator.equals("=")) {
				conditions.add(new EqualsCondition(condition.colName.id, condition.colValue.text));
			}
			if(condition.operatorExpr.operator.equals(">")) {
				conditions.add(new GreaterThanCondition(condition.colName.id, Integer.parseInt(condition.colValue.text)));
			}
			if(condition.operatorExpr.operator.equals("<")) {
				conditions.add(new LesserThanCondition(condition.colName.id, Integer.parseInt(condition.colValue.text)));
			}
			if(condition.operatorExpr.operator.equals("HAS")) {
				conditions.add(new HasCondition(condition.colName.id, condition.colValue.text));
			}
		}
		for(Condition condition: conditions) {
			record = condition.meetCondition(record);
		}
		return record;
	}

}
