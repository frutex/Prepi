package com.examprep.app.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class NutzerDAO {

	
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();
	
	public NutzerDAO(){
		super();
	}
	
	public static Nutzer createNutzer(String name, String vorname, String email, String password, Hochschule hochschule){
		CryptoHelpClass cryp = new CryptoHelpClass();
		
		Nutzer nutzer = new Nutzer();
		
		password = cryp.generateHashPassword(email, password);
		nutzer.setEmail(email);
		nutzer.setName(name);
		nutzer.setVorname(vorname);
		nutzer.setPassword(password);
		nutzer.setHochschule(hochschule);
		
		Dao<Nutzer, String> nutzerDao;
		try {
			nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
			nutzerDao.create(nutzer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return nutzer;
	}
	
	public static Nutzer getNutzerById(String id){
		Nutzer nutzer = null;
		try {
			Dao<Nutzer, String> nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
			nutzer = nutzerDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nutzer;
	}
	
	public static List<Nutzer> getNutzerByName(String name){
		
		List<Nutzer> nutzerList = new ArrayList<>();
		try {
			Dao<Nutzer, String> nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
			nutzerList = nutzerDao.queryBuilder().where().eq("email", name).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nutzerList;
	}
	
	
	
}
