package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import core.DBConnector;
import expression.FormExpr;
import expression.FormInputExpr;
import expression.IdToken;
import expression.InputType;
import expression.ListToken;
import model.TableQueryType;
import model.User;

public class FormCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;

	public FormCrud() {
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
	}

	ArrayList<FormExpr> getAll(User user) {
		try {
			HashMap<Integer, FormExpr> forms = new HashMap<Integer, FormExpr>();
			PreparedStatement st = sqlConnection.prepareStatement(
					"select fi.type, fi.table_id, t.tablename, fi.name, fi.field, fi.link, fi.args from mysqlgui_form_inputs as fi join mysqlgui_tables as t on fi.table_id = t.id where t.user=?;");
			st.setInt(1, user.getId());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				TableQueryType type = TableQueryType.valueOf(rs.getString(1));
				switch (type) {
				case FORM: {
					int pk = rs.getInt(2);
					if (!forms.containsKey(pk)) {
						FormExpr form = new FormExpr();
						form.idToken = new IdToken(rs.getString(3));
						form.formInputs = new ArrayList<FormInputExpr>();
						forms.put(pk, form);
					}
					forms.get(pk).addInput(
							new FormInputExpr(new IdToken(rs.getString(5)), InputType.valueOf(rs.getString(6)),
									new ListToken(Arrays.asList(rs.getString(7).split(",")))));
					break;
				}
				case VIEW: {
					break;
				}
				}
			}
			if(!forms.isEmpty()) {
				return new ArrayList<FormExpr>(forms.values());
			} else {
				return new ArrayList<FormExpr>();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
