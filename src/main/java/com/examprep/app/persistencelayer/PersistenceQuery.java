package com.examprep.app.persistencelayer;

import java.lang.reflect.Method;
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
			reconnect();
			credDao = DaoManager.createDao(connSource, Credibility.class);
			credDao.create(cred);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			return cred;
		}
	}

	@SuppressWarnings("finally")
	public static int getAllLikesForOneQuestionNumber(KlausurFrage kfrage) {
		List<Credibility> credList = new ArrayList<>();
		int anzahl = 0;
		try {
			credDao = DaoManager.createDao(connSource, Credibility.class);
			credList = credDao.queryBuilder().where().eq("kfgc_id", kfrage.getF_id()).query();
		} catch (SQLException e) {
			reconnect();
			anzahl = getAllLikesForOneQuestionNumber(kfrage);
			;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			anzahl = credList.size();
			return anzahl;

		}

	}

	@SuppressWarnings("finally")
	public static List<Credibility> getAllLikesForOneQuestion(KlausurFrage kfrage) {
		List<Credibility> credList = new ArrayList<>();
		int anzahl = 0;
		try {
			credDao = DaoManager.createDao(connSource, Credibility.class);
			credList = credDao.queryBuilder().where().eq("kfgc_id", kfrage.getF_id()).query();
		} catch (SQLException e) {
			reconnect();
			credList = getAllLikesForOneQuestion(kfrage);
			;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (credList.size() > 0) {
				return credList;
			} else {
				return null;
			}

		}

	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Dozent
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	@SuppressWarnings("finally")
	public static Dozent createDozent(String vorname, String nachname) {
		Dozent doz = new Dozent();

		doz.setVorname(vorname);
		doz.setNachname(nachname);

		try {
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			dozentDao.create(doz);
		} catch (SQLException e) {
			reconnect();
			doz = createDozent(vorname, nachname);
			e.printStackTrace();
		} finally {
			return doz;
		}

	}

	@SuppressWarnings("finally")
	public static List<Dozent> getAllDozenten() {
		List<Dozent> dozList = new ArrayList<>();

		try {
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			dozList = dozentDao.queryForAll();
		} catch (SQLException e) {
			reconnect();

			dozList = getAllDozenten();
			e.printStackTrace();
		} finally {
			return dozList;
		}
	}

	@SuppressWarnings("finally")
	public static Dozent getDozentByName(String vorname, String nachname) {
		List<Dozent> dozList = new ArrayList<>();
		Dozent doz = new Dozent();

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
			dozList = dozentDao.query(preparedQuery);
;			doz = dozList.get(0);
		} catch (SQLException e) {
			reconnect();
			doz = getDozentByName(vorname, nachname);
			e.printStackTrace();
		} finally {
			return doz;
		}

	}

	@SuppressWarnings("finally")
	public static List<Modul> getAllModuleVonDozent(Dozent dozent) {
		List<Modul> modulList = new ArrayList<>();
		List<DozentUnterrichtetModul> dozUmodList = new ArrayList<>();

		try {
			dozentUnterrichtetModulDao = DaoManager.createDao(connSource, DozentUnterrichtetModul.class);
			modulDao = DaoManager.createDao(connSource, Modul.class);
			// doz = dozentDao.queryBuilder().where().and(where.eq("vorname",
			// vorname), where. ));

			dozUmodList = dozentUnterrichtetModulDao.queryBuilder().where().eq("ddum_id", dozent.getD_id()).query();
			for (DozentUnterrichtetModul tmp : dozUmodList) {
				modulList.add(tmp.getModul());
			}

		} catch (SQLException e) {
			reconnect();
			modulList = getAllModuleVonDozent(dozent);
			e.printStackTrace();
		} finally {
			if (modulList.size() > 0) {
				return modulList;
			} else {
				List<Modul> newList = new ArrayList<>();
				return newList;
			}
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
			reconnect();
			hochschule = createHochschule(name, stadt);
			e.printStackTrace();

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
			reconnect();

			listHochschule = getAllHochschulen();
			e.printStackTrace();
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
			reconnect();
			listHochschule = getHochschuleByName(name);
			e.printStackTrace();
		} finally {
			return listHochschule;
		}
	}

	@SuppressWarnings("finally")
	public static List<Modul> getAllModuleEinerHochschule(String name) {
		Hochschule hochschule = getHochschuleByName(name).get(0);
		List<ModulAnHochschule> modAnHochList = new ArrayList<>();
		List<Modul> modList = new ArrayList<>();
		try {
			modulAnHochschuleDao = DaoManager.createDao(connSource, ModulAnHochschule.class);
			modAnHochList = modulAnHochschuleDao.queryBuilder().where().eq("hochschule_id", hochschule.getH_id())
					.query();
			modulDao = DaoManager.createDao(connSource, Modul.class);
			for (ModulAnHochschule tmp : modAnHochList) {
				modList.add(tmp.getModul());
			}
		} catch (SQLException e) {
			reconnect();

			modList = getAllModuleEinerHochschule(name);
			e.printStackTrace();
		} finally {
			return modList;
		}
	}

	@SuppressWarnings("finally")
	public static List<Dozent> getAllDozentenEinerHochschule(String name) {
		Hochschule hochschule = getHochschuleByName(name).get(0);
		List<DozentAnHochschule> dozAnHochList = new ArrayList<>();
		List<Dozent> dozList = new ArrayList<>();
		try {
			dozentAnHochschuleDao = DaoManager.createDao(connSource, DozentAnHochschule.class);
			dozAnHochList = dozentAnHochschuleDao.queryBuilder().where().eq("hoch_id", hochschule.getH_id()).query();
			dozentDao = DaoManager.createDao(connSource, Dozent.class);
			for (DozentAnHochschule tmp : dozAnHochList) {
				dozList.add(tmp.getDozent());
			}
		} catch (SQLException e) {
			reconnect();
			dozList = getAllDozentenEinerHochschule(name);
			e.printStackTrace();
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

	@SuppressWarnings("finally")
	public static KlausurFrage createKlausurfrage(String text, int jahr, String titel, Hochschule hochschule,
			Dozent dozent, Modul modul, Nutzer nutzer) {

		KlausurFrage klausurf = new KlausurFrage();

		klausurf.setText(text);
		klausurf.setJahr(jahr);
		klausurf.setHochschule(hochschule);
		klausurf.setDozent(dozent);
		klausurf.setModul(modul);
		klausurf.setNutzer(nutzer);
		klausurf.setTitel(titel);

		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			klausurfDao.create(klausurf);
		} catch (SQLException e) {
			reconnect();
			klausurf = createKlausurfrage(text, jahr, titel, hochschule, dozent, modul, nutzer);
			e.printStackTrace();
		} finally {
			return klausurf;
		}

	}

	@SuppressWarnings("finally")
	public static List<KlausurFrage> getAllQuestions() {
		List<KlausurFrage> listFrage = new ArrayList<>();

		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			listFrage = klausurfDao.queryForAll();

		} catch (SQLException e) {
			reconnect();
			listFrage = getAllQuestions();
			e.printStackTrace();
		} finally {
			if (listFrage.size() > 0) {
				return listFrage;
			} else {
				return null;
			}
		}

	}

	@SuppressWarnings("finally")
	public static List<KlausurFrage> getKlausurFrage(Dozent dozent, Modul modul, Hochschule hochschule,
			String keyword) {
		String[] words = keyword.split(" ");
		List<KlausurFrage> kList = new ArrayList<>();
		try {

			QueryBuilder<KlausurFrage, String> queryBuilder = klausurfDao.queryBuilder();
			// get the WHERE object to build our query
			Where<KlausurFrage, String> where = queryBuilder.where();
			if (dozent != null) {
				where.eq(KlausurFrage.DOZENT_ID_FIELD_NAME, dozent.getD_id());
				where.and();
			}
			if (modul != null) {
				where.eq(KlausurFrage.MODUL_ID_FIELD_NAME, modul.getM_id());
				where.and();
			}

			if (hochschule != null) {
				where.eq(KlausurFrage.HOCHSCHULE_ID_FIELD_NAME, hochschule.getH_id());
				where.and();
			}
			if (keyword.length() > 0) {
				for (int i = 0; i < words.length; i++) {
					where.like("text", words[i]);
					if (i < words.length - 1) {
						where.and();
					}
				}
			}

			PreparedQuery<KlausurFrage> preparedQuery = queryBuilder.prepare();
			kList = klausurfDao.query(preparedQuery);

			if (kList.size() < 1) {
				QueryBuilder<KlausurFrage, String> queryBuilderContent = klausurfDao.queryBuilder();
				// get the WHERE object to build our query
				Where<KlausurFrage, String> whereContent = queryBuilder.where();

				if (dozent != null) {
					where.eq(KlausurFrage.DOZENT_ID_FIELD_NAME, dozent.getD_id());
					where.and();
				}
				if (modul != null) {
					where.eq(KlausurFrage.MODUL_ID_FIELD_NAME, modul.getM_id());
					where.and();
				}

				if (hochschule != null) {
					where.eq(KlausurFrage.HOCHSCHULE_ID_FIELD_NAME, hochschule.getH_id());
					where.and();
				}
				if (keyword.length() > 0) {
					for (int i = 0; i < words.length; i++) {
						where.like("text", words[i]);
						if (i < words.length - 1) {
							where.and();
						}
					}

				}
				PreparedQuery<KlausurFrage> preparedQuery1 = queryBuilder.prepare();
				kList = klausurfDao.query(preparedQuery1);
			}
		} catch (SQLException e) {
			reconnect();
			kList = getKlausurFrage(dozent, modul, hochschule, keyword);

		}finally{
			if(kList.size() > 0){
				return kList;
			}else{
				return null;
			}
		}


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
			reconnect();
			modul = createModul(modulName);
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
			reconnect();

			modul = createModulWithHochschule(modulName, hochschule);
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
			reconnect();
			modul = createModulWithDozent(modulName, dozent);
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
			reconnect();
			modul = createModulWithDozentAndHochschule(modulName, dozent, hochschule);
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
	public static Modul getModulById(String id) {
		Modul modul = null;
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			modul = modulDao.queryForId(id);
		} catch (SQLException e) {
			reconnect();

			modul = getModulById(id);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return modul;
		}

	}

	@SuppressWarnings("finally")
	public static List<Modul> getAllModule() {
		List<Modul> moduleList = new ArrayList<>();
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryForAll();
		} catch (SQLException e) {
			reconnect();
			moduleList = getAllModule();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (moduleList.size() > 0) {
				return moduleList;
			} else {
				return null;
			}
		}

	}

	@SuppressWarnings("finally")
	public static List<Modul> getModulByName(String name) {

		List<Modul> moduleList = new ArrayList<>();
		try {
			modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryBuilder().where().eq("modul", name).query();
		} catch (SQLException e) {
			reconnect();
			moduleList = getModulByName(name);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (moduleList.size() > 0) {
				return moduleList;
			} else {
				return null;
			}
		}

	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Nutzer
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	@SuppressWarnings("finally")
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
			reconnect();
			nutzer = createNutzer(name, vorname, email, password, hochschule);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return nutzer;
		}

	}

	@SuppressWarnings("finally")
	public static Nutzer getNutzerById(String id) {
		Nutzer nutzer = null;
		try {
			nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
			nutzer = nutzerDao.queryForId(id);
		} catch (SQLException e) {
			reconnect();
			nutzer = getNutzerById(id);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return nutzer;
		}

	}

	@SuppressWarnings("finally")
	public static List<Nutzer> getNutzerByName(String name) {

		List<Nutzer> nutzerList = new ArrayList<>();
		try {
			Dao<Nutzer, String> nutzerDao = DaoManager.createDao(connSource, Nutzer.class);
			nutzerList = nutzerDao.queryBuilder().where().eq("email", name).query();
		} catch (SQLException e) {
			reconnect();
			nutzerList = getNutzerByName(name);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (nutzerList.size() > 0) {
				return nutzerList;
			} else {
				return null;
			}
		}

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
			reconnect();
			cred = getAllLikesForRegisteredQuestionsForOneUser(name);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			return cred;
		}
	}

	@SuppressWarnings("finally")
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
			reconnect();
			listFrage = getAllLikedQuestionsForOneUser(name);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (listFrage.size() > 0) {
				return listFrage;
			} else {
				return null;
			}
		}

	}

	@SuppressWarnings("finally")
	public static List<KlausurFrage> getAllQuestionsFromOneUser(String name) {
		Nutzer nutzer = getOneNutzerByName(name);
		List<KlausurFrage> frageList = new ArrayList<>();
		try {
			klausurfDao = DaoManager.createDao(connSource, KlausurFrage.class);
			frageList = klausurfDao.queryBuilder().where().eq(KlausurFrage.NUTZER_ID_FIELD_NAME, nutzer.getN_id())
					.query();

		} catch (SQLException e) {
			reconnect();
			frageList = getAllQuestionsFromOneUser(name);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (frageList.size() > 0) {
				return frageList;
			} else {
				 List<KlausurFrage> newList = new ArrayList<>();
				 return newList;
			}
		}

	}

	@SuppressWarnings("finally")
	public static boolean isQuestionLikedByUser(KlausurFrage kfrage, Nutzer nutzer) {
		List<Credibility> credList = new ArrayList<>();
		boolean liked = false;
		try {
			credDao = DaoManager.createDao(connSource, Credibility.class);
			// doz = dozentDao.queryBuilder().where().and(where.eq("vorname",
			// vorname), where. ));

			QueryBuilder<Credibility, String> queryBuilder = credDao.queryBuilder();
			// get the WHERE object to build our query
			Where<Credibility, String> where = queryBuilder.where();
			where.like("nutgc_id", nutzer.getN_id());
			where.and();
			where.like("kfgc_id", kfrage.getF_id());

			PreparedQuery<Credibility> preparedQuery = queryBuilder.prepare();
			credList = credDao.query(preparedQuery);
		} catch (SQLException e) {
			reconnect();
			liked = isQuestionLikedByUser(kfrage, nutzer);
			e.printStackTrace();
		} finally {
			if (liked = false) {
				if (credList.size() > 0) {
					liked = true;
					return liked;
				} else {
					liked = false;
					return false;
				}
			} else {
				return liked;
			}

		}
	}

	/**
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 * Part for Util
	 * ---------------------------------------------------------------------------------------------
	 * ---------------------------------------------------------------------------------------------
	 */

	public static void reconnect() {
		connSource = sess.createConnection();
	}
}
