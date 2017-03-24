package com.examprep.app.util;

import java.util.List;

import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.j256.ormlite.dao.ForeignCollection;

public class JSONConverter {

	public static String toJSON(Nutzer nutzer) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"Email\":\"").append(nutzer.getEmail()).append("\",");
		sb.append("\"Name\":\"").append(nutzer.getName()).append("\",");
		sb.append("\"Vorname\":\"").append(nutzer.getVorname()).append("\",");
		sb.append("\"Adresse\":\"").append(nutzer.getHochschule()).append("\",");
		sb.append("\"Klausurfragen\":").append(toJSON(nutzer.getKlausurfragen())).append("");
		sb.append("}");
		String res = sb.toString();
		return res;
	}
	
	public static String toJSON(ForeignCollection<KlausurFrage> fragen){
		StringBuilder sb = new StringBuilder();
		sb.append("[]");
		
		
		
		String res = sb.toString();
		return res;
	}
}