package com.hhcl.mail.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sfa_connection {
	private static sfa_connection instance = new sfa_connection();
	public static final String URL = "jdbc:sqlserver://localhost:1433";
	public static final String USER = "sa";
	public static final String PASSWORD = "123";
	public static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// private constructor
	private sfa_connection() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}
 
}
