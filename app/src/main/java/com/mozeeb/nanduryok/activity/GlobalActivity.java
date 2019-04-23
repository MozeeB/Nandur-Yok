package com.mozeeb.nanduryok.activity;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.orhanobut.hawk.Hawk;

import okhttp3.OkHttpClient;

public class GlobalActivity extends Application {

    public static final String BASE_URL = "https://nanduryok.000webhostapp.com/nandur/index.php/Api/";

    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Sini mas", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        AndroidNetworking.initialize(this, okHttpClient);
    }
}
