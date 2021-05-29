package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StartUp {
	public static void call() {
		System.out.println("Starting to initialize database");
		DBConnector dbConnector = DBConnector.getInstance();
		Connection sqlConnection = dbConnector.getSqlConnection();
		try {
			PreparedStatement st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_users(id INT NOT NULL AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50), PRIMARY KEY (id));");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_tables(id INT NOT NULL AUTO_INCREMENT, tablename VARCHAR(50), PRIMARY KEY (id));");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_table_fields(table_id INT, fieldname VARCHAR(50), fieldtype VARCHAR(50), constraints VARCHAR(50), FOREIGN KEY (table_id) REFERENCES mysqlgui_tables(id) ON DELETE CASCADE);");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_table_forms(id INT NOT NULL AUTO_INCREMENT, table_id INT, form VARCHAR(5000), PRIMARY KEY (id), FOREIGN KEY (table_id) REFERENCES mysqlgui_tables(id) ON DELETE CASCADE);");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
