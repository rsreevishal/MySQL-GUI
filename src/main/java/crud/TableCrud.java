package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.DBConnector;
import crud.orm.TableORM;
import model.Field;
import model.FieldConstraint;
import model.FieldType;
import model.Table;

public class TableCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;
	private TableORM orm;
	
	public TableCrud(){
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
		orm = new TableORM(sqlConnection);
	}
	
	public Table create(Table table) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_tables(tablename) values(?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, table.getName());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			int pk = 1;
            if(rs != null && rs.next()){
            	pk = rs.getInt(1);
            }
            table.setId(pk);
			for(Field f: table.getFields()) {
				st = sqlConnection.prepareStatement("insert into mysqlgui_table_fields(table_id, fieldtype, fieldname, constraints) values(?, ?, ?, ?)");
				st.setInt(1, pk);
				st.setString(2, f.getFieldType().toString());
				st.setString(3, f.getName());
				StringBuilder constraints = new StringBuilder("");
				for(FieldConstraint fc: f.getFieldConstraint()) {
					constraints.append(fc).append(',');
				}
				st.setString(4, constraints.toString().substring(0, constraints.length() - 1));
				st.executeUpdate();
			}
			orm.create(table);
			return table;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void createForm(int table_id, String form) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_table_forms(table_id, form) values(?, ?);");
			st.setInt(1, table_id);
			st.setString(2, form);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public String getLastForm() {
		String result="<p style='color:red;'>No form found create one to view here..</p>";
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select form from mysqlgui_table_forms order by id desc limit 1;");
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;	
	}
	public void delete(String tablename, int id) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("delete from mysqlgui_tables where id=?;");
			st.setInt(1, id);
			st.executeUpdate();
			orm.delete(tablename);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Table> getAll() {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,tablename from mysqlgui_tables;");
			ResultSet rs = st.executeQuery();
			ArrayList<Table> tables = new ArrayList<Table>();
			while(rs.next()) {
				Table table = this.get(rs.getInt(1));
				tables.add(table);
			}
			return tables;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Table get(int id) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,tablename from mysqlgui_tables where id=?;");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			Table table = new Table();
			if(rs.next()) {
				table.setId(id);
				table.setName(rs.getString(2));
				st = sqlConnection.prepareStatement("select fieldname,fieldtype,constraints from mysqlgui_table_fields where table_id=?");
				st.setInt(1, id);
				ResultSet rs2 = st.executeQuery();
				ArrayList<Field> fields = new ArrayList<Field>();
				while(rs2.next()) {
					Field field = new Field();
					field.setName(rs2.getString(1));
					field.setFieldType(FieldType.valueOf(rs2.getString(2)));
					String[] constrts = rs2.getString(3).split(",");
					ArrayList<FieldConstraint> constraints = new ArrayList<FieldConstraint>();
					for(String s: constrts) {
						constraints.add(FieldConstraint.valueOf(s));
					}
					field.setFieldConstraint(constraints);
					fields.add(field);
				}
				table.setFields(fields);
			}
			return table;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Table get(String tableName) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,tablename from mysqlgui_tables where tablename=?;");
			st.setString(1, tableName);
			ResultSet rs = st.executeQuery();
			Table table = new Table();
			if(rs.next()) {
				int pk = rs.getInt(1);
				table.setId(pk);
				table.setName(rs.getString(2));
				st = sqlConnection.prepareStatement("select fieldname,fieldtype,constraints from mysqlgui_table_fields where table_id=?");
				st.setInt(1, pk);
				ResultSet rs2 = st.executeQuery();
				ArrayList<Field> fields = new ArrayList<Field>();
				while(rs2.next()) {
					Field field = new Field();
					field.setName(rs2.getString(1));
					field.setFieldType(FieldType.valueOf(rs2.getString(2)));
					String[] constrts = rs2.getString(3).split(",");
					ArrayList<FieldConstraint> constraints = new ArrayList<FieldConstraint>();
					for(String s: constrts) {
						constraints.add(FieldConstraint.valueOf(s));
					}
					field.setFieldConstraint(constraints);
					fields.add(field);
				}
				table.setFields(fields);
			}
			return table;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
