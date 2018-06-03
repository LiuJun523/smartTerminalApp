package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.awshelper.ECacheHelper;
import com.iss.smartterminal.constant.ConfConstant;
import com.iss.smartterminal.dao.RecordDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.RecordDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;
import com.iss.smartterminal.interceptor.UserToken;
import com.iss.smartterminal.model.Comment;
import com.iss.smartterminal.model.Record;
import com.iss.smartterminal.model.Relationship;
import com.iss.smartterminal.pojo.CommentPojo;

import redis.clients.jedis.Tuple;

public class RecordApp {

	RecordDao recordDao = new RecordDaoImpl();
	RelationshipDao relationshipDao = new RelationshipDaoImpl();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doAction(long minTime, HttpSession session) {

		UserToken userToken = (UserToken) session.getAttribute("userToken");
		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "get records error";
		minTime = minTime == 0 ? new Date().getTime() : minTime;

		List<Relationship> relationships = relationshipDao.listByDoctorid(userToken.getUserId());
		List<String> patIds = relationships.stream().map(r -> r.getPatid()).collect(Collectors.toList());

		Set<Tuple> tuples = ECacheHelper.getJedis().zrevrangeByScoreWithScores(userToken.getUserId(), minTime - 1, 0, 0,
				ConfConstant.PAGE_SIZE);

		if (tuples != null && tuples.size() > 0) {
			List<String> recIds = new ArrayList<>();
			String element, pid, recid;
			for (Tuple tuple : tuples) {
				element = tuple.getElement();
				pid = element.split("_")[0];
				recid = element.split("_")[1];

				if (patIds.contains(pid)) {
					recIds.add(recid);
				}
			}

			List<Record> records = recordDao.getByRecordids(recIds, minTime, recIds.size());
			Collections.sort(records, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					Long at1 = ((Record) o1).getAddTime();
					Long at2 = ((Record) o2).getAddTime();
					return at2.compareTo(at1);
				}
			});
			jo.put("records", JSONArray.toJSONString(records));
			state = 1;
			msg = "get records success";
		} else {
			state = 0;
			msg = "no more records";
		}

		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	public String doAction(CommentPojo cPojo) {

		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "";

		Comment comment = generageComment(cPojo);
		List<String> recIds = new ArrayList<>();
		recIds.add(cPojo.getRecordId());
		Record record = recordDao.getByRecordids(recIds, new Date().getTime(), 1).get(0);
		record.getComments().add(comment);

		if (recordDao.addComment(record) > 0) {
			state = 1;
			msg = "add comment success";
		} else {
			state = 0;
			msg = "add comment failed";
		}

		jo.put("state", state);
		jo.put("msg", msg);

		return jo.toJSONString();
	}

	private Comment generageComment(CommentPojo cPojo) {

		Comment comment = new Comment();
		comment.setAddTime(new Date().getTime());
		comment.setContent(cPojo.getContent());
		comment.setDocid(cPojo.getDocID());
		comment.setDocName(cPojo.getDocName());

		return comment;
	}
}
