package com.mozeeb.nanduryok.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.activity.login.LoginActivity;
import com.mozeeb.nanduryok.fragment.sell.SellFragment;
import com.mozeeb.nanduryok.fragment.profile.ProfileFragment;
import com.mozeeb.nanduryok.fragment.tumbuhan.TumbuhanFragment;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TumbuhanFragment()).commit();

        editor = getSharedPreferences("Nandur", MODE_PRIVATE).edit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_logout:
                alertDialogShow();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new TumbuhanFragment();
                    break;
                case R.id.navigation_profile:
                    Toasty.success(HomeActivity.this, "Welcome to Profile!", Toasty.LENGTH_LONG).show();
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.navigation_sell:
                    Toasty.success(HomeActivity.this, "Welcome to Sell!", Toasty.LENGTH_LONG).show();
                    selectedFragment = new SellFragment();
                    break;
            }
            FragmentManager mFragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };




    private void alertDialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Keluar dari akun ini ?")
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent log = new Intent(HomeActivity.this, LoginActivity.class);
                        log.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        log.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        editor.remove("ingat");
                        editor.apply();
                        startActivity(log);
                        finish();
                    }
                });
        AlertDialog buil = builder.create();
        buil.show();
    }
}
