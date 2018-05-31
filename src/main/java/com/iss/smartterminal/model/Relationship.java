package com.iss.smartterminal.model;

import com.iss.smartterminal.utils.DateUtil;

public class Relationship extends BaseModel {

	private String docid;
	private String patid;

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getPatid() {
		return patid;
	}

	public void setPatid(String patid) {
		this.patid = patid;
	}

	@Override
	public String toString() {
		return "Relationship [docid=" + docid + ", patid=" + patid + ", id=" + id + ", addTime="
				+ DateUtil.convertDate(addTime) + ", updateTime=" + DateUtil.convertDate(updateTime) + ", delFlag="
				+ delFlag + ", exLong=" + exLong + ", exString=" + exString + "]";
	}
}
