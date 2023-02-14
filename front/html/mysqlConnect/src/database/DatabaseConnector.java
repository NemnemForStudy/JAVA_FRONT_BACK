package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	private static final String DB_URL ="jdbc:mysql://127.0.0.1:3306/peed";
	private static final String USER_NAME = "root";
	private static final String USER_PASSWORD = "root";
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
		} catch (Exception exception) {
			return null;
		} finally {
			
		}
		
	}
}
