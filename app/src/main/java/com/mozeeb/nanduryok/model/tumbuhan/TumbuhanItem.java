package com.mozeeb.nanduryok.model.tumbuhan;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TumbuhanItem implements Serializable{

	@SerializedName("jenis_tumbuhan")
	private String jenisTumbuhan;

	@SerializedName("terjual")
	private String terjual;

	@SerializedName("kondisi")
	private String kondisi;

	@SerializedName("harga")
	private String harga;

	@SerializedName("foto")
	private String foto;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("stock")
	private String stock;

	@SerializedName("nama_tumbuhan")
	private String namaTumbuhan;

	public String getJenisTumbuhan(){
		return jenisTumbuhan;
	}

	public String getTerjual(){
		return terjual;
	}

	public String getKondisi(){
		return kondisi;
	}

	public String getHarga(){
		return harga;
	}

	public String getFoto(){
		return foto;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public String getStock(){
		return stock;
	}

	public String getNamaTumbuhan(){
		return namaTumbuhan;
	}
}