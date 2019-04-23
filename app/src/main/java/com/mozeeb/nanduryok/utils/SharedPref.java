package com.mozeeb.nanduryok.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mozeeb.nanduryok.activity.Splashscreen;
import com.mozeeb.nanduryok.activity.login.LoginActivity;

public class SharedPref {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NO_TELEPON = "no_tlp";
    private static final String KEY_PASSWORD = "password";
    private static final String is_login = "loginstatus";
    private final String SHARED_NAME = "loginsesion";
    private final int MODE_PRIVATE = 0;
    private Context Mcontext;

    public SharedPref(Context context) {
        this.Mcontext = context;
        sp = Mcontext.getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String user){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, user);
        editor.commit();
    }

    public void setIdUser(String idUser){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_ID, idUser);
        editor.commit();
    }
    public String getIdUser() {
        return sp.getString(KEY_ID, "");

    }

    public String getUsername(){
        return sp.getString(KEY_USERNAME, "");
    }
    public void setUsername(String username){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }
    public String getNamaUser(){
        return sp.getString(KEY_NAMA, "");
    }
    public void setUsernama(String nama){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NAMA, nama);
        editor.commit();
    }
    public void set_telp(String telp){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_NO_TELEPON, telp);
        editor.commit();
    }
    public String getTelp(){
        return sp.getString(KEY_NO_TELEPON, "");

    }
    public void setAlamatPref(String alamat){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_ALAMAT, alamat);
        editor.commit();

    }
    public String getAlamatPref(){
        return sp.getString(KEY_ALAMAT, "");
    }
    public void setEmailPref(String emailPref){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, emailPref);
        editor.commit();

    }
    public String getEmailPref(){
        return sp.getString(KEY_EMAIL,"");
    }

    public void setPassword(String pass){
        editor.putBoolean(is_login, true);
        editor.putString(KEY_PASSWORD, pass);
        editor.commit();
    }
    public String getPass(){
        return sp.getString(KEY_PASSWORD, "");
    }

    public void checkLogin(){
//        if (!this.Login()){
//            Intent intent = new Intent(Mcontext, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            Mcontext.startActivity(intent);
//        }
        if (this.Login()){
            Intent intent = new Intent(Mcontext, Splashscreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Mcontext.startActivity(intent);
        }
    }

    private boolean Login() {
        return sp.getBoolean(is_login, false);
    }
    public void Logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(Mcontext, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Mcontext.startActivity(i);
    }


//    public void setFotoPref(String fotoPref){
//        editor.putBoolean(is_login, true);
//        editor.putString(KEY_FOTO, fotoPref);
//        editor.commit();
//    }
//    public String getFotoPref(){
//        return sp.getString(KEY_FOTO,"");
//    }

}
