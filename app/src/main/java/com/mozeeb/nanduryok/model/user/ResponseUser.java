package com.mozeeb.nanduryok.model.user;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseUser{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("user")
	private List<UserItem> user;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public List<UserItem> getUser(){
		return user;
	}

	public boolean isStatus(){
		return status;
	}
}