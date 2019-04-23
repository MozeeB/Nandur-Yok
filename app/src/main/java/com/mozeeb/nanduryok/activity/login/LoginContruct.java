package com.mozeeb.nanduryok.activity.login;

import android.widget.EditText;

public interface LoginContruct {
    interface View{
        void onSuccessLogin(String message);
        void onFailedLogin(String message);
        void goRegister();
        void ShowLogginIn();
        void ShowToastFormNotValid();
        void prgressDialog();

    }
    interface Presenter{
        void doLogin(String username, String password);
        void loginClicked(EditText username, EditText password);
        void goRegister();
    }
}
