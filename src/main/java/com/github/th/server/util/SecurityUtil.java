package com.github.th.server.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;


public class SecurityUtil {
	
	private final static String loginSalt = "TONGHANG-SECRET";
	
	private static SecretKeySpec keySpec = new SecretKeySpec(loginSalt.getBytes(), "HmacSHA1");
	
	private static Mac mac = null;
	static {
		try {
			mac = Mac.getInstance("HmacSHA1");
			mac.init(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("init key error",e);
		}
		
	}

	public static String generateLoginToken(String id, String email) throws Exception {
		return id + ":" + email + ":" + encode(email);
	}
	
	private static String encode(String message) throws Exception {
		byte[] rawHmac = mac.doFinal(message.getBytes());
		return Hex.encodeHexString(rawHmac);
	}

}
