package com.ahrgahh.timon;

public class Call {

	private long id;
	private String comment;
	public String name ="?";
	public String number ="?";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return comment;
	}

}
