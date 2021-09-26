package com.hsbc.btsapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	
	private static final Random RANDOM = new Random();
	private static final int ITERATIONS = 1000;
	private static final int KEY_LENGTH = 256;

	
	private static byte[] getSalt() {
		byte[] salt= new byte[16];
		salt = "ABCDEFGH".getBytes(); // just for testing. in actual stroe sal talong with password
		return salt;
	}
	
	public static byte[] hash(char[] password,byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password,salt,ITERATIONS,KEY_LENGTH);
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
		byte[] salt = getSalt();
		byte[] encryptedPassword = hash(password.toCharArray(),salt);
		return Base64.getEncoder().encodeToString(encryptedPassword);
	}

	public static boolean validatePassword(String receivedPassword, String userPassword) {
		boolean status = false;

		String encryptedPassword = encrpyt(receivedPassword);
		status = encryptedPassword.equalsIgnoreCase(userPassword);
		return status;
	}
}
