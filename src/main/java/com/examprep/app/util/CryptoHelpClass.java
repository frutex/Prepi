package com.examprep.app.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;

@SuppressWarnings("restriction")
public class CryptoHelpClass {

	private static String keyStr = "asijdiae88ajdsidj93ja";

	public String seperator = UserTokenMachine.seperator;

	public String token = "SuperSecretExamPrepToken";

	public String generateHashPassword(String email, String password) {
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
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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

	public String generateUserToken(Nutzer nutzer) throws Exception {
		return nutzer.getEmail() + seperator + generateHash(nutzer.getEmail() + nutzer.getPassword() + token)
				+ seperator + encodeNewDate();
	}

	private static String encodeNewDate() throws Exception {

		long mls = System.currentTimeMillis();
		String mString = Long.toString(mls);

		SecretKeySpec sks = createSKS();

		// Verschluesseln
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks);
		byte[] encrypted = cipher.doFinal(mString.getBytes());

		// bytes zu Base64-String konvertieren (dient der Lesbarkeit)
		BASE64Encoder myEncoder = new BASE64Encoder();
		String res = myEncoder.encode(encrypted);

		return res;
	}

	private static String decodeDate(String encoded) throws Exception {
		SecretKeySpec sks = createSKS();

		// BASE64 String zu Byte-Array konvertieren
		BASE64Decoder myDecoder2 = new BASE64Decoder();
		byte[] crypted2 = myDecoder2.decodeBuffer(encoded);

		// Entschluesseln
		Cipher cipher2 = Cipher.getInstance("AES");
		cipher2.init(Cipher.DECRYPT_MODE, sks);
		byte[] cipherData2 = cipher2.doFinal(crypted2);
		String erg = new String(cipherData2);

		return erg;

	}

	private static SecretKeySpec createSKS() throws Exception {
		// Encoder Bauen
		byte[] key = (keyStr).getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

		return secretKeySpec;

	}

	public static boolean checkDurability(String token) throws Exception {
		String durK = "";
		if (token.contains(UserTokenMachine.seperator)) {
			durK = UserTokenMachine.getDurabilityFromToken(token);
		} else {
			durK = token;
		}

		String decoded = decodeDate(durK);
		long mlsOld = Long.parseLong(decoded.trim());

		long mls = System.currentTimeMillis();

		long dif = mls - mlsOld;

		System.out.println(dif);
		long minu = TimeUnit.MILLISECONDS.toMinutes(dif);

		if(minu > 60 || minu < 0){
			return false;
		} else {
			return true;
		}
	}

}
