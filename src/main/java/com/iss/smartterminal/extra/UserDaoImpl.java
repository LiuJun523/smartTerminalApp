package com.iss.smartterminal.extra;

import com.iss.smartterminal.entity.User;

public class UserDaoImpl {

	public int register(User user) {
		return DemoMysql.addUser(user);
	}
	
}