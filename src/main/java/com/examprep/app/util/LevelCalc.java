package com.examprep.app.util;

public class LevelCalc {

	private static int[] res = {10,25,45,70,100,135,175,220,270,335,395,460,530}; 
	
	public static int calculateLevel(int num){
		int level = 0;
		for(int i = 0; i<res.length; i++){
			if ( num > res[i]){
				level ++;
			}
		}
		
		return level;
	}
	
}
