package com.mozeeb.nanduryok.model.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserItem implements Serializable {

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("alamat")
	private String alamat;

	public String getPassword(){
		return password;
	}

	public String getNama(){
		return nama;
	}

	public String getFoto(){
		return foto;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public String getAlamat(){
		return alamat;
	}
}