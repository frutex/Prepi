package com.examprep.app.bean;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.DozentUnterrichtetModulDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "DozentUnterrichtetModul", daoClass = DozentUnterrichtetModulDaoImpl.class)
public class DozentUnterrichtetModul {
	
	@DatabaseField(generatedId = true, columnName = "dum_id")
	private int dum_id;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ddum_id")
	private Dozent dozent;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "md_id")
	private Modul modul;

	public DozentUnterrichtetModul() {


	}
	
	public DozentUnterrichtetModul(Dozent dozent, Modul modul) {

		this.dozent = dozent;
		this.modul = modul;
	}

	public int getDum_id() {
		return dum_id;
	}



	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}
	
	
	
}
