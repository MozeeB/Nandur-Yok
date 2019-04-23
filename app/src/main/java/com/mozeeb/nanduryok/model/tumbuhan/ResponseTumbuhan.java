package com.mozeeb.nanduryok.model.tumbuhan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTumbuhan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("tumbuhan")
	private List<TumbuhanItem> tumbuhan;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public List<TumbuhanItem> getTumbuhan(){
		return tumbuhan;
	}

	public boolean isStatus(){
		return status;
	}
}