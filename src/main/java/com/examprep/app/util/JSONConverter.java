//package com.jwt.hibernate.util;
//
//import java.util.List;
//
//import com.jwt.hibernate.bean.KundeEO;
//import com.jwt.hibernate.bean.MitarbeiterEO;
//import com.jwt.hibernate.bean.VertragEO;
//
//public class JSONConverter {
//
//	public String toJSONM(List<MitarbeiterEO> maList) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (int i = 0; i < maList.size(); i++) {
//			sb.append(toJSONM(maList.get(i)));
//			if (i != maList.size() - 1) {
//				sb.append(",");
//			}
//		}
//		sb.append("]");
//		return sb.toString();
//
//	}
//
//	public String toJSONM(MitarbeiterEO ma) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("\"ID\":\"").append(ma.getM_id()).append("\",");
//		sb.append("\"Name\":\"").append(ma.getM_name()).append("\",");
//		sb.append("\"Vorname\":\"").append(ma.getM_vorname()).append("\",");
//		sb.append("\"Adresse\":\"").append(ma.getM_adresse().replace(",", " ")).append("\",");
//		sb.append("\"TelNr\":\"").append(ma.getM_telnr()).append("\"");
//		sb.append("}");
//		String res = sb.toString();
//		return res;
//	}
//
//	public String toJSONK(List<KundeEO> kList) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (int i = 0; i < kList.size(); i++) {
//			sb.append(toJSONK(kList.get(i)));
//			if (i != kList.size() - 1) {
//				sb.append(",");
//			}
//		}
//		sb.append("]");
//		return sb.toString();
//
//	}
//
//	public String toJSONK(KundeEO k) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("\"ID\":\"").append(k.getK_id()).append("\",");
//		sb.append("\"Name\":\"").append(k.getK_name()).append("\",");
//		sb.append("\"Vorname\":\"").append(k.getK_vorname()).append("\",");
//		sb.append("\"Adresse\":\"").append(k.getK_adresse().replace(",", " ")).append("\",");
//		sb.append("\"Unternehmen\":\"").append(k.getK_unternehmen()).append("\",");
//		sb.append("\"TelNr\":\"").append(k.getK_telnr()).append("\"");
//		sb.append("}");
//		String res = sb.toString();
//		return res;
//	}
//
//	public String toJSONV(List<VertragEO> vList) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		for (int i = 0; i < vList.size(); i++) {
//			sb.append(toJSONV(vList.get(i)));
//			if (i != vList.size() - 1) {
//				sb.append(",");
//			}
//		}
//		sb.append("]");
//		return sb.toString();
//
//	}
//
//	public String toJSONV(VertragEO v) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("{");
//		sb.append("\"ID\":\"").append(v.getV_id()).append("\",");
//		sb.append("\"Name\":\"").append(v.getV_name()).append("\",");
//		sb.append("\"MitarbeiterID\":\"").append(v.getM_id()).append("\",");
//		sb.append("\"KundeID\":\"").append(v.getK_id()).append("\",");
//		sb.append("\"Vertragsdatum\":\"").append(v.getV_datum()).append("\",");
//		sb.append("\"Ablaufdatum\":\"").append(v.getV_ablauf()).append("\",");
//		sb.append("\"Beschreibung\":\"").append(v.getV_besch().replace(".", "").replace(",", "")).append("\",");
//		sb.append("\"Wert\":\"").append(v.getV_wert()).append("\",");
//		sb.append("\"Branche\":\"").append(v.getV_branche()).append("\"");
//		sb.append("}");
//		String res = sb.toString();
//		return res;
//	}
//}