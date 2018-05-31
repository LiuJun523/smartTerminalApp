package com.iss.smartterminal.dao.impl;

import java.sql.ResultSet;
import java.sql.Statement;

import com.iss.smartterminal.awshelper.RDSHelper;

public class BaseImpl {

	public int update(String sql) {
		
		int result = -1;
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			System.out.println(sql);
			int rs = stmt.executeUpdate(sql);
			result = rs > 0 ? 1 : 0;
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int exist(String sql) {
		
		int result = -1;
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			result = rs.next() ? 1 : 0;
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
