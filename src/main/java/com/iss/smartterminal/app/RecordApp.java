package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.awshelper.ECacheHelper;
import com.iss.smartterminal.constant.ConfConstant;
import com.iss.smartterminal.dao.RecordDao;
import com.iss.smartterminal.dao.impl.RecordDaoImpl;
import com.iss.smartterminal.model.Comment;
import com.iss.smartterminal.model.Record;
import com.iss.smartterminal.pojo.CommentPojo;

import redis.clients.jedis.Tuple;

public class RecordApp {

	RecordDao recordDao = new RecordDaoImpl();

	public String doAction(String docid, int page) {

		JSONObject jo = new JSONObject();
		int state = -1;
		String msg = "";

		Set<Tuple> tuples = ECacheHelper.getJedis().zrevrangeByScoreWithScores(docid, new Date().getTime(), 0,
				(page - 1) * ConfConstant.PAGE_SIZE, ConfConstant.PAGE_SIZE);
		List<String> recIds = tuples.stream().map(t -> t.getElement()).collect(Collectors.toList());
		List<Record> records = recordDao.getByRecordids(recIds, new Date().getTime(), 0);
		
		//TODO 最小时间过滤下一页内容
		//最后一页内容拉取
		
		jo.put("state", state);
		jo.put("msg", msg);
		jo.put("page", page + 1);
		jo.put("records", JSONArray.toJSONString(records));

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
