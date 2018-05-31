package com.iss.smartterminal.awshelper;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynmDBHelper {

	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("ap-southeast-1").build();
	private static DynamoDB dynamoDB = new DynamoDB(client);
	private static String tableName = "Record";
	
	public static Table getTable() {
		return dynamoDB.getTable(tableName);
	}
	
}
