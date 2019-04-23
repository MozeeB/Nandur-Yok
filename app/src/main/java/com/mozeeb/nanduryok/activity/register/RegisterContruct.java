package com.mozeeb.nanduryok.activity.register;

public interface RegisterContruct {
    interface View{
        void onRegisterSuccess(String message);
        void onRegisterFailed(String message);
        void formValidate();
        void progressDialog();
    }
    interface Presenter{
        void onRegister(String nama, String username, String alamat, String email, String no_telp, String password);
    }
}
