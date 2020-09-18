package com.treinaWeb.curse.resources.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografar {
	
	public static String encriptografar(String password) {
		String retorno = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
			retorno = hash.toString(16);
			
		}catch(Exception e) {
			
		}
		
		
		return retorno;
	}
}
