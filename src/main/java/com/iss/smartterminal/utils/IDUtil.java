package com.iss.smartterminal.utils;

import java.util.UUID;

public class IDUtil {
	
	public static String generateID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
