package com.mozeeb.nanduryok.fragment.sell;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mozeeb.nanduryok.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment implements SellContruct.View {

    EditText nama, jenis, stock, kondisi, harga, terjual;
    ImageView foto;
    Button btn_upload, btn_sell;

    private Uri filepath;
    private String mediapath;
    private Bitmap mPhoto;
    String part_image;
    //permission granted
    private int STORAGE_PERMISSION_CODE = 1;

    public final int REQ_CHOOSE_FILE_REGISTER = 100;
    private SellPresenter sellPresenter;



    public SellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.edt_nama_sell);
        jenis = view.findViewById(R.id.edt_jenis_sell);
        stock = view.findViewById(R.id.edt_stock_sell);
        kondisi = view.findViewById(R.id.edt_kondisi_sell);
        harga = view.findViewById(R.id.edt_harga_sell);
        terjual = view.findViewById(R.id.edt_terjual_sell);
        foto = view.findViewById(R.id.img_sell);
        btn_upload = view.findViewById(R.id.btn_upload_sell);
        btn_sell = view.findViewById(R.id.btn_sell);

        sellPresenter = new SellPresenter(this);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellPresenter.uploadFoto();
            }
        });
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(getActivity(), "this feature is not yet available", Toasty.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onSuccessSell() {
        part_image = getPath(filepath);

    }

    @Override
    public void onFailedSell() {

    }

    @Override
    public void uploadFoto() {
        ChooseImage(REQ_CHOOSE_FILE_REGISTER);
    }

    private void ChooseImage(int requestCode){
        Intent toGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(toGalery, requestCode);
        Log.i("Gallery", "Masuk Gallery");

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toasty.success(getActivity(), "You have already granted this permission!", Toasty.LENGTH_SHORT).show();
        } else {
            requestStoragePermission();
        }
    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(getActivity())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else {
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQ_CHOOSE_FILE_REGISTER){
                if (data.getData() != null){
                    filepath = data.getData();
//                    Uri seletedImage = data.getData();
                }
                try {
                    mPhoto = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filepath);
                    foto.setImageBitmap(mPhoto);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String getPath(Uri filepath){
        Cursor cursor = getActivity().getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images
                        .Media._ID +  " = ? ", new String[]{document_id}, null);

        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;


    }
}
