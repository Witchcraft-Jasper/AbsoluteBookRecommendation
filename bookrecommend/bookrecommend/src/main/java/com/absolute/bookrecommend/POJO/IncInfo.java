package com.absolute.bookrecommend.POJO;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "inc")
public class IncInfo {
	/** collName : User incId : 1 */
	@Field
	private String collName;

	@Field private int incId;

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public int getIncId() {
		return incId;
	}

	public void setIncId(int incId) {
		this.incId = incId;
	}
}
