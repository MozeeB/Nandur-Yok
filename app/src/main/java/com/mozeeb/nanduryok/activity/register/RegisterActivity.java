package com.mozeeb.nanduryok.activity.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.activity.login.LoginActivity;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity implements RegisterContruct.View {

    private RegisterPresenter registerPresenter;

    private EditText nama, username,alamat, email, no_telp, password;
    private Button btn_regis;

    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setCancelable(false);

        nama = findViewById(R.id.edt_regis_nama);
        username = findViewById(R.id.edt_regis_username);
        alamat = findViewById(R.id.edt_alamat_regis);
        email = findViewById(R.id.edt_regis_email);
        no_telp = findViewById(R.id.edt_regis_phone);
        password = findViewById(R.id.edt_regis_password);
        btn_regis = findViewById(R.id.btn_create);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.onRegister(nama.getText().toString(),
                        username.getText().toString(),
                        alamat.getText().toString(),
                        email.getText().toString(),
                        no_telp.getText().toString(),
                        password.getText().toString());

            }
        });

    }

    @Override
    public void onRegisterSuccess(String message) {
        progressDialog.dismiss();
        Toasty.success(RegisterActivity.this, "Create account successfully!", Toasty.LENGTH_LONG).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

    }

    @Override
    public void onRegisterFailed(String message) {
        progressDialog.dismiss();
        Toasty.error(RegisterActivity.this, "Create account failed!", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void formValidate() {
        Toasty.error(RegisterActivity.this, "Field cannot be empty!", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void progressDialog() {
        progressDialog.show();
    }
}
