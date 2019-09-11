package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Factory Object[Method] Pattern
 *
 */
public class ConnectionFactory {
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbInfo");
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");

		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e1) {
			throw new RuntimeException();
		}
	}

	static String url;
	static String user;
	static String password;

	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
