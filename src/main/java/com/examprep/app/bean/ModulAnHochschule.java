package com.examprep.app.bean;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.ModulAnHochschuleDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ModulAnHochschule", daoClass = ModulAnHochschuleDaoImpl.class)
public class ModulAnHochschule {
	@DatabaseField(generatedId = true, columnName = "mah_id")
	private int dum_id;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "modul_id")
	private Modul modul;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "hochschule_id")
	private Hochschule hochschule;

	
	public ModulAnHochschule() {

	}
	public ModulAnHochschule(Modul modul, Hochschule hochschule) {

		this.modul = modul;
		this.hochschule = hochschule;
	}
	
	

}
