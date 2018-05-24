package com.iss.smartterminal.extra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.iss.smartterminal.entity.User;

public class DemoMysql {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/cpdb";

	static final String USER = "root";
	static final String PASS = "12345678";
	private static Connection conn = null;

	private static Connection getConnection() {
		if (null == conn) {
			try {
				Class.forName(JDBC_DRIVER);
				System.out.println("connect to db...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static int addUser(User user) {

		int result = -1;
		Statement stmt = null;
		if (null == user) {
			return result;
		}
		try {
			stmt = getConnection().createStatement();
			String sql = String.format("insert into t_user(user_id,user_name,user_password) values('%s','%s','%s')",
					user.getUserId(), user.getUserName(), user.getUserPassword());
			int rs = stmt.executeUpdate(sql);
			result = rs > 0 ? 1 : 0;
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static User getUser(String id) {

		Statement stmt = null;
		User user = null;
		try {
			stmt = getConnection().createStatement();
			String sql;
			sql = "SELECT user_id, user_name, user_password FROM t_user where user_id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String user_id = rs.getString("user_id");
				String name = rs.getString("user_name");
				String pwd = rs.getString("user_password");
				user = new User();
				user.setUserId(user_id);
				user.setUserName(name);
				user.setUserPassword(pwd);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static void main(String[] args) {

		User u1 = getUser("1");
		User u2 = getUser("2");
		System.out.println(u1);
		System.out.println(u2);
		User u3 = new User("a", "test", "test");
		System.out.println(addUser(u3));
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
