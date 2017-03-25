package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Modul;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.persistencelayer.daoif.ModulDao;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class ModulDaoImpl extends BaseDaoImpl<Modul, String> implements ModulDao{



	public ModulDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Modul.class);
	}



}
