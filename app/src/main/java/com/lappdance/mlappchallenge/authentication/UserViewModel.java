package com.lappdance.mlappchallenge.authentication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class UserViewModel extends AndroidViewModel {
    @NonNull
    private final UserRepository mUserRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mUserRepository = DiskBasedUserRepository.getInstance(application);
    }

    public LiveData<Boolean> getUserSession() {
        return mUserRepository.isLoggedIn();
    }

    public void login() {
        mUserRepository.setLoggedIn(true);
    }

    public void logout() {
        mUserRepository.setLoggedIn(false);
    }
}
