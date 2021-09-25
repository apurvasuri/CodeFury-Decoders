package com.hsbc.btsapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hsbc.btsapp.helpers.PropertiesHelper;


public class ConnectionUtils {

	private static Connection con;
	private static String conurl;
	private static String dbDriver;
	private static String username;
	private static String password;

	static  {
		PropertiesHelper helper = new PropertiesHelper();
		conurl = helper.getProperty("CONURL");
		dbDriver = helper.getProperty("DRIVERCLASSNAME");
		username = helper.getProperty("USERNAME");
		password = helper.getProperty("PASSWORD");

		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {

			con = DriverManager.getConnection(conurl, username, password);

			System.out.println("-------------Connected to DB--------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {
		try {
			con.close();
			System.out.println("----------------Closed the connection inside utility--------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

