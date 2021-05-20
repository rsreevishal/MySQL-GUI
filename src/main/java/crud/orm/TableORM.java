package crud.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Field;
import model.FieldConstraint;
import model.FieldType;
import model.Table;

public class TableORM {
	private Connection sqlConnection;

	public TableORM(Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}

	public void create(Table table) {
		try {
			StringBuilder columns = new StringBuilder("");
			for (Field f : table.getFields()) {
				columns.append(f.getName() + " " + FieldType.filter(f.getFieldType()) + ",");
			}
			String query = String.format("CREATE TABLE %s(%s);", table.getName(),
					columns.toString().substring(0, columns.length() - 1));
			System.out.println("Query: " + query);
			PreparedStatement st = sqlConnection.prepareStatement(query);
			st.executeUpdate();
			for (Field f : table.getFields()) {
				for (FieldConstraint fc : f.getFieldConstraint()) {
					this.addConstraints(table.getName(), f.getName(), fc, f.getFieldType());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String tablename) {
		try {
			String query = String.format("DROP TABLE %s;", tablename);
			PreparedStatement st = sqlConnection.prepareStatement(query);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void addConstraints(String tablename, String fieldname, FieldConstraint constraint, FieldType fieldtype) {
		try {
			if (constraint != FieldConstraint.NONE) {
				String query = null;
				switch (constraint) {
					case PRIMARY_KEY: {
						query = String.format("ALTER TABLE %s ADD CONSTRAINT %s %s (%s);", tablename, "pk_" + tablename,
								"PRIMARY KEY", fieldname);
						break;
					}
					case UNIQUE: {
						query = String.format("ALTER TABLE %s ADD UNIQUE (%s);", tablename, fieldname);
						break;
					}
					case NOT_NULL: {
						query = String.format("ALTER TABLE %s MODIFY %s %s NOT NULL;", tablename, fieldname, FieldType.filter(fieldtype));	
						break;
					}
					case NONE: {
						break;
					}
					default: {
					}
				}
				if(query != null) {
					System.out.println("Query: " + query);
					PreparedStatement st = sqlConnection.prepareStatement(query);
					st.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
