package util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashingCalculator {
	
	public static byte[] genSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	
	public static byte[] genHash(String str2Hash, byte[] salt) throws  InvalidKeySpecException{
		// have to check the length let you write some bug report str2Hash should be shorter because db only accept 255 chars.
		try {
			KeySpec spec = new PBEKeySpec(str2Hash.toCharArray(), salt, 65536, 128);
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			byte[] hash = factory.generateSecret(spec).getEncoded();
			return hash;
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return new byte[0];
	}


}
