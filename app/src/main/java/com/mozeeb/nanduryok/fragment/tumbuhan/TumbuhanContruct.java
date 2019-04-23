package com.mozeeb.nanduryok.fragment.tumbuhan;

import com.mozeeb.nanduryok.model.tumbuhan.TumbuhanItem;

import java.util.List;

public interface TumbuhanContruct {
    interface View{
        void onTumbuhanSuccess(String message);
        void onTumbuhanFailed(String message);
        void goDetailTumbuhan(TumbuhanItem tumbuhanItem);
        void showTumbuhan(List tumbuhan);
    }
    interface Presenter{
        void getTumbuhan();
        void goDetailTumbuhan(TumbuhanItem tumbuhanItem);
    }
}
