package com.iss.smartterminal.model;

import com.iss.smartterminal.utils.DateUtil;

public class Comment {

	private String docid;
	private String docName;
	private String content;
	private long addTime;

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "Comment [docid=" + docid + ", docName=" + docName + ", content=" + content + ", addTime="
				+ DateUtil.convertDate(addTime) + "]";
	}
}
