package com.mozeeb.nanduryok.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.activity.HomeActivity;
import com.mozeeb.nanduryok.activity.Splashscreen;
import com.mozeeb.nanduryok.activity.register.RegisterActivity;
import com.mozeeb.nanduryok.model.user.UserItem;
import com.mozeeb.nanduryok.utils.SharedPref;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements LoginContruct.View{

    private TextView creatAccount;
    private LoginPresenter presenter;
    private EditText username, password;
    private Button btn_login;

    private ProgressDialog progressDialog;
    private CheckBox remember;

    private SharedPreferences.Editor editor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sharedPrefManajer = new SharedPref(this);
//        sharedPrefManajer.checkLogin();

        username = findViewById(R.id.edt_username_login);
        password = findViewById(R.id.edt_pass_login);
        btn_login = findViewById(R.id.btn_login);
        remember = findViewById(R.id.remember_me);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);

        presenter = new LoginPresenter(this);
        editor = getSharedPreferences("Nandur", MODE_PRIVATE).edit();

        creatAccount = findViewById(R.id.tv_new_account);
        creatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goRegister();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginClicked(username, password);

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("Nandur", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("ingat", false)){
            Intent in = new Intent(LoginActivity.this, Splashscreen.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();
        }
    }

    @Override
    public void onSuccessLogin(String message) {
        progressDialog.dismiss();
        Toasty.success(LoginActivity.this, "Login Successfully!", Toasty.LENGTH_LONG).show();

    }

    @Override
    public void onFailedLogin(String message) {
        progressDialog.dismiss();
        Toasty.error(LoginActivity.this, "Login Failed!", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void goRegister() {
        Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(in);

    }

    @Override
    public void ShowLogginIn() {
        progressDialog.dismiss();
        if (remember.isChecked()){
            editor.putBoolean("ingat", true);
        }else {
            editor.putBoolean("ingat", false);
        }
        editor.apply();
        Toasty.success(LoginActivity.this, "Login Successfully!", Toasty.LENGTH_LONG).show();
        presenter.doLogin(username.getText().toString(), password.getText().toString());
        Intent in = new Intent(LoginActivity.this, Splashscreen.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    @Override
    public void ShowToastFormNotValid() {
        Toasty.error(LoginActivity.this, "Username and Password cannot be empty!", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void prgressDialog() {
        progressDialog.show();
    }

}
