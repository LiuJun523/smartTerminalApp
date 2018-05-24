package com.iss.smartterminal.dao.impl;

import com.iss.smartterminal.entity.User;
import com.iss.smartterminal.extra.DemoMysql;

public class UserDaoImpl {

	public int register(User user) {
		
		return DemoMysql.addUser(user);
	}
	
}