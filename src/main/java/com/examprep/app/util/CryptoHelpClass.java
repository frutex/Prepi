package com.examprep.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.examprep.app.bean.Nutzer;

public class CryptoHelpClass {
	
	public String token = "SuperSecretExamPrepToken";
	
	 
	public String generateHashPassword(String email, String password){
		String salt = email;
		String saltedPassword = salt + password;
		
		String hashedPassword = generateHash(saltedPassword);

		return hashedPassword;
		
	}

	public String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	
	public String generateUserToken(Nutzer nutzer, String secretToken) {
		return nutzer.getEmail() + "|" + generateHash(nutzer.getEmail() + nutzer.getPassword() + secretToken);
	}
	
	
}
