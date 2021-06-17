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
			int valCounter = 0;
			for (int i = 0; i < table.getFields().size(); i++) {
				// skip PRIMARY_KEY
				if (!table.getFields().get(i).getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
					TableRecordField tableRecordField = new TableRecordField();
					tableRecordField.setFieldName(table.getFields().get(i).getName());
					tableRecordField.setFieldType(table.getFields().get(i).getFieldType());
					String value = expr.listToken.values.get(valCounter);
					tableRecordField.setFieldValue(value.substring(1, value.length() - 1)); // remove ' ' from start and end
					tableRecordFields.add(tableRecordField);
					valCounter += 1;
				}
			}
			tableRecord.setFields(tableRecordFields);
			tableRecordCrud.create(tableRecord);
			return expr.toHTML();
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
			return expr.toHTML();
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
		return expr.toHTML();
	}

	public String viewAll(ViewAllExpr expr) {
		Table table = tableCrud.get(expr.idToken.id);
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table);
		expr.setRecords(records);
		return expr.toHTML();
	}

	public String viewAll(ColViewAllExpr expr) {
		ArrayList<TableRecord> records = tableRecordCrud.getAll(expr.idToken.id, expr.colToken.id);
		expr.setRecords(records);
		return expr.toHTML();
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
		expr.setRecord(record);
		return expr.toHTML();
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
		expr.setRecord(record);
		return expr.toHTML();
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

	public String createFormTable(FormExpr expr, boolean createNew) {
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
			if(fie.inputType == InputType.LINK) {
				Field originalField = tableCrud.getField(fie.args.values.get(0), fie.args.values.get(1));
				if(originalField != null) {
					field.setFieldType(originalField.getFieldType());
					field.setFieldConstraint(originalField.getFieldConstraint());
					fie.setLinkedValues(tableRecordCrud.getAllValues(fie.args.values.get(0), fie.args.values.get(1)));
				} else {
					field.setFieldType(FieldType.VARCHAR);
					fie.setLinkedValues(new ArrayList<String>());
				}
			} else {
				field.setFieldType(FieldType.fromInputType(fie.inputType));
			}
			ArrayList<FieldConstraint> constraints = new ArrayList<FieldConstraint>();
			constraints.add(FieldConstraint.NOT_NULL);
			field.setFieldConstraint(constraints);
			fields.add(field);
		}
		table.setFields(fields);
		Table result = table;
		if(createNew) {
			 table.setUser(expr.getUser());
			 result = tableCrud.create(table);
			 tableCrud.saveFormQuery(result.getId(), expr);
		}
		String form = expr.toHTML();
		return form;
	}
	
	public String createReport(FormReportExpr expr, boolean createNew) {
		Table table = tableCrud.get(expr.table.id);
		if(createNew) {
			tableCrud.saveFormQuery(table.getId(), expr);
		}
		ArrayList<TableRecord> records = tableRecordCrud.getAll(table);
		records = filter(records, expr);
		expr.setRecords(records);
		return expr.toHTML();
	}
	
	public ArrayList<TableRecord> filter(ArrayList<TableRecord> record, FormReportExpr expr) {
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
