package com.mozeeb.nanduryok.activity.login;

import android.content.Context;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mozeeb.nanduryok.activity.GlobalActivity;
import com.mozeeb.nanduryok.model.user.UserItem;
import com.mozeeb.nanduryok.utils.SharedPref;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements LoginContruct.Presenter {

    private SharedPref sharedPrefManajer;
    private Context context;
    private UserItem userItem;


    LoginContruct.View view;

    public LoginPresenter(LoginContruct.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(final String username, final String password) {
        view.prgressDialog();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();

        }

        AndroidNetworking.post(GlobalActivity.BASE_URL + "login")
                .setPriority(Priority.HIGH)
                .addBodyParameter(jsonObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("status")){
                                view.onSuccessLogin(response.toString());

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.onFailedLogin(response.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.onFailedLogin(anError.toString());

                    }
                });


    }

    @Override
    public void loginClicked(EditText username, EditText password) {
        if (username.getText().length() > 0){
            view.ShowLogginIn();
        }else {
            view.ShowToastFormNotValid();
        }
    }

    @Override
    public void goRegister() {
        view.goRegister();

    }
}
