package com.examprep.app.util;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class SessionFactory {

	
	private static String connURL = "jdbc:mysql://rds-mysql-10-min-tutorial.cn3d9reirkc0.eu-central-1.rds.amazonaws.com:3306/MyTestDB";
	private static String connUsername = "masterUsername";
	private static String connPassword = "masterUsername";
	ConnectionSource connSource = null;
	
	public SessionFactory(){
		
	}
	
	public ConnectionSource createConnection(){
		try {
			connSource = new JdbcConnectionSource(connURL, connUsername, connPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connSource;
	}
}
