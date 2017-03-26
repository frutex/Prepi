package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.Dozent;
import com.examprep.app.persistencelayer.daoif.DozentDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class DozentDaoImpl extends BaseDaoImpl<Dozent, String> implements DozentDao{

	public DozentDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Dozent.class);
		// TODO Auto-generated constructor stub
	}

}
