package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.DBConnector;
import model.Field;
import model.FieldConstraint;
import model.PrimaryKey;
import model.Table;
import model.TableRecord;
import model.TableRecordField;

public class TableRecordCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;

	public TableRecordCrud() {
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
	}

	public void create(TableRecord record) {
		StringBuilder fields = new StringBuilder(""), values = new StringBuilder("");
		for (TableRecordField trf : record.getFields()) {
			fields.append(trf.getFieldName()).append(",");
			switch (trf.getFieldType()) {
			case INT: {
				values.append(trf.getFieldValue()).append(",");
				break;
			}
			case VARCHAR: {
				values.append(String.format("'%s'", trf.getFieldValue())).append(",");
				break;
			}
			default: {
			}
			}
		}

		String query = String.format("INSERT INTO %s(%s) values(%s)", record.getTablename(),
				fields.toString().substring(0, fields.length() - 1),
				values.toString().substring(0, values.length() - 1));
		try {
			PreparedStatement st = sqlConnection.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(TableRecord record, PrimaryKey key) {
		StringBuilder  values = new StringBuilder("");
		for (TableRecordField trf : record.getFields()) {
			values.append(trf.getFieldName()).append("=");
			switch (trf.getFieldType()) {
			case INT: {
				values.append(trf.getFieldValue()).append(",");
				break;
			}
			case VARCHAR: {
				values.append(String.format("'%s'", trf.getFieldValue())).append(",");
				break;
			}
			default: {
			}
			}
		}
		String query = String.format("UPDATE %s SET %s WHERE %s=%s;", record.getTablename(),
				values.toString().substring(0, values.length() - 1),
				key.getKey(), key.getValue());
		try {
			PreparedStatement st = sqlConnection.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public ArrayList<TableRecord> getAll(Table table) {
		ArrayList<TableRecord> result = new ArrayList<TableRecord>();
		StringBuilder fields = new StringBuilder("");
		for (Field field : table.getFields()) {
			fields.append(field.getName()).append(",");
		}
		System.out.println(fields.toString());
		String query = String.format("SELECT %s FROM %s;", fields.toString().substring(0, fields.length() - 1),
				table.getName());
		System.out.println(query);
		try {
			PreparedStatement statement = sqlConnection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TableRecord tableRecord = new TableRecord();
				tableRecord.setTablename(table.getName());
				ArrayList<TableRecordField> tableRecordFields = new ArrayList<TableRecordField>();
				for (int fi = 0; fi < table.getFields().size(); fi++) {
					Field field = table.getFields().get(fi);
					if (field.getFieldConstraint().contains(FieldConstraint.PRIMARY_KEY)) {
						tableRecord.setKey(new PrimaryKey(field.getName(), resultSet.getString(fi + 1)));;
					}
					TableRecordField trf = new TableRecordField(field.getFieldType(), field.getName(),
							resultSet.getString(fi + 1));
					tableRecordFields.add(trf);
					
				}
				tableRecord.setFields(tableRecordFields);
				result.add(tableRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void delete(String tablename, PrimaryKey key) {
		try {
			String query = String.format("DELETE FROM %s WHERE %s = %s", tablename, key.getKey(), key.getValue());
			PreparedStatement statement = sqlConnection.prepareStatement(query);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
