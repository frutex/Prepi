package com.examprep.app.persistencelayer.daoimpl;

import java.sql.SQLException;

import com.examprep.app.bean.DozentUnterrichtetModul;
import com.examprep.app.persistencelayer.daoif.DozentUnterrichtetModulDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class DozentUnterrichtetModulDaoImpl extends BaseDaoImpl<DozentUnterrichtetModul, String> implements DozentUnterrichtetModulDao {

	public DozentUnterrichtetModulDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, DozentUnterrichtetModul.class);
		// TODO Auto-generated constructor stub
	}

}
