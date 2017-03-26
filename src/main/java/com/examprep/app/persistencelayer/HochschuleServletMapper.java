package com.examprep.app.persistencelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class HochschuleServletMapper {
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	@SuppressWarnings("finally")
	public static Hochschule createHochschule(String name, String stadt) {

		Hochschule hochschule = new Hochschule();
		hochschule.setName(name);
		hochschule.setStadt(stadt);

		Dao<Hochschule, String> hochschuleDao;

		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			hochschuleDao.create(hochschule);
		} catch (SQLException e) {

		} finally {
			return hochschule;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Hochschule> getAllHochschulen(){
		List<Hochschule> listHochschule = new ArrayList<>();
		Dao<Hochschule, String> hochschuleDao;
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			listHochschule = hochschuleDao.queryForAll();
		} catch (SQLException e) {

		} finally {
			return listHochschule;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Hochschule> searchForHochschulenByName(String name){
		List<Hochschule> listHochschule = new ArrayList<>();
		Dao<Hochschule, String> hochschuleDao;
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			listHochschule = hochschuleDao.queryBuilder().where().eq("name", name).query();
		} catch (SQLException e) {

		} finally {
			return listHochschule;
		}
	}
}
