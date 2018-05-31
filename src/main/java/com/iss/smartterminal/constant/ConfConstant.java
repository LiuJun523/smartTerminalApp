package com.iss.smartterminal.constant;

import java.io.FileInputStream;
import java.util.Properties;
import com.iss.smartterminal.awshelper.RDSHelper;
import com.iss.smartterminal.utils.StringUtil;

public class ConfConstant {

	public static int MAX_FEED = 10;
	public static int PAGE_SIZE = 2;

	static {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(
					RDSHelper.class.getClassLoader().getResource("properties/conf.properties").getPath()));
			MAX_FEED = StringUtil.tryParseInt(prop.getProperty("max_feed"), 10);
			PAGE_SIZE = StringUtil.tryParseInt(prop.getProperty("page_size"), 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
