package com.mozeeb.nanduryok.fragment.tumbuhan;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozeeb.nanduryok.R;
import com.mozeeb.nanduryok.activity.detailTumbuhan.DetailTumbuhanActivity;
import com.mozeeb.nanduryok.adapter.AdapterTumbuhan;
import com.mozeeb.nanduryok.model.tumbuhan.TumbuhanItem;
import com.mozeeb.nanduryok.model.user.UserItem;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class TumbuhanFragment extends Fragment implements TumbuhanContruct.View {

    private TumbuhanPresenter presenter;
    private RecyclerView rv_tum;
    private AdapterTumbuhan adapterTumbuhan;
    private Context context;
    private UserItem userItem;



    public TumbuhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tumbuhan2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new TumbuhanPresenter(this);

        rv_tum = view.findViewById(R.id.rv_tumbuhan);
        rv_tum.setHasFixedSize(true);
        rv_tum.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        presenter.getTumbuhan();
    }

    @Override
    public void onTumbuhanSuccess(String message) {
        Toasty.success(getActivity(), "Welcome!" , Toasty.LENGTH_LONG).show();
    }

    @Override
    public void onTumbuhanFailed(String message) {
        Toasty.error(getActivity(), "Connection Error!", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void goDetailTumbuhan(TumbuhanItem tumbuhanItem) {
        Intent de = new Intent(getActivity(), DetailTumbuhanActivity.class);
        de.putExtra("tumbuhan", tumbuhanItem);
        startActivity(de);
    }

    @Override
    public void showTumbuhan(List tumbuhan) {
        adapterTumbuhan = new AdapterTumbuhan(tumbuhan, presenter);
        rv_tum.setAdapter(adapterTumbuhan);

    }
}
