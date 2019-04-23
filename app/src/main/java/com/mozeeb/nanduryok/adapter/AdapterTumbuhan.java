package com.mozeeb.nanduryok.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.fragment.tumbuhan.TumbuhanPresenter;
import com.mozeeb.nanduryok.model.tumbuhan.TumbuhanItem;

import java.util.List;

public class AdapterTumbuhan extends RecyclerView.Adapter<AdapterTumbuhan.ViewHolder> {

    private List<TumbuhanItem> tumbuhanItems;
    private TumbuhanPresenter tumbuhanPresenter;
//    private Context context;

    public AdapterTumbuhan(List<TumbuhanItem> tumbuhanItems, TumbuhanPresenter tumbuhanPresenter) {
        this.tumbuhanItems = tumbuhanItems;
        this.tumbuhanPresenter = tumbuhanPresenter;
    }

    @NonNull
    @Override
    public AdapterTumbuhan.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tumbuhan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTumbuhan.ViewHolder viewHolder, final int i) {
        viewHolder.nama.setText(tumbuhanItems.get(i).getNamaTumbuhan());
        viewHolder.kondisi.setText(tumbuhanItems.get(i).getKondisi());
        viewHolder.stock.setText(tumbuhanItems.get(i).getStock());
        viewHolder.harga.setText(tumbuhanItems.get(i).getHarga());

        Glide.with(viewHolder.itemView.getContext()).load( tumbuhanItems.get(i).getFoto()).into(viewHolder.img);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tumbuhanPresenter.goDetailTumbuhan(tumbuhanItems.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tumbuhanItems == null){
            return 0;
        }
        return tumbuhanItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,kondisi, stock, harga;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama_tum);
            kondisi = itemView.findViewById(R.id.tv_kondisi);
            stock = itemView.findViewById(R.id.tv_stock);
            harga = itemView.findViewById(R.id.tv_harga);
            img = itemView.findViewById(R.id.img_tumbuhan);
        }
    }
}
