package core;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static DBConnector instance = null;
	private Connection sqlConnection;

	private DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = String.format("jdbc:mysql://%s:%s/%s", Config.MySql.host, Config.MySql.port, Config.MySql.db);
			System.out.println("Connection url: " + url);
			setSqlConnection(DriverManager.getConnection(url, Config.MySql.user, Config.MySql.password));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
			return instance;
		}
		return instance;
	}

	public Connection getSqlConnection() {
		return sqlConnection;
	}

	private void setSqlConnection(Connection sqlConnection) {
		this.sqlConnection = sqlConnection;
	}
}
