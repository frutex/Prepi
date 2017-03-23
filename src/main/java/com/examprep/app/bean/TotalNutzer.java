package com.examprep.app.bean;

import com.examprep.app.util.LevelCalc;
import com.j256.ormlite.dao.ForeignCollection;

public class TotalNutzer extends Nutzer{
	private ForeignCollection<KlausurFrage> Klausurfragen;
	private int credibility;
	private int level;
	
	
	
	public TotalNutzer(String name, String vorname, String email, String password, Hochschule hochschule,
			ForeignCollection<KlausurFrage> klausurfragen, int credibility, int level) {
		super(name, vorname, email, password, hochschule);
		Klausurfragen = klausurfragen;
		this.credibility = credibility;
		this.level = LevelCalc.calculateLevel(this.credibility);
	}

	public TotalNutzer(ForeignCollection<KlausurFrage> klausurfragen, int credibility, int level) {
		super();
		Klausurfragen = klausurfragen;
		this.credibility = credibility;
		this.level = LevelCalc.calculateLevel(this.credibility);
	}

	public TotalNutzer() {
		super();
	}
	
	public ForeignCollection<KlausurFrage> getKlausurfragen() {
		return Klausurfragen;
	}
	public void setKlausurfragen(ForeignCollection<KlausurFrage> klausurfragen) {
		Klausurfragen = klausurfragen;
	}
	public int getCredibility() {
		return credibility;
	}
	public void setCredibility(int credibility) {
		this.credibility = credibility;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	
}
