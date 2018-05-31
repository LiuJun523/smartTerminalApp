package com.iss.smartterminal.model;

import java.util.List;

import com.iss.smartterminal.utils.DateUtil;

public class Record {

	private String recordId;
	private long addTime;
	private String patientId;
	private String patientName;
	private String imgUrl;
	private long updateTime;
	private List<Comment> comments;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {

		String strComments = "";
		if (comments != null && comments.size() > 0) {
			strComments = "[";
			for (Comment c : comments) {
				strComments = c.toString() + ",";
			}
			strComments.substring(0, strComments.length() - 1);
			strComments += "]";
		}
		return "Record [recordId=" + recordId + ", addTime=" + DateUtil.convertDate(addTime) + ", patientId="
				+ patientId + ", patientName=" + patientName + ", imgUrl=" + imgUrl + ", updateTime="
				+ DateUtil.convertDate(updateTime) + ", comments=" + strComments + "]";
	}
}
