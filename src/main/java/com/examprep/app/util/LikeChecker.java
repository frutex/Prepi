package com.examprep.app.util;

import java.util.List;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;

public class LikeChecker {

	public static boolean check(KlausurFrage frage, Nutzer nutzer) {
		List<Credibility> cList = PersistenceQuery.getAllLikesForOneQuestion(frage);
		return check(cList, nutzer);
	}

	public static boolean check(List<Credibility> cList, Nutzer nutzer) {
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).getNutz().getN_id() == nutzer.getN_id()) {
				return false;
			}
		}
		return true;
	}

	public static boolean check(KlausurFrage frage, int nID) {
		return check(frage, nID + "");
	}
	
	public static boolean check(KlausurFrage frage, String nID) {
		Nutzer nutzer = PersistenceQuery.getNutzerById(nID);
		List<Credibility> cList = PersistenceQuery.getAllLikesForOneQuestion(frage);
		return check(cList, nutzer);
	}
	

}
