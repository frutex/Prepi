package com.examprep.app.util;

public class UserTokenMachine {

	public static String seperator = "!";

	public String getSeperator() {
		return seperator;
	}
	
	public static String getUserFromToken(String token){
		String nutzer = token.split("\\!")[0];
		
		return nutzer;
		
	}
	
	
}
