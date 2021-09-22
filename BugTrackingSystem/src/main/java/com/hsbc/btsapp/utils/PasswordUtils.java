package com.hsbc.btsapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {

	public static byte[] hash(char[] password) {
		PBEKeySpec spec = new PBEKeySpec(password);
		try {
			SecretKeyFactory sf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return sf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	public static String encrpyt(String password) {
		byte[] encryptedPassword = hash(password.toCharArray());
		return Base64.getEncoder().encodeToString(encryptedPassword);
	}

	public static boolean validatePassword(String receivedPassword, String userPassword) {
		boolean status = false;

		String encryptedPassword = encrpyt(receivedPassword);
		status = encryptedPassword.equalsIgnoreCase(userPassword);
		return status;
	}
}
