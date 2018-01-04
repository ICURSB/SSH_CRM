package com.hi.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import com.sun.org.apache.bcel.internal.util.SecuritySupport;

public class MD5_BASE64 {
	
/*	public static void main(String[] args) {
		System.out.println(md5("hello"));
		byte[] bytes = "FF".getBytes();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			System.out.print(b + "\t");
		}
		System.err.println(new BigInteger("17990").toString(16));
	}*/
	
	
	public static String md5(String input){
		try {
			byte[] digest = MessageDigest.getInstance("md5").digest(input.getBytes());
//			for (int i = 0; i < digest.length; i++) {
//				byte b = digest[i];
//				System.out.println(b);
//			}
			String string = new BigInteger(1, digest).toString(16);
			
			for (int i = 0; i < 32 - string.length(); i++) {
				string = "0" + string;
			}
			return string;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String base64(String string){
		
		return null;
	}
}
