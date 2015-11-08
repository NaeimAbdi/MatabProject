package org.bihe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * this class connect system to database
 * 
 */
public class DBManager {
	private static final String DB_PORTOCOL = "jdbc:mysql://";
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_SCHEMA = "matab";

	private static final String DB_USER = "root";
	private static final String DB_PASS = "123";

	private static final String DB_DRIVERNAME = "com.mysql.jdbc.Driver";

	private static Connection connection;

	/**
	 * this method connect our system to database
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(DB_DRIVERNAME);

				String url = DB_PORTOCOL + DB_IP + ":" + DB_PORT + "/"
						+ DB_SCHEMA;
				connection = DriverManager.getConnection(url, DB_USER, DB_PASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
