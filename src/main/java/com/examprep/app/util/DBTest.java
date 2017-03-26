package com.examprep.app.util;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.examprep.app.bean.*;
import com.examprep.app.persistencelayer.PersistenceQuery;



public class DBTest {
	private static String connURL = "jdbc:mysql://rds-mysql-10-min-tutorial.cn3d9reirkc0.eu-central-1.rds.amazonaws.com:3306/MyTestDB";
	private static String connUsername = "masterUsername";
	private static String connPassword = "masterUsername";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersistenceQuery.createModul("new Modul");
	}

}
