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
			PreparedStatement st = sqlConnection.prepareStatement("CREATE DATABASE IF NOT EXISTS mysqlgui;");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_users(id INT NOT NULL AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50), PRIMARY KEY (id));");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_tables(id INT NOT NULL AUTO_INCREMENT, user INT, tablename VARCHAR(50), PRIMARY KEY (id), FOREIGN KEY (user) REFERENCES mysqlgui_users(id) ON DELETE CASCADE);");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_table_fields(id INT NOT NULL AUTO_INCREMENT, table_id INT, fieldname VARCHAR(50), fieldtype VARCHAR(50), constraints VARCHAR(50), PRIMARY KEY(id), FOREIGN KEY (table_id) REFERENCES mysqlgui_tables(id) ON DELETE CASCADE);");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_form_inputs(id INT NOT NULL AUTO_INCREMENT, table_id INT, name VARCHAR(50), field VARCHAR(50), link VARCHAR(50), args VARCHAR(100), type VARCHAR(50), PRIMARY KEY(id), FOREIGN KEY (table_id) REFERENCES mysqlgui_tables(id) ON DELETE CASCADE);");
			st.executeUpdate();
			st = sqlConnection.prepareStatement("CREATE TABLE IF NOT EXISTS mysqlgui_form_tries(id VARCHAR(50) NOT NULL, user INT NOT NULL, trie VARCHAR(500), PRIMARY KEY(id, user));");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
