package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import core.DBConnector;
import expression.ConditionExpr;
import expression.FormReportExpr;
import expression.IdToken;
import expression.OperatorExpr;
import expression.TextToken;
import model.TableQueryType;

public class FormReportCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;

	public FormReportCrud() {
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
	}

	ArrayList<FormReportExpr> getAll() {
		try {
			HashMap<Integer, FormReportExpr> reports = new HashMap<Integer, FormReportExpr>();
			PreparedStatement st = sqlConnection.prepareStatement(
					"select fi.type, fi.table_id, t.tablename, fi.name, fi.field, fi.link, fi.args from mysqlgui_form_inputs as fi join mysqlgui_tables as t on fi.table_id = t.id;");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				TableQueryType type = TableQueryType.valueOf(rs.getString(1));
				switch (type) {
				case FORM: {
					break;
				}
				case VIEW: {
					int pk = rs.getInt(2);
					if (!reports.containsKey(pk)) {
						FormReportExpr report = new FormReportExpr();
						report.table = new IdToken(rs.getString(3));
						report.name = new IdToken(rs.getString(4));
						report.conditions = new ArrayList<ConditionExpr>();
						reports.put(pk, report);
					}
					reports.get(pk).addCondition(new ConditionExpr(new IdToken(rs.getString(5)),
							new TextToken(rs.getString(7)), new OperatorExpr(rs.getString(6))));
					break;
				}
				}
			}
			if(!reports.isEmpty()) {
				return new ArrayList<FormReportExpr>(reports.values());
			} else {
				return new ArrayList<FormReportExpr>();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
