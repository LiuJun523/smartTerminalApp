package com.iss.smartterminal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.iss.smartterminal.awshelper.DynmDBHelper;
import com.iss.smartterminal.dao.RecordDao;
import com.iss.smartterminal.model.Comment;
import com.iss.smartterminal.model.Record;

public class RecordDaoImpl implements RecordDao {

	@Override
	public int add(Record record) {

		Table table = DynmDBHelper.getTable();
		int result = -1;

		try {
			Item item = new Item().withPrimaryKey("recordId", record.getRecordId())
					.withString("patientId", record.getPatientId()).withString("patientName", record.getPatientName())
					.withJSON("comments", JSON.toJSONString(record.getComments()))
					.withLong("addTime", record.getAddTime()).withLong("updateTime", record.getUpdateTime())
					.withString("imgUrl", record.getImgUrl());
			table.putItem(item);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int addComment(Record record) {

		Table table = DynmDBHelper.getTable();
		int result = -1;
		try {
			UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("recordId", record.getRecordId())
					.withReturnValues(ReturnValue.ALL_NEW).withUpdateExpression("set #c = :e1 and #ut = :e2")
					.withConditionExpression("#id = :c1")
					.withNameMap(new NameMap().with("#c", "comments").with("#ut", "updateTime").with("#id", "recordId"))
					.withValueMap(new ValueMap().withJSON(":e1", JSON.toJSONString(record.getComments()))
							.withLong(":e2", record.getUpdateTime()).withString(":c1", record.getRecordId()));

			table.updateItem(updateItemSpec);
			result = 1;
		} catch (Exception e) {
			System.err.println("Error updating item in " + table.getTableName());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Record> getByRecordids(List<String> recordIds, long addTime, int limit) {

		Table table = DynmDBHelper.getTable();
		List<Record> records = new ArrayList<>();

		String strRecParam = "";
		ValueMap valueMap = new ValueMap();
		for (int i = 0; i < recordIds.size(); i++) {
			strRecParam += ":id" + i + ",";
			valueMap.withString(":id" + i, recordIds.get(i));
		}
		strRecParam = strRecParam.substring(0, strRecParam.length() - 1);

		try {
			ScanSpec scanSpec = new ScanSpec()
					.withProjectionExpression("#at, #recId, patientId, patientName, updateTime, imgUrl, comments")
					.withFilterExpression("#at < :time and #recId in (" + strRecParam + ")")
					.withNameMap(new NameMap().with("#recId", "recordId").with("#at", "addTime"))
					.withValueMap(valueMap.withLong(":time", addTime));
			if (limit > 0) {
				scanSpec.withMaxResultSize(limit);
			}

			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				Record record = getRecordFromItem(item);
				records.add(record);
			}

		} catch (Exception e) {
			System.err.println("GetItem failed.");
			System.err.println(e.getMessage());
		}

		return records;
	}

	@Override
	public List<Record> getByPatientids(List<String> patientIds, long addTime, int limit) {

		Table table = DynmDBHelper.getTable();
		List<Record> records = new ArrayList<>();

		String strPatParam = "";
		ValueMap valueMap = new ValueMap();
		for (int i = 0; i < patientIds.size(); i++) {
			strPatParam += ":id" + i + ",";
			valueMap.withString(":id" + i, patientIds.get(i));
		}
		strPatParam = strPatParam.substring(0, strPatParam.length() - 1);

		try {
			ScanSpec scanSpec = new ScanSpec()
					.withProjectionExpression("#at, recordId, #pid, patientName, updateTime, imgUrl, comments")
					.withFilterExpression("#at < :time and #pid in (" + strPatParam + ")")
					.withNameMap(new NameMap().with("#pid", "patientId").with("#at", "addTime"))
					.withValueMap(valueMap.withLong(":time", addTime));
			if (limit > 0) {
				scanSpec.withMaxResultSize(limit);
			}

			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				Record record = getRecordFromItem(item);
				records.add(record);
			}

		} catch (Exception e) {
			System.err.println("GetItem failed.");
			e.printStackTrace();
		}

		return records;
	}

	@Override
	public Map<String, Double> getIdandAtimeByPatientids(List<String> patientIds, long addTime, int limit) {

		Table table = DynmDBHelper.getTable();
		Map<String, Double> recMap = new HashMap<>();

		String strPatParam = "";
		ValueMap valueMap = new ValueMap();
		for (int i = 0; i < patientIds.size(); i++) {
			strPatParam += ":id" + i + ",";
			valueMap.withString(":id" + i, patientIds.get(i));
		}
		strPatParam = strPatParam.substring(0, strPatParam.length() - 1);

		try {
			ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#at, recordId, #pid")
					.withFilterExpression("#at < :time and #pid in (" + strPatParam + ")")
					.withNameMap(new NameMap().with("#pid", "patientId").with("#at", "addTime"))
					.withValueMap(valueMap.withLong(":time", addTime));
			if (limit > 0) {
				scanSpec.withMaxResultSize(limit);
			}

			ItemCollection<ScanOutcome> items = table.scan(scanSpec);

			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				recMap.put(item.getString("recordId"), (double) item.getLong("addTime"));
			}

		} catch (Exception e) {
			System.err.println("GetItem failed.");
			e.printStackTrace();
		}

		return recMap;
	}

	private Record getRecordFromItem(Item item) {

		Record record = new Record();
		record.setRecordId(item.getString("recordId"));
		record.setAddTime(item.getLong("addTime"));
		record.setImgUrl(item.getString("imgUrl"));
		record.setPatientName(item.getString("patientName"));
		record.setUpdateTime(item.getLong("updateTime"));
		record.setPatientId(item.getString("patientId"));

		JSONArray ja = JSONArray.parseArray(item.getJSON("comments"));
		List<Comment> comments = ja.toJavaList(Comment.class);
		record.setComments(comments);

		return record;
	}
}
