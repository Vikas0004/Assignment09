package com.nagarro.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Authors {	
	
	private String authorName;
	
	public Authors() {
		
	}
	
	public Authors(String authorName) {
		super();
		this.authorName = authorName;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
