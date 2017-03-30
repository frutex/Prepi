package com.examprep.app.util;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Persistence;

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
//		Modul modul = PersistenceQuery.createModul("new Modul");
//		Dozent dozent = PersistenceQuery.createDozent("Hans", "Obst");
//		Hochschule hochschule = PersistenceQuery.createHochschule("HWR Berlin", "Berlin");
//		Nutzer nutzer = PersistenceQuery.createNutzer("Obst", "Bjarne", "bjarne.obst@gmail.com", "password", hochschule);
//		Modul modul = PersistenceQuery.getModulByName("Business Intelligenze").get(0);
//		Dozent dozent = PersistenceQuery.getDozentByName("Hans", "Obst");
//		Hochschule hochschule = PersistenceQuery.getHochschuleByName("HWR Berlin").get(0);
//		Nutzer nutzer = PersistenceQuery.getOneNutzerByName("bjarne.obst@gmail.com");
		List<Dozent> dozList = PersistenceQuery.getAllDozentenEinerHochschule("HWR Berlin");
//		System.out.println(modul.getModul());
//		System.out.println(dozent.getVorname());
//		System.out.println(hochschule.getName());
//		System.out.println(nutzer.getEmail());
		
//		PersistenceQuery.createKlausurfrage("Wie funktioniert dieses Programm?", 2017, hochschule, dozent, modul, nutzer);
		for(Dozent tmp : dozList){
			System.out.println(tmp.getVorname());
		}
		
	}

}
