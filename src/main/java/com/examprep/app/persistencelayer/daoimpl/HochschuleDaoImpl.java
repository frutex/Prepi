package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class HochschuleDaoImpl extends BaseDaoImpl<Hochschule, String> implements HochschuleDao {



	public HochschuleDaoImpl(ConnectionSource connectionsource) throws SQLException {
		super(connectionsource, Hochschule.class);
	}

}
