package com.mozeeb.nanduryok.fragment.profile;

public class ProfilePresenter implements ProfileContruct.Presenter {

    private ProfileContruct.View view;

    public ProfilePresenter(ProfileContruct.View view) {
        this.view = view;
    }

    @Override
    public void onLogout() {
        view.onLogout();

    }
}
