package com.iss.smartterminal.extra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.RangeKeyCondition;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;

public class DemoDynamoDB {
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("ap-southeast-1").build();
	static DynamoDB dynamoDB = new DynamoDB(client);
	static String tableName = "Records";

	public static JSONObject createItems() {

		Table table = dynamoDB.getTable(tableName);
		JSONObject jo = new JSONObject();
		try {

			JSONObject jo1 = new JSONObject();
			jo1.put("docId", "testdid");
			jo1.put("docName", "testdname");
			jo1.put("content", "abc");
			jo1.put("caddTime", 23456);

			JSONObject jo2 = new JSONObject();
			jo2.put("docId", "testdid");
			jo2.put("docName", "testdname");
			jo2.put("content", "abc");
			jo2.put("caddTime", 23454);

			List<JSONObject> comments = new ArrayList<>();
			comments.add(jo1);
			comments.add(jo2);

			Item item = new Item().withPrimaryKey("recordId", "localtestid2").withString("patientId", "testpid2")
					.withString("patientName", "testpname").withList("comments", comments)
					.withString("addTime", "56709");
			PutItemOutcome outcome = table.putItem(item);
			Item itemresult = outcome.getItem();
			PutItemResult result = outcome.getPutItemResult();
			jo.put("result", result.getAttributes());
			jo.put("imresult", itemresult.toJSON());

		} catch (Exception e) {
			jo.put("result", e.getMessage());
		}
		return jo;
	}

	public static void getItem() {

		Table table = dynamoDB.getTable(tableName);

		try {

			// QuerySpec spec = new QuerySpec().withKeyConditionExpression("recordId =
			// :v_id")
			// .withValueMap(new ValueMap().withString(":v_id", "testid"));
			// QuerySpec spec = new QuerySpec().withHashKey("recordId", "testid");

			// QuerySpec spec1 = new QuerySpec().withKeyConditionExpression("patientId =
			// :pid")
			// .withValueMap(new ValueMap().withString(":pid", "testpid2"));
			// QuerySpec spec = new QuerySpec().withFilterExpression("patientId =
			// testpid2");

			Index index = table.getIndex("patientId-addTime-index");
			QuerySpec querySpec = new QuerySpec();
			RangeKeyCondition rangeKeyCondition = new RangeKeyCondition("addTime").gt("13");
			// querySpec.withKeyConditionExpression("patientId = :pid")
			// .withValueMap(new ValueMap().withString(":pid", "testpid2"));
			querySpec.withHashKey("patientId", "testpid2").withRangeKeyCondition(rangeKeyCondition);

			ItemCollection<QueryOutcome> items = index.query(querySpec);

			// ItemCollection<QueryOutcome> items = table.query(spec);

			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.toJSONPretty());
			}

		} catch (Exception e) {
			System.err.println("GetItem failed.");
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		scanItem();
	}

	private static void scanItem() {
		Table table = dynamoDB.getTable(tableName);

		try {

			// Index index = table.getIndex("patientId-addTime-index");
//			ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#pid, #at, recordId")
//					.withFilterExpression("#at > :time and #pid in (:start_yr,:end_yr)")
//					.withNameMap(new NameMap().with("#pid", "patientId").with("#at", "addTime"))
//					.withValueMap(new ValueMap().withString(":start_yr", "testpid").withString(":end_yr", "testpid2")
//							.withString(":time", "23"));
			
			ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#pid, #at, recordId")
					.withFilterExpression("#at > :time and #pid in (:start_yr,:end_yr)")
					.withNameMap(new NameMap().with("#pid", "patientId").with("#at", "addTime"))
					.withValueMap(new ValueMap().withString(":start_yr", "testpid").withString(":end_yr", "testpid2")
							.withString(":time", "23"));

			ItemCollection<ScanOutcome> items = table.scan(scanSpec);
			
			Iterator<Item> iterator = items.iterator();
			Item item = null;
			while (iterator.hasNext()) {
				item = iterator.next();
				System.out.println(item.toJSONPretty());
			}

		} catch (Exception e) {
			System.err.println("GetItem failed.");
			System.err.println(e.getMessage());
		}
	}
}
