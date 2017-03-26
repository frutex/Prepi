package com.examprep.app.bean;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.HochschuleDaoImpl;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Hochschule", daoClass = HochschuleDaoImpl.class)
public class Hochschule {

	@DatabaseField(generatedId = true, columnName = "h_id")
	private int h_id;
	
	@DatabaseField
	private String name;
	
	@DatabaseField
	private String stadt;
	

	
	public Hochschule() {

	}

	public Hochschule(String name, String stadt) {
		super();
		this.name = name;
		this.stadt = stadt;

	}

	public int getH_id() {
		return h_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}


	
	
	
	
}
