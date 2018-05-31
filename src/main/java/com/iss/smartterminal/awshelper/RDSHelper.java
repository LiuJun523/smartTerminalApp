package com.iss.smartterminal.awshelper;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class RDSHelper {

	private static Connection conn = null;

	public static Connection getConnection() {
		
		if (null == conn) {
			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream(
						RDSHelper.class.getClassLoader().getResource("properties/mysql.properties").getPath()));

				String JDBC_DRIVER = prop.getProperty("driver");
				String DB_URL = prop.getProperty("url");
				String USER = prop.getProperty("username");
				String PASS = prop.getProperty("password");
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
