package com.examprep.app.persistencelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class PersistenceQuery {
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	static Dao<Credibility, String> credDao;
	static Dao<Hochschule, String> hochschuleDao;
	static Dao<Modul, String> modulDao;
	static Dao<Nutzer, String> nutzerDao;
	static Dao<KlausurFrage, String> klausurfDao;
	
	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Credibility
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */


	@SuppressWarnings("finally")
	public static Credibility createCredibility(KlausurFrage kfrage, Nutzer nutzer) {
		Credibility cred = new Credibility();
		cred.setKlausurf_id(kfrage);
		cred.setNutzc_id(nutzer);

		try {
			credDao = DaoManager.createDao(connSource, Credibility.class);
			credDao.create(cred);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			return cred;
		}
	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Hochschule
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */


	@SuppressWarnings("finally")
	public static Hochschule createHochschule(String name, String stadt) {

		Hochschule hochschule = new Hochschule();
		hochschule.setName(name);
		hochschule.setStadt(stadt);

		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			hochschuleDao.create(hochschule);
		} catch (SQLException e) {

		} finally {
			return hochschule;
		}
	}

	@SuppressWarnings("finally")
	public static List<Hochschule> getAllHochschulen() {
		List<Hochschule> listHochschule = new ArrayList<>();
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			listHochschule = hochschuleDao.queryForAll();
		} catch (SQLException e) {

		} finally {
			return listHochschule;
		}
	}

	@SuppressWarnings("finally")
	public static List<Hochschule> searchForHochschulenByName(String name) {
		List<Hochschule> listHochschule = new ArrayList<>();
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			listHochschule = hochschuleDao.queryBuilder().where().eq("name", name).query();
		} catch (SQLException e) {

		} finally {
			return listHochschule;
		}
	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Klausurfrage
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	public static KlausurFrage createKlausurfrage(int schwierigkeit, String text, int jahr, Hochschule hochschule,
			Dozent dozent, Modul modul, Nutzer nutzer) {

		KlausurFrage klausurf = new KlausurFrage();

		klausurf.setSchwierigkeit(schwierigkeit);
		klausurf.setText(text);
		klausurf.setJahr(jahr);
		klausurf.setHochschule(hochschule);
		klausurf.setDozent(dozent);
		klausurf.setModul(modul);
		klausurf.setNutzer(nutzer);

		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
		} catch (SQLException e) {

		}

		// Dao<Nutzer, String> nutzerDao;
		// try {
		// nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
		// nutzerDao.create(nutzer);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return klausurf;
	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Modul
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */


	@SuppressWarnings("finally")
	public static Modul createModul(String modulName) {
		Modul modul = new Modul(modulName);
		try {
			Dao<Modul, String> modulDao = DaoManager.createDao(connSource, Modul.class);
			modulDao.create(modul);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			;
			e.printStackTrace();
		} finally {
			int id = modul.getM_id();
			if (id == 0) {
				return null;
			} else {
				System.out.println(id);
				return modul;
			}
		}

	}

	public static Modul searchModulById(String id) {
		Modul modul = null;
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			modul = modulDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modul;

	}

	public static List<Modul> getAllModule() {
		List<Modul> moduleList = new ArrayList<>();
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moduleList;
	}

	public static List<Modul> searchModulByName(String name) {

		List<Modul> moduleList = new ArrayList<>();
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryBuilder().where().eq("modul", name).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moduleList;

	}
	
	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Nutzer
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */
	
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
			nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
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
