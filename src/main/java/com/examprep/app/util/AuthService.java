package com.examprep.app.util;

import java.util.List;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.dao.NutzerDAO;

public class AuthService {

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public boolean checkAuth(String token) {

		if (token != "" && token.contains("|")) {
			String name = token.split("\\|")[0];
			try {
				Nutzer nutzer = NutzerDAO.getOneNutzerByName(name);
				String genToken = cryp.generateUserToken(nutzer, cryp.token);
				if (genToken.matches(token)) {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}
}
