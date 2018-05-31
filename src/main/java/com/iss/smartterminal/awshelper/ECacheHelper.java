package com.iss.smartterminal.awshelper;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import com.iss.smartterminal.constant.ConfConstant;
import com.iss.smartterminal.utils.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class ECacheHelper {

	private static Jedis jedis = null;

	public static Jedis getJedis() {

		if (jedis == null) {
			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream(
						RDSHelper.class.getClassLoader().getResource("properties/redis.properties").getPath()));
				String url = prop.getProperty("url");
				int port = StringUtil.tryParseInt(prop.getProperty("port"), 6379);
				jedis = new Jedis(url, port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jedis;
	}

	public static void rmExtra(String docid) {

		if (getJedis().zcard(docid) > ConfConstant.MAX_FEED) {

			Set<Tuple> tuples = getJedis().zrevrangeByScoreWithScores(docid, new Date().getTime(), 0,
					ConfConstant.MAX_FEED - 1, 1);
			double minscore = tuples.stream().map(tuple -> tuple.getScore()).collect(Collectors.toList()).get(0);
			getJedis().zremrangeByScore(docid, 0, minscore - 1);
		}
	}
}
