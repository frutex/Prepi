package com.examprep.app.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.ModulDaoImpl;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Modul", daoClass = ModulDaoImpl.class)
public class Modul {
	
	@DatabaseField(generatedId = true, columnName = "m_id")
	private int m_id;
	

	
	@DatabaseField(columnName = "modul")
	private String modul;
	
	@ForeignCollectionField
	private ForeignCollection<KlausurFrage> klausurfragen;
	
	public Modul() {

	}

	public Modul( String modul) {


		this.modul = modul;
	}

	public int getM_id() {
		return m_id;
	}


	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		this.modul = modul;
	}

	public ForeignCollection<KlausurFrage> getKlausurfragen() {
		return klausurfragen;
	}

	public void setKlausurfragen(ForeignCollection<KlausurFrage> klausurfragen) {
		this.klausurfragen = klausurfragen;
	}
	
	
	

}
