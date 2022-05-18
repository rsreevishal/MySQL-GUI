package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.DBConnector;
import crud.orm.TableORM;
import expression.ConditionExpr;
import expression.FormReportExpr;
import expression.IdToken;
import expression.InputType;
import expression.ListToken;
import expression.FormExpr;
import expression.FormInputExpr;
import model.Field;
import model.FieldConstraint;
import model.FieldType;
import model.Table;
import model.TableQueryType;
import model.User;

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
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_tables(tablename, user) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, table.getName());
			st.setInt(2, table.getUser().getId());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			int pk = 1;
            if(rs != null && rs.next()){
            	pk = rs.getInt(1);
            }
            table.setId(pk);
			orm.create(table);
			for(Field field: table.getFields()) {
				st = sqlConnection.prepareStatement("insert into mysqlgui_table_fields(table_id, fieldtype, fieldname, constraints) values(?, ?, ?, ?)");
				st.setInt(1, table.getId());
				st.setString(2, field.getFieldType().toString());
				st.setString(3, field.getName());
				StringBuilder constraints = new StringBuilder("");
				for(FieldConstraint fc: field.getFieldConstraint()) {
					constraints.append(fc).append(',');
				}
				if(constraints.length() > 0) {
					st.setString(4, constraints.toString().substring(0, constraints.length() - 1));
				} else {
					st.setString(4, "");
				}
				st.executeUpdate();
			}
			return table;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createTableField(Table table, Field field) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_table_fields(table_id, fieldtype, fieldname, constraints) values(?, ?, ?, ?)");
			st.setInt(1, table.getId());
			st.setString(2, field.getFieldType().toString());
			st.setString(3, field.getName());
			StringBuilder constraints = new StringBuilder("");
			for(FieldConstraint fc: field.getFieldConstraint()) {
				constraints.append(fc).append(',');
			}
			if(constraints.length() > 0) {
				st.setString(4, constraints.toString().substring(0, constraints.length() - 1));
			} else {
				st.setString(4, "");
			}
			st.executeUpdate();
			orm.addTableColumn(table.getName(), field);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public ArrayList<Table> getAll(User user) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,tablename from mysqlgui_tables where user=?;");
			st.setInt(1, user.getId());
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
	
	public Table get(String tableName, User user) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,tablename from mysqlgui_tables where tablename=? and user=?;");
			st.setString(1, tableName);
			st.setInt(2, user.getId());
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
	
	public void createFormField(int table_id, String tablename, FormInputExpr fie) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_form_inputs(table_id, field, link, args, type, name) values(?, ?, ?, ?, ?, ?);");
			st.setInt(1, table_id);
			st.setString(2, fie.idToken.id);
			st.setString(3, fie.inputType.toString());
			String args = "";
			for(String arg: fie.args.values) {
				args += String.format(",'%s'", arg);
			}
			st.setString(4, args.substring(1));
			st.setString(5, TableQueryType.FORM.toString());
			st.setString(6, tablename);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public FormInputExpr getFormField(int table_id, String fieldName) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select field, link, args from mysqlgui_form_inputs where table_id=? and field=? limit 1;");
			st.setInt(1, table_id);
			st.setString(2, fieldName);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				FormInputExpr fie = new FormInputExpr(new IdToken(result.getString(1)),
						InputType.valueOf(result.getString(2)), new ListToken(ListToken.fromString(result.getString(3))));
				return fie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveFormQuery(int table_id, FormExpr expr) {
		for(FormInputExpr fie : expr.formInputs) {
			createFormField(table_id, expr.idToken.id, fie);
		}
	}
	
	public void changeArgs(int table_id, FormInputExpr expr) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("update mysqlgui_form_inputs set args=? where table_id=? and field=? and type='FORM';");
			st.setString(1, expr.args.toString());
			st.setInt(2, table_id);
			st.setString(3, expr.idToken.id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void changeType(int table_id, FormInputExpr expr) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("update mysqlgui_form_inputs set link=? where table_id=? and field=? and type='FORM';");
			st.setString(1, expr.inputType.toString());
			st.setInt(2, table_id);
			st.setString(3, expr.idToken.id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFormQuery(int table_id, FormReportExpr expr) {
		try {
			for(ConditionExpr ce : expr.conditions) {
				PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_form_inputs(table_id, field, link, args, type, name) values(?, ?, ?, ?, ?, ?);");
				st.setInt(1, table_id);
				st.setString(2, ce.colName.id);
				st.setString(3, ce.operatorExpr.operator);
				st.setString(4, "'" + ce.colValue.text + "'");
				st.setString(5, TableQueryType.VIEW.toString());
				st.setString(6, expr.table.id);
				st.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Field getField(String tablename, User user, String field) {
		Table table = this.get(tablename, user);
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
