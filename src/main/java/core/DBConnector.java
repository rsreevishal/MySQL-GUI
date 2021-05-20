package core;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static DBConnector instance = null;
	private Connection sqlConnection;
	private DBConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}'
			setSqlConnection(DriverManager.getConnection("jdbc:mysql://172.19.0.3:3306/app", "root", "root"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DBConnector getInstance() {
		if(instance == null) {
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
