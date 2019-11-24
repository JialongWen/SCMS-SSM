package com.javaee.scms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASS = "1234";
	private static final String URL = "jdbc:mysql://localhost:3306/scms";
	
	private static Connection connection;
	
	static{
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("数据库连接成功！");
		} catch (ClassNotFoundException e) {
			System.out.println("驱动类未找到！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接异常！");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
}
