package com.iss.smartterminal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iss.smartterminal.awshelper.RDSHelper;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.model.Relationship;
import com.iss.smartterminal.utils.DateUtil;
import com.iss.smartterminal.utils.IDUtil;

public class RelationshipDaoImpl implements RelationshipDao {

	@Override
	public int subscribe(String doctorId, String patientId) {

		String sql = String.format(
				"insert into relationship(id,addTime,delFlag,updateTime,docid,patid) values('%s', %d, %d, '%s', '%s', '%s')",
				IDUtil.generateID(), new Date().getTime(), 0, DateUtil.convertDate(new Date()), doctorId, patientId);

		return new BaseImpl().update(sql);
	}

	@Override
	public int unsubscribe(String doctorId, String patientId) {

		String sql = String.format(
				"update relationship set updateTime='%s',delFlag=1 where docid='%s' and patid='%s';",
				DateUtil.convertDate(new Date()), doctorId, patientId);

		return new BaseImpl().update(sql);
	}
	
	@Override
	public List<Relationship> listByDoctorid(String doctorId) {

		List<Relationship> relationships = new ArrayList<>();
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM relationship where delFlag=0 and docid='" + doctorId + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Relationship relationship = getRelationshipFromRS(rs);
				relationships.add(relationship);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationships;
	}

	@Override
	public List<Relationship> listByPatientid(String patientId) {

		List<Relationship> relationships = new ArrayList<>();
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = "SELECT * FROM relationship where delFlag=0 and patid='" + patientId + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Relationship relationship = getRelationshipFromRS(rs);
				relationships.add(relationship);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return relationships;
	}

	@Override
	public int hasSubscribed(String doctorId, String patientId) {

		int result = -1;
		try {
			Statement stmt = RDSHelper.getConnection().createStatement();
			String sql = String.format(
					"SELECT id FROM relationship where delFlag=0 and docid='%s' and patid='%s' limit 1", doctorId,
					patientId);
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

	private Relationship getRelationshipFromRS(ResultSet rs) throws SQLException {

		Relationship relationship = new Relationship();

		relationship.setId(rs.getString("id"));
		relationship.setAddTime(rs.getLong("addTime"));
		relationship.setDelFlag(rs.getInt("delFlag"));
		relationship.setUpdateTime(rs.getDate("updateTime"));
		relationship.setExLong(rs.getLong("exLong"));
		relationship.setExString(rs.getString("exString"));
		relationship.setDocid(rs.getString("docid"));
		relationship.setPatid(rs.getString("patid"));

		return relationship;
	}

}
