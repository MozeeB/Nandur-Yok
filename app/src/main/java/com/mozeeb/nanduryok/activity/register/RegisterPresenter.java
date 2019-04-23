package com.mozeeb.nanduryok.activity.register;

import android.util.Patterns;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mozeeb.nanduryok.activity.GlobalActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterPresenter implements RegisterContruct.Presenter {

    private RegisterContruct.View view;

    public RegisterPresenter(RegisterContruct.View view) {
        this.view = view;
    }

    @Override
    public void onRegister(String nama, String username, String alamat, String email, String no_telp, String password) {
        view.progressDialog();

        if (nama.length() > 0 && username.length() > 0
                && alamat.length() > 0 && email.length() > 0
                && no_telp.length() > 0 && password.length() > 0 && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && Patterns.PHONE.matcher(no_telp).matches()){
            AndroidNetworking.post(GlobalActivity.BASE_URL + "register")
                    .setPriority(Priority.HIGH)
                    .addBodyParameter("nama", nama)
                    .addBodyParameter("username", username)
                    .addBodyParameter("alamat", alamat)
                    .addBodyParameter("email", email)
                    .addBodyParameter("no_telp", no_telp)
                    .addBodyParameter("password", password)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("status")){
                                    view.onRegisterSuccess(response.toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                view.onRegisterFailed(response.toString());
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            view.onRegisterFailed(anError.toString());
                        }
                    });
        }else {
            view.formValidate();
        }

    }
}
