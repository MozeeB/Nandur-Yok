package com.mozeeb.nanduryok.fragment.tumbuhan;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mozeeb.nanduryok.activity.GlobalActivity;
import com.mozeeb.nanduryok.model.tumbuhan.ResponseTumbuhan;
import com.mozeeb.nanduryok.model.tumbuhan.TumbuhanItem;

public class TumbuhanPresenter implements TumbuhanContruct.Presenter {

    private TumbuhanContruct.View view;

    public TumbuhanPresenter(TumbuhanContruct.View view) {
        this.view = view;
    }

    @Override
    public void getTumbuhan() {
        AndroidNetworking.get(GlobalActivity.BASE_URL + "getTumbuhan")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(ResponseTumbuhan.class, new ParsedRequestListener<ResponseTumbuhan>() {
                    @Override
                    public void onResponse(ResponseTumbuhan response) {
                        view.showTumbuhan(response.getTumbuhan());
                        view.onTumbuhanSuccess(response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.onTumbuhanFailed(anError.toString());
                    }
                });
    }

    @Override
    public void goDetailTumbuhan(TumbuhanItem tumbuhanItem) {
        view.goDetailTumbuhan(tumbuhanItem);
    }
}
