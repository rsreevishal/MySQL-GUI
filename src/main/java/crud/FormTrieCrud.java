package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.DBConnector;
import model.Trie;
import model.User;

public class FormTrieCrud {
	private DBConnector dbConnector;
	private Connection sqlConnection;

	public FormTrieCrud() {
		dbConnector = DBConnector.getInstance();
		sqlConnection = dbConnector.getSqlConnection();
	}
	public Trie getAll(User user) {
		Trie trie = new Trie();
		try {
			PreparedStatement st = sqlConnection.prepareStatement("SELECT id,trie FROM mysqlgui_form_tries where user=?;");
			st.setInt(1, user.getId());
			ResultSet result = st.executeQuery();
			while(result.next()) {
				String trieKeyString = result.getString(2);
				ArrayList<ArrayList<String>> trieKey = new ArrayList<ArrayList<String>>();
				for(String row: trieKeyString.split("#")) {
					ArrayList<String> rowList = new ArrayList<String>();
					for(String col: row.split("&")) {
						rowList.add(col);
					}
					trieKey.add(rowList);
				}
				trie.insertAll(trieKey);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return trie;
	}
	public Trie get(String tablename, User user) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("SELECT id,trie FROM mysqlgui_form_tries WHERE id=? AND user=? LIMIT 1;");
			st.setString(1, tablename);
			st.setInt(2, user.getId());
			ResultSet result = st.executeQuery();
			Trie trie = new Trie();
			if(result.next()) {
				String trieKeyString = result.getString(2);
				ArrayList<ArrayList<String>> trieKey = new ArrayList<ArrayList<String>>();
				for(String row: trieKeyString.split("|")) {
					ArrayList<String> rowList = new ArrayList<String>();
					for(String col: row.split(".")) {
						rowList.add(col);
					}
					trieKey.add(rowList);
				}
				trie.insertAll(trieKey);
			}
			return trie;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void add(String tablename, User user, String trie) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("INSERT INTO mysqlgui_form_tries(id,user,trie) values(?,?,?);");
			st.setString(1, tablename);
			st.setInt(2, user.getId());
			st.setString(3, trie);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(String tablename, User user, String trie) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("UPDATE mysqlgui_form_tries SET trie=? WHERE id=? AND user=?;");
			st.setString(1, trie);
			st.setString(2, tablename);
			st.setInt(3, user.getId());
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(String tablename, User user) {
		try {
			PreparedStatement st = sqlConnection.prepareStatement("DELETE FROM mysqlgui_form_tries WHERE id=? AND user=?;");
			st.setString(1, tablename);
			st.setInt(2, user.getId());
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
