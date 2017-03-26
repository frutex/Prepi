package com.examprep.app.bean;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.NutzerDaoImpl;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Nutzer", daoClass = NutzerDaoImpl.class)
public class Nutzer {
	
	@DatabaseField(generatedId = true, columnName = "n_id")
	private int n_id;
	
	@DatabaseField
	private String name;
	
	@DatabaseField
	private String vorname;
	
	@DatabaseField
	private String email;
	
	@DatabaseField
	private String password;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "hochschul_ID")
	private Hochschule hochschule;
	
	@ForeignCollectionField
	private ForeignCollection<KlausurFrage> klausurfrage;

	@ForeignCollectionField
	private ForeignCollection<Credibility> cred;
	
	public Nutzer() {
	}
	
	public Nutzer(String name, String vorname, String email, String password, Hochschule hochschule) {

		this.name = name;
		this.vorname = vorname;
		this.email = email;
		this.password = password;
		this.hochschule = hochschule;

	}



	public int getN_id() {
		return n_id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Hochschule getHochschule() {
		return hochschule;
	}

	public void setHochschule(Hochschule hochschule) {
		this.hochschule = hochschule;
	}

	public ForeignCollection<KlausurFrage> getKlausurfragen() {
		return klausurfrage;
	}

	public void setKlausurfragen(ForeignCollection<KlausurFrage> klausurfrage) {
		this.klausurfrage = klausurfrage;
	}

	
	
}
