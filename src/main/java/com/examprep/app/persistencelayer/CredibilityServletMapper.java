package com.examprep.app.persistencelayer;

import java.sql.SQLException;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.daoif.CredibilityDao;
import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class CredibilityServletMapper {
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	public CredibilityServletMapper() {

	}

	@SuppressWarnings("finally")
	public Credibility createCredibility(KlausurFrage kfrage, Nutzer nutzer) {
		Credibility cred = new Credibility();
		cred.setKlausurf_id(kfrage);
		cred.setNutzc_id(nutzer);

		try {
			Dao<Credibility, String> credDao = DaoManager.createDao(connSource, Credibility.class);
			credDao.create(cred);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			return cred;
		}
	}


}
