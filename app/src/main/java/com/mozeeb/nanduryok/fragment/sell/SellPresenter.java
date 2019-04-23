package com.mozeeb.nanduryok.fragment.sell;

public class SellPresenter implements SellContruct.Presenter {

    private SellContruct.View view;

    public SellPresenter(SellContruct.View view) {
        this.view = view;
    }

    @Override
    public void onSell() {

    }

    @Override
    public void uploadFoto() {
        view.uploadFoto();
    }
}
