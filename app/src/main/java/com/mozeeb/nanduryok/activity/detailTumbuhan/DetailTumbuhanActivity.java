package com.mozeeb.nanduryok.activity.detailTumbuhan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.model.tumbuhan.TumbuhanItem;

import es.dmoral.toasty.Toasty;

public class DetailTumbuhanActivity extends AppCompatActivity {

    TextView nama, jenis, stock, kondisi, harga ,terjual;
    ImageView foto;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tumbuhan);



        final TumbuhanItem tumbuhanItem = (TumbuhanItem) getIntent().getSerializableExtra("tumbuhan");

        nama = findViewById(R.id.detail_nama);
        jenis = findViewById(R.id.detail_jenis);
        stock = findViewById(R.id.detail_stock);
        kondisi = findViewById(R.id.detail_kondisi);
        harga = findViewById(R.id.detail_harga);
        terjual = findViewById(R.id.detail_terjual);
        phone = findViewById(R.id.btn_beli);
        foto = findViewById(R.id.img_detail_tum);

        nama.setText(tumbuhanItem.getNamaTumbuhan());
        jenis.setText(tumbuhanItem.getJenisTumbuhan());
        stock.setText(tumbuhanItem.getStock());
        kondisi.setText(tumbuhanItem.getKondisi());
        harga.setText(tumbuhanItem.getHarga());
        terjual.setText(tumbuhanItem.getTerjual());
        phone.setText(tumbuhanItem.getNoTelp());

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCallBtnClick();
                phoneCall();
            }
        });


        Glide.with(this).load(tumbuhanItem.getFoto()).into(foto);


    }
    private void onCallBtnClick(){
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall();
        }else {

            if (ActivityCompat.checkSelfPermission(DetailTumbuhanActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall();
            }else {
                final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                //Asking request Permissions
                ActivityCompat.requestPermissions(DetailTumbuhanActivity.this, PERMISSIONS_STORAGE, 9);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
            Toasty.error(DetailTumbuhanActivity.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(DetailTumbuhanActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
            DetailTumbuhanActivity.this.startActivity(callIntent);
        }else{
            Toasty.error(DetailTumbuhanActivity.this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

}
