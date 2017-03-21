package com.examprep.app.bean;

import com.j256.ormlite.field.DatabaseField;

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

	
}
