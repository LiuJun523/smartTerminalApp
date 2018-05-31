package com.iss.smartterminal.extra;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class DemoRedis {
	
    static Jedis jedis = null;  
//    static final String DATASOURCE_URL = "127.0.0.1";
    static final String DATASOURCE_URL = "smttappcache-001.zudsev.0001.apse1.cache.amazonaws.com";
    static final int DATASOURCE_SORT = 6379;
//    static final String DATASOURCE_PASS = "123456";
//    static final int DATASOURCE_SELECT = 1;
    
    public static void init() {
        jedis = new Jedis(DATASOURCE_URL, DATASOURCE_SORT);
//        jedis.auth(DATASOURCE_PASS);  
//        jedis.select(DATASOURCE_SELECT);
    }
    
    public static void add() {
    	Map<String, Double> map = new HashMap<>();
    	map.put("rec1", 1d);
    	map.put("rec2", 2d);
    	map.put("rec3", 5d);
    	map.put("rec4", 3d);
		jedis.zadd("reclist1",map);
	}
  
    public static Set<Tuple> get(){
        
        Set<Tuple> tuples = jedis.zrangeWithScores("reclist1", 0, -1);
        
        return tuples;
    }  
    
//	public static void main(String[] args) {
//		init();
//		add();
//		get();
//	}
}
