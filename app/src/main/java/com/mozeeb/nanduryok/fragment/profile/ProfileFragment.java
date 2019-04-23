package com.mozeeb.nanduryok.fragment.profile;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.activity.login.LoginActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileContruct.View {

    private Button logout;

    private ProfilePresenter profilePresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    TextView nama, username, alamat, email, no_telp;


    public ProfileFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editor = this.getActivity().getSharedPreferences("Nandur", MODE_PRIVATE).edit();

        nama = view.findViewById(R.id.detail_nama);
        username = view.findViewById(R.id.tv_username_profile);
        alamat = view.findViewById(R.id.tv_alamat_profile);
        email = view.findViewById(R.id.tv_email_profil);
        no_telp = view.findViewById(R.id.tv_phone_profile);

        logout = view.findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogout();

            }
        });

    }

    @Override
    public void onLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
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
                        Intent log = new Intent(getActivity(), LoginActivity.class);
                        log.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        log.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        editor.remove("ingat");
                        editor.apply();
                        startActivity(log);
                        getActivity().onBackPressed();
                    }
                });
        AlertDialog buil = builder.create();
        buil.show();
    }
}
