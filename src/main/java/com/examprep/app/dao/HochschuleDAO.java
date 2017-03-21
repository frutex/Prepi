package com.examprep.app.dao;

import java.sql.SQLException;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class HochschuleDAO {

	
	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();
	
	public HochschuleDAO(){
		super();
	}
	
	public static Hochschule createHochschule(String name, String stadt){
		Hochschule hochschule = new Hochschule(name, stadt);
		
		Dao<Hochschule, String> hochschuleDao;
		try {
			hochschuleDao = DaoManager.createDao(connSource, Hochschule.class);
			hochschuleDao.create(hochschule);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hochschule;
	}
}
