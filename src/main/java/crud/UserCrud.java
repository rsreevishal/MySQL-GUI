package crud;
import java.sql.*;
import core.DBConnector;
import model.User;

public class UserCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;
	public UserCrud(){
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
	}
	
	public User authenticateUser(String username, String password){
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,username,password from mysqlgui_users where username = ? and password = ?");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void create(String username, String password) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("insert into mysqlgui_users(username, password) values(?,?);");
			st.setString(1, username);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User get(int id) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("select id,username,password from mysqlgui_users where id = ?;");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
