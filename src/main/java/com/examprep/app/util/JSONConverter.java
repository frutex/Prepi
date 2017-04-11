package com.examprep.app.util;

import java.util.List;

import com.examprep.app.bean.Credibility;
import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.j256.ormlite.dao.ForeignCollection;

public class JSONConverter {

	public static String toJSONN(Nutzer nutzer) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Email\":\"").append(nutzer.getEmail()).append("\",");
		sb.append("\"Name\":\"").append(nutzer.getName()).append("\",");
		sb.append("\"Vorname\":\"").append(nutzer.getVorname()).append("\",");
		sb.append("\"Hochschule\":\"").append(nutzer.getHochschule().getName()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}

	public static String toJSONH(Hochschule hochschule) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Name\":\"").append(hochschule.getName()).append("\",");
		sb.append("\"Stadt\":\"").append(hochschule.getStadt()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}

	public static String toJSONH(List<Hochschule> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSONH(list.get(i)));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONM(Modul modul) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Name\":\"").append(modul.getModul()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}

	public static String toJSONM(List<Modul> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSONM(list.get(i)));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSOND(Dozent dozent) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Vorname\":\"").append(dozent.getVorname()).append("\",");
		sb.append("\"Nachname\":\"").append(dozent.getNachname()).append("\",");
		sb.append("\"Vollname\":\"").append(dozent.getNachname()).append(", ").append(dozent.getVorname()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}

	public static String toJSOND(List<Dozent> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSOND(list.get(i)));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONF(KlausurFrage frage, Nutzer nutzer) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Jahr\":\"").append(frage.getJahr()).append("\",");
		sb.append("\"Beschreibung\":\"").append(frage.getText()).append("\",");
		sb.append("\"Dozent\":\"").append(frage.getDozent().getNachname()).append(", ")
				.append(frage.getDozent().getVorname()).append("\",");
		sb.append("\"Hochschule\":\"").append(frage.getHochschule().getName()).append("\",");
		sb.append("\"Modul\":\"").append(frage.getModul().getModul()).append("\",");
		sb.append("\"Nutzer\":\"").append(frage.getNutzer().getEmail()).append("\",");
		sb.append("\"Titel\":\"").append(frage.getTitel()).append("\",");
		sb.append("\"Likes\":\"").append(frage.getCred().size()).append("\",");
		sb.append("\"FragenID\":\"").append(frage.getF_id()).append("\",");
		sb.append("\"isLikeable\":").append(LikeChecker.check(frage, nutzer)).append("");
		sb.append("}");
		String res = sb.toString();
		return res;
	}
	
	public static String toJSONF(KlausurFrage frage) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Jahr\":\"").append(frage.getJahr()).append("\",");
		sb.append("\"Beschreibung\":\"").append(frage.getText()).append("\",");
		sb.append("\"Dozent\":\"").append(frage.getDozent().getNachname()).append(", ")
				.append(frage.getDozent().getVorname()).append("\",");
		sb.append("\"Hochschule\":\"").append(frage.getHochschule().getName()).append("\",");
		sb.append("\"Modul\":\"").append(frage.getModul().getModul()).append("\",");
		sb.append("\"Nutzer\":\"").append(frage.getNutzer().getEmail()).append("\",");
		sb.append("\"Titel\":\"").append(frage.getTitel()).append("\",");
		sb.append("\"Likeable\":\"").append(true).append("\",");
		sb.append("\"Likes\":\"").append(frage.getCred().size()).append("\",");
		sb.append("\"FragenID\":\"").append(frage.getF_id()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}
	
	public static String toJSONF(List<KlausurFrage> list, Nutzer nutzer) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSONF(list.get(i), nutzer));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONF(List<KlausurFrage> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSONF(list.get(i)));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONFullF(KlausurFrage frage, Nutzer nutzer) {
		StringBuilder sb = new StringBuilder();
		sb.append("[{\"Fragendetails\":");
		sb.append(toJSONF(frage, nutzer));
		sb.append("},{\"Likes\":");
		sb.append(toJSONL(PersistenceQuery.getAllLikesForOneQuestion(frage)));
		sb.append("}]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONL(List<Credibility> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(toJSONL(list.get(i)));

		}
		sb.append("]");
		String res = sb.toString();
		return res;
	}

	public static String toJSONL(Credibility cred) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Name\":\"").append(cred.getNutz().getName()).append("\",");
		sb.append("\"Vorname\":\"").append(cred.getNutz().getVorname()).append("\",");
		sb.append("\"Email\":\"").append(cred.getNutz().getEmail()).append("\",");
		sb.append("\"FragenID\":\"").append(cred.getKlausurf_id().getF_id()).append("\"");
		sb.append("}");
		String res = sb.toString();
		return res;
	}
}