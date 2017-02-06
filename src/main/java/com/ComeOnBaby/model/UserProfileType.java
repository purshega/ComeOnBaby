package com.ComeOnBaby.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	CUSTOMER("CUSTOMER"),
	DBA("DBA"),
	ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}

}
