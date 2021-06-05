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
import model.TableQuery;
import model.TableQueryType;

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

	public String getLastQueries(int count, TableQueryType type) {
		String result = "";
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select query from mysqlgui_form_query where type = ? order by id desc limit ?;");
			st.setString(1, type.toString());
			st.setInt(2, count);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				result += (rs.getString(1) + "\n");
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
			} else {
				return null;
			}
			return table;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveFormQuery(int table_id, String name, String query, String type) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_form_query(table_id, name, query, type) values(?, ?, ?, ?);");
			st.setInt(1, table_id);
			st.setString(2, name);
			st.setString(3, query);
			st.setString(4, type);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TableQuery> getAllFormQuery() {
		ArrayList<TableQuery> result = new ArrayList<TableQuery>();
		try {
			PreparedStatement st = sqlConnection.prepareStatement(String.format("select id,table_id,name,query,type from mysqlgui_form_query;"));
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				TableQuery query = new TableQuery();
				query.setId(rs.getInt(1));
				query.setTable_id(rs.getInt(2));
				query.setName(rs.getString(3));
				query.setQuery(rs.getString(4));
				query.setType(TableQueryType.valueOf(rs.getString(5)));
				result.add(query);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	public Field getField(String tablename, String field) {
		Table table = this.get(tablename);
		if(table != null) {
			for(Field f: table.getFields()) {
				if(f.getName().equals(field)) {
					return f;
				}
			}
		}
		return null;
	}
}
