package com.nagarro.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails 
{
	
	
	public UserDetails() {
		
	}
	
	public UserDetails(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Id
	String userName;
	
	String password;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
