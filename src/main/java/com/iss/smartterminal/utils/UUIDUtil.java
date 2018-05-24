package com.iss.smartterminal.utils;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
