package com.examprep.app.persistencelayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.examprep.app.bean.Modul;
import com.examprep.app.util.SessionFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class ModulServletMapper {

	

	static SessionFactory sess = new SessionFactory();
	static ConnectionSource connSource = sess.createConnection();

	
	@SuppressWarnings("finally")
	public static Modul createModul(String modulName) {
		Modul modul = new Modul(modulName);
		try {
			Dao<Modul, String> modulDao = DaoManager.createDao(connSource, Modul.class);
			modulDao.create(modul);
		} catch (Exception e) {
			System.out.println(e.getMessage());;
			e.printStackTrace();
		}finally{
			int id = modul.getM_id();
			if (id == 0){
				return null;
			}else{
				System.out.println(id);
				return modul;
			}
		}
	

	}
	
	public static Modul searchModulById(String id){
		Modul modul = null;
		try {
			Dao<Modul, String> modulDao = DaoManager.createDao(connSource, Modul.class);
			modul = modulDao.queryForId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modul;

		
	}
	
	public static List<Modul> getAllModule(){
		List<Modul> moduleList = new ArrayList<>();
		try {
			Dao<Modul, String> modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moduleList;
	}
	
	public static List<Modul> searchModulByName(String name){
		
		List<Modul> moduleList = new ArrayList<>();
		try {
			Dao<Modul, String> modulDao = DaoManager.createDao(connSource, Modul.class);
			moduleList = modulDao.queryBuilder().where().eq("modul", name).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moduleList;
		
	}
}
