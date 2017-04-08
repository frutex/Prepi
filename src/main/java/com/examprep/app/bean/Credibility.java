package com.examprep.app.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@Entity
@DatabaseTable(tableName = "GegebeneCred", daoClass = CredibilityDaoImpl.class)
public class Credibility {

	@DatabaseField(generatedId = true, columnName = "c_id")
	private int c_id;
	
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "kfgc_id")
	private KlausurFrage klausurf_id;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "nutgc_id")
	private Nutzer nutzc_id;

	
	public Credibility(){
		
	}
	
	public Credibility(KlausurFrage klausurf_id, Nutzer nutzc_id){
		this.klausurf_id = klausurf_id;
		this.nutzc_id = nutzc_id;
		
	}

	public int getC_id() {
		return c_id;
	}



	public KlausurFrage getKlausurf_id() {
		return klausurf_id;
	}

	public void setKlausurf_id(KlausurFrage klausurf_id) {
		this.klausurf_id = klausurf_id;
	}

	public Nutzer getNutz() {
		return nutzc_id;
	}

	public void setNutzc_id(Nutzer nutzc_id) {
		this.nutzc_id = nutzc_id;
	}
	
	
	
	
	
}
