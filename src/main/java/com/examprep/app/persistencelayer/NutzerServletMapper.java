package com.examprep.app.persistencelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class NutzerServletMapper {

	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	public static Nutzer createNutzer(String name, String vorname, String email, String password,
			Hochschule hochschule) {
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

	public static Nutzer getNutzerById(String id) {
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

	public static List<Nutzer> getNutzerByName(String name) {

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

	public static Nutzer getOneNutzerByName(String name) {
		return getNutzerByName(name).get(0);
	}

	@SuppressWarnings("finally")
	public static int getAllLikesForRegisteredQuestionsForOneUser(String name) {
		Nutzer nutzer = getNutzerById(name);
		List<KlausurFrage> listFrage = new ArrayList<>();
		int cred = 0;
		try {
			Dao<KlausurFrage, String> frageDao = DaoManager.createDao(connSource, KlausurFrage.class);
			listFrage = frageDao.queryBuilder().where().eq("nk_id", nutzer).query();
			Dao<Credibility, String> credDao = DaoManager.createDao(connSource, Credibility.class);
			List<Credibility> credList = new ArrayList<>();
			for (KlausurFrage tmp : listFrage) {
				credList = credDao.queryBuilder().where().eq("kfgc_id", tmp.getF_id()).query();
				cred = cred + credList.size();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			return cred;
		}
	}

	public static List<KlausurFrage> getAllLikedQuestionsForOneUser(String name) {
		List<KlausurFrage> listFrage = new ArrayList<>();
		Nutzer nutzer = getNutzerById(name);
		List<Credibility> credList = new ArrayList<>();
		try {
			Dao<Credibility, String> credDao = DaoManager.createDao(connSource, Credibility.class);
			credList = credDao.queryBuilder().where().eq("nutgc_id", nutzer.getN_id()).query();

			for (Credibility cred : credList) {
				listFrage.add(cred.getKlausurf_id());
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listFrage;
	}

}
