package com.examprep.app.util;

import java.util.List;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;



public class AuthService {

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public boolean checkAuth(String token) {

		if (token != "" && token.contains(UserTokenMachine.seperator)) {
			String name = UserTokenMachine.getUserFromToken(token);
			try {
				Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);
				String genToken = cryp.generateUserToken(nutzer, cryp.token);
				if (genToken.equalsIgnoreCase(token)) {
					int i = 0;
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}
}
