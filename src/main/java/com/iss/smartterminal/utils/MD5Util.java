package com.iss.smartterminal.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {

	public static String encryp(String str) {

		String enc = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			enc = new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enc;
	}
}
