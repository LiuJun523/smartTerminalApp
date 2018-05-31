package com.iss.smartterminal.pojo;

public class CommentPojo {

	private String docID;
	private String docName;
	private String content;
	private String recordId;

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
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

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return "CommentPojo [docID=" + docID + ", docName=" + docName + ", content=" + content + ", recordId="
				+ recordId + "]";
	}
}
