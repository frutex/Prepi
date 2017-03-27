package com.examprep.app.bean;

import com.examprep.app.persistencelayer.daoimpl.CredibilityDaoImpl;
import com.examprep.app.persistencelayer.daoimpl.DozentAnHochschuleDaoImpl;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DozentAnHochschule", daoClass = DozentAnHochschuleDaoImpl.class)
public class DozentAnHochschule {
	@DatabaseField(generatedId = true, columnName = "dah_id")
	private int dah_id;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "doz_id")
	private Dozent dozent;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "hoch_id")
	private Hochschule hochschule;

	
	public DozentAnHochschule() {

	}
	public DozentAnHochschule(Dozent dozent, Hochschule hochschule) {

		this.dozent = dozent;
		this.hochschule = hochschule;
	}
	public int getDah_id() {
		return dah_id;
	}

	public Dozent getDozent() {
		return dozent;
	}
	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}
	public Hochschule getHochschule() {
		return hochschule;
	}
	public void setHochschule(Hochschule hochschule) {
		this.hochschule = hochschule;
	}

	
	
}
