package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.ModulAnHochschule;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.persistencelayer.daoif.ModulAnHochschuleDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ModulAnHochschuleDaoImpl extends BaseDaoImpl<ModulAnHochschule, String> implements ModulAnHochschuleDao{

	public ModulAnHochschuleDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, ModulAnHochschule.class);
		// TODO Auto-generated constructor stub
	}

}
