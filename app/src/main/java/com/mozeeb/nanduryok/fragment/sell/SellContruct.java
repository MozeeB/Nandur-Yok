package com.mozeeb.nanduryok.fragment.sell;

public interface SellContruct {
    interface View{
        void onSuccessSell();
        void onFailedSell();
        void uploadFoto();


    }
    interface Presenter{
        void onSell();
        void uploadFoto();

    }
}
