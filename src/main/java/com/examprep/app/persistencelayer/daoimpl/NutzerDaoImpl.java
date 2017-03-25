package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.daoif.ModulDao;
import com.examprep.app.persistencelayer.daoif.NutzerDao;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class NutzerDaoImpl extends BaseDaoImpl<Nutzer, String> implements NutzerDao{

	public NutzerDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Nutzer.class);
		// TODO Auto-generated constructor stub
	}

	

	

	

	
	
}
