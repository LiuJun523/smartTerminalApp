package com.iss.smartterminal.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.iss.smartterminal.awshelper.ECacheHelper;
import com.iss.smartterminal.constant.ConfConstant;
import com.iss.smartterminal.dao.RecordDao;
import com.iss.smartterminal.dao.RelationshipDao;
import com.iss.smartterminal.dao.impl.RecordDaoImpl;
import com.iss.smartterminal.dao.impl.RelationshipDaoImpl;

public class SubscribeApp {

	RelationshipDao relationshipDao = new RelationshipDaoImpl();
	RecordDao recordDao = new RecordDaoImpl();

	public String doAction(String docid, String patid, boolean isSubscribe) {

		JSONObject jo = new JSONObject();

		Map<String, Object> resMap;

		if (isSubscribe) {
			resMap = handleSubscribe(docid, patid);
		} else {
			resMap = handleUnsubscribe(docid, patid);
		}
		jo.put("state", (int) resMap.get("state"));
		jo.put("msg", (String) resMap.get("msg"));

		return jo.toJSONString();
	}

	private Map<String, Object> handleUnsubscribe(String docid, String patid) {

		Map<String, Object> resMap = new HashMap<>();
		int state = 0;
		String msg = "";
		if (relationshipDao.hasSubscribed(docid, patid) > 0) {
			if (relationshipDao.unsubscribe(docid, patid) > 0) {
				state = 1;
				msg = "unsubscribe success";
			} else {
				msg = "unsubscribe failed";
			}

		} else {
			msg = "doctor has not subscribed the patient: " + docid + " | " + patid;
		}
		resMap.put("state", state);
		resMap.put("msg", msg);

		return resMap;
	}

	private Map<String, Object> handleSubscribe(String docid, String patid) {

		Map<String, Object> resMap = new HashMap<>();
		int state = -1;
		String msg = "";

		if (relationshipDao.hasSubscribed(docid, patid) > 0) {
			msg = "doctor has subscribed the patient: " + docid + " | " + patid;
		} else {
			if (relationshipDao.subscribe(docid, patid) > 0) {
				List<String> patientIds = new ArrayList<>();
				patientIds.add(patid);
				Map<String, Double> recMap = recordDao.getIdandAtimeByPatientids(patientIds, new Date().getTime(),
						ConfConstant.MAX_FEED);
				ECacheHelper.getJedis().zadd(docid, recMap);
				ECacheHelper.rmExtra(docid);
				state = 1;
				msg = "subscribe success";
			} else {
				msg = "subscribe failed";
			}
		}

		resMap.put("state", state);
		resMap.put("msg", msg);

		return resMap;
	}
}
