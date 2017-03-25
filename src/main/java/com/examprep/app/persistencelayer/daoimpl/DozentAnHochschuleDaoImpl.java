package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.DozentAnHochschule;
import com.examprep.app.persistencelayer.daoif.DozentAnHochschuleDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class DozentAnHochschuleDaoImpl extends BaseDaoImpl<DozentAnHochschule, String> implements DozentAnHochschuleDao{

	public DozentAnHochschuleDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, DozentAnHochschule.class);
		// TODO Auto-generated constructor stub
	}

}
