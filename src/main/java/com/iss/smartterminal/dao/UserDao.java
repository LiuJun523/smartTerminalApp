package com.iss.smartterminal.dao;

import com.iss.smartterminal.entity.User;

public interface UserDao {
	
	  void register(User user);  
	  User validateUser(User login); 
}
