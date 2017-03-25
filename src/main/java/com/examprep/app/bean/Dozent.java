package com.examprep.app.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.DozentDaoImpl;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@Entity
@DatabaseTable(tableName = "Dozent", daoClass = DozentDaoImpl.class)

public class Dozent implements Serializable{
	
	@DatabaseField(generatedId = true, columnName = "d_id")
	private int d_id;
	
	@DatabaseField
	private String vorname;
	
	@DatabaseField
	private String nachname;
	
	@ForeignCollectionField
	private ForeignCollection<KlausurFrage> klausurfrage;

	public Dozent() {

	}
	
	public Dozent(int d_id, String vorname, String nachname) {
		super();
		this.d_id = d_id;
		this.vorname = vorname;
		this.nachname = nachname;
	}

	
	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	public ForeignCollection<KlausurFrage> getKlausurfrage() {
		return klausurfrage;
	}


	public void setKlausurfrage(ForeignCollection<KlausurFrage> klausurfrage) {
		this.klausurfrage = klausurfrage;
	}
	

	
	
}
