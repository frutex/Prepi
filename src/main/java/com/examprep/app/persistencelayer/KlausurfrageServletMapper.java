package com.examprep.app.persistencelayer;

import java.sql.SQLException;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class KlausurfrageServletMapper {

	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

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


		Dao<KlausurFrage, String> klausurfDao;

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
}
