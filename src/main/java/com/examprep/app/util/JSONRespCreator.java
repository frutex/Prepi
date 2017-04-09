package com.examprep.app.util;

public class JSONRespCreator {

	public static String createWobj(boolean successfull, String resp){
		String res = "";
		res = "{\"successfull\":" + successfull + ",\"data\":" + resp + "}";
		return res;
	}
	
	public static String createWstring(boolean successfull, String resp){
		String res = "";
		res = "{\"successfull\":" + successfull + ",\"data\":\"" + resp + "\"}";
		return res;
	}
}
