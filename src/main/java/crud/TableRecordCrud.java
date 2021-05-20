package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.tools.javac.util.List;

import core.DBConnector;
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
}
