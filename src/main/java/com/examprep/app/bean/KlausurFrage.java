package com.examprep.app.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.KlausurfrageDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Klausurfrage", daoClass = KlausurfrageDaoImpl.class)

public class KlausurFrage implements Serializable {


	@DatabaseField(generatedId = true, columnName = "k_id")
	private int k_id;
	
	@DatabaseField(columnName = "schwierigkeit")
	private int schwierigkeit;
	
	@DatabaseField(columnName = "text")
	private String text;
	
	@DatabaseField(columnName = "jahr")
	private int jahr;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "hk_id")
	private Hochschule hochschule;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "dk_id")
	private Dozent dozent;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "mk_id")
	private Modul modul;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "nk_id")
	private Nutzer nutzer;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "ck_id")
	private Credibility credibility;
	
	
	public KlausurFrage() {
		
	}
	
	public KlausurFrage(int f_id, int schwierigkeit, String text, Dozent dozent, Modul modul, int jahr,
			Hochschule hochschule, Nutzer nutzer, Credibility credibility) {
		this.k_id = f_id;
		this.schwierigkeit = schwierigkeit;
		this.text = text;
		this.dozent = dozent;
		this.modul = modul;
		this.jahr = jahr;
		this.hochschule = hochschule;
		this.credibility = credibility;
	}
	

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public int getF_id() {
		return k_id;
	}


	public int getSchwierigkeit() {
		return schwierigkeit;
	}

	public void setSchwierigkeit(int schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public Hochschule getHochschule() {
		return hochschule;
	}

	public void setHochschule(Hochschule hochschule) {
		this.hochschule = hochschule;
	}

	public Credibility getCredibility() {
		return credibility;
	}

	public void setCredibility(Credibility credibility) {
		this.credibility = credibility;
	}
	
	
	
	



}
