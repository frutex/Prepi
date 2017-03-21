package com.examprep.app.util;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.examprep.app.bean.*;
import com.examprep.app.dao.HochschuleDAO;
import com.examprep.app.dao.ModulDAO;
import com.examprep.app.dao.NutzerDAO;

public class DBTest {
	private static String connURL = "jdbc:mysql://rds-mysql-10-min-tutorial.cn3d9reirkc0.eu-central-1.rds.amazonaws.com:3306/MyTestDB";
	private static String connUsername = "masterUsername";
	private static String connPassword = "masterUsername";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ConnectionSource connectionSource = null;
//		try {
//			connectionSource = new JdbcConnectionSource(connURL, connUsername, connPassword);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Modul mod1 = new Modul("TestModul");
//		Dao<Modul, String> accountDao = null;
//        try {
//			accountDao =
//			        DaoManager.createDao(connectionSource, Modul.class);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//        try {
//			accountDao.create(mod1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ModulDAO.createModul("TESTIBESTI");
		
//		List<Modul> moduleList = ModulDAO.getAllModule();
//		
//		for(int i = 0; i < moduleList.size(); i ++){
//			System.out.println(moduleList.get(i).getModul());
//		}
//		System.out.println(ModulDAO.searchModulById("5").getModul());
//		
//		moduleList = ModulDAO.searchModulByName("TestModul");
//		System.out.println(moduleList.size());
//		for(int i = 0; i < moduleList.size(); i ++){
//			System.out.println(moduleList.get(i).getModul());
//		}
		
		Hochschule hochschule = HochschuleDAO.createHochschule("HWR Berlin", "Berlin");
		
		Nutzer nutzer = NutzerDAO.createNutzer("Obst", "Bjarne", "bjarne.obst@de.ibm.com", "password", hochschule);
		
		
	}

}
