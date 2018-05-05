package com.lshop.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 加密工具
 * @author tangd
 *
 */
public class Sha256Util {
	public static String encode(String s) {
		String des = "";
		String tmp = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(s.getBytes());
			for (int i = 0; i < digest.length; i++) {
				tmp = (Integer.toHexString(digest[i] & 0xFF));
				if (tmp.length() == 1) {
					des += "0";
				}
				des += tmp;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return des;
	}
}
