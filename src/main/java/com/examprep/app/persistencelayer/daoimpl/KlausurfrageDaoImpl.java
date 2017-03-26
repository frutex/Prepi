package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.persistencelayer.daoif.KlausurfrageDao;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class KlausurfrageDaoImpl extends BaseDaoImpl<KlausurFrage, String> implements KlausurfrageDao{
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();
	public KlausurfrageDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, KlausurFrage.class);
	}



}
