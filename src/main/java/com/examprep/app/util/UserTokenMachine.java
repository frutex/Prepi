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
	
	public static String getTokenFromToken(String token){
		String nutzer = token.split("\\!")[1];
		
		return nutzer;
		
	}
	
	public static String getDurabilityFromToken(String token){
		String dur = token.split("\\!")[2];
		
		return dur;
		
	}
	
	
}
