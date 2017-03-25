package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.Credibility;
import com.examprep.app.persistencelayer.daoif.CredibilityDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class CredibilityDaoImpl extends BaseDaoImpl<Credibility, String> implements CredibilityDao {

	public CredibilityDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Credibility.class);
		// TODO Auto-generated constructor stub
	}

}
