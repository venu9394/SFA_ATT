package com.hhcl.mail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class conn {
	private static conn instance = new conn();
	/*public static final String URL = "jdbc:mysql://192.168.30.223:3306/";
	public static final String USER = "hcluser";
	public static final String PASSWORD = "test!@#";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	*/
	public static final String URL = "jdbc:mysql://192.168.30.105:3306/";
	public static final String USER = "hcluser";
	public static final String PASSWORD = "hcluser!23";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	
	
	
	
	// private constructor
	private conn() {
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
