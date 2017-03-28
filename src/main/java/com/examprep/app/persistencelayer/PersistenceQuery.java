package com.examprep.app.persistencelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.DozentAnHochschule;
import com.examprep.app.bean.DozentUnterrichtetModul;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.ModulAnHochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.daoif.ModulAnHochschuleDao;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

public class PersistenceQuery {

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Create Connection Source
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * DAOs
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	static Dao<Credibility, String> credDao;
	static Dao<Hochschule, String> hochschuleDao;
	static Dao<Modul, String> modulDao;
	static Dao<Nutzer, String> nutzerDao;
	static Dao<KlausurFrage, String> klausurfDao;
	static Dao<Dozent, String> dozentDao;
	static Dao<DozentAnHochschule, String> dozentAnHochschuleDao;
	static Dao<DozentUnterrichtetModul, String> dozentUnterrichtetModulDao;
	static Dao<ModulAnHochschule, String> modulAnHochschuleDao;
	
	
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
	 * Part for Dozent
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	public static Dozent createDozent(String vorname, String nachname) {
		Dozent doz = new Dozent();

		doz.setVorname(vorname);
		doz.setNachname(nachname);

		try {
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			dozentDao.create(doz);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doz;
	}

	public static List<Dozent> getAllDozenten() {
		List<Dozent> dozList = new ArrayList<>();

		try {
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			dozList = dozentDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dozList;

	}

	public static Dozent getDozentByName(String vorname, String nachname) {
		List<Dozent> doz = new ArrayList<>();

		try {
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			// doz = dozentDao.queryBuilder().where().and(where.eq("vorname",
			// vorname), where. ));

			QueryBuilder<Dozent, String> queryBuilder = dozentDao.queryBuilder();
			// get the WHERE object to build our query
			Where<Dozent, String> where = queryBuilder.where();
			where.like("vorname", vorname);
			where.and();
			where.like("nachname", nachname);

			PreparedQuery<Dozent> preparedQuery = queryBuilder.prepare();
			doz = dozentDao.query(preparedQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doz.get(0);
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
	public static List<Hochschule> getHochschuleByName(String name) {
		List<Hochschule> listHochschule = new ArrayList<>();
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			listHochschule = hochschuleDao.queryBuilder().where().eq("name", name).query();
		} catch (SQLException e) {

		} finally {
			return listHochschule;
		}
	}

	@SuppressWarnings("finally")
	public static List<Modul> getAllModuleEinerHochschule(String name){
		Hochschule hochschule = getHochschuleByName(name).get(0);
		List<ModulAnHochschule> modAnHochList = new ArrayList<>();
		List<Modul> modList = new ArrayList<>();
		try {
			modulAnHochschuleDao = DaoManager.createDao(connSource, ModulAnHochschule.class);
			modAnHochList = modulAnHochschuleDao.queryBuilder().where().eq("hochschule_id", hochschule.getH_id()).query();
			modulDao = DaoManager.createDao(connSource, Modul.class);
			for (ModulAnHochschule tmp : modAnHochList){
				modList.add(tmp.getModul());
			}
		} catch (SQLException e) {

		} finally {
			return modList;
		}
	}
	
	@SuppressWarnings("finally")
	public static List<Dozent> getAllDozentenEinerHochschule(String name){
		Hochschule hochschule = getHochschuleByName(name).get(0);
		List<DozentAnHochschule> modAnHochList = new ArrayList<>();
		List<Dozent> dozList = new ArrayList<>();
		try {
			dozentAnHochschuleDao = DaoManager.createDao(connSource, DozentAnHochschule.class);
			modAnHochList = dozentAnHochschuleDao.queryBuilder().where().eq("hochschule_id", hochschule.getH_id()).query();
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			for (DozentAnHochschule tmp : modAnHochList){
				dozList.add(tmp.getDozent());
			}
		} catch (SQLException e) {

		} finally {
			return dozList;
		}
	}
	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Klausurfrage
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	public static KlausurFrage createKlausurfrage(String text, int jahr, Hochschule hochschule, Dozent dozent,
			Modul modul, Nutzer nutzer) {

		KlausurFrage klausurf = new KlausurFrage();

		klausurf.setText(text);
		klausurf.setJahr(jahr);
		klausurf.setHochschule(hochschule);
		klausurf.setDozent(dozent);
		klausurf.setModul(modul);
		klausurf.setNutzer(nutzer);

		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			klausurfDao.create(klausurf);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	@SuppressWarnings("finally")
	public static Modul createModulWithHochschule(String modulName, Hochschule hochschule) {
		Modul modul = new Modul(modulName);
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			modulDao.create(modul);
			modulAnHochschuleDao = DaoManager.createDao(connSource, ModulAnHochschule.class);
			ModulAnHochschule modanh = new ModulAnHochschule(modul, hochschule);
			modulAnHochschuleDao.create(modanh);
				
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
	
	@SuppressWarnings("finally")
	public static Modul createModulWithDozent(String modulName, Dozent dozent) {
		Modul modul = new Modul(modulName);
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			modulDao.create(modul);
			dozentUnterrichtetModulDao = DaoManager.createDao(connSource, DozentUnterrichtetModul.class);
			DozentUnterrichtetModul modwithdoz = new DozentUnterrichtetModul(dozent, modul);
			dozentUnterrichtetModulDao.create(modwithdoz);
				
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
	
	@SuppressWarnings("finally")
	public static Modul createModulWithDozentAndHochschule(String modulName, Dozent dozent, Hochschule hochschule) {
		Modul modul = new Modul(modulName);
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			modulDao.create(modul);
			
			modulAnHochschuleDao = DaoManager.createDao(connSource, ModulAnHochschule.class);
			ModulAnHochschule modanhoch = new ModulAnHochschule(modul, hochschule);
			modulAnHochschuleDao.create(modanhoch);
			
			dozentUnterrichtetModulDao = DaoManager.createDao(connSource, DozentUnterrichtetModul.class);
			DozentUnterrichtetModul dozUModul = new DozentUnterrichtetModul(dozent, modul);
			dozentUnterrichtetModulDao.create(dozUModul);
				
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

	public static List<Modul> getModulByName(String name) {

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
		Nutzer nutzer = getOneNutzerByName(name);
		List<KlausurFrage> listFrage = new ArrayList<>();
		int cred = 0;
		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			listFrage = klausurfDao.queryBuilder().where().eq("nk_id", nutzer).query();
			credDao = DaoManager.createDao(connSource, Credibility.class);
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
		Nutzer nutzer = getOneNutzerByName(name);
		List<Credibility> credList = new ArrayList<>();
		try {
			credDao = DaoManager.createDao(connSource, Credibility.class);
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
	
	@SuppressWarnings("finally")
	public static List<KlausurFrage> getAllQuestions(){
		List<KlausurFrage> listFrage = new ArrayList<>();

		try{
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			listFrage = klausurfDao.queryForAll();
			
		}		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(listFrage.size() > 0 ){
				return listFrage;
			}else{
				return null;
			}
		}

	}
}
