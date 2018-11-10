package com.lappdance.mlappchallenge.splash;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lappdance.mlappchallenge.authentication.DiskBasedUserRepository;
import com.lappdance.mlappchallenge.authentication.UserRepository;

public class SplashViewModel extends AndroidViewModel {
    @NonNull
    private final UserRepository mUserRepository;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        mUserRepository = DiskBasedUserRepository.getInstance(application);
    }

    public LiveData<Boolean> getUserSession() {
        return mUserRepository.isLoggedIn();
    }

    public void login() {
        mUserRepository.setLoggedIn(true);
    }
}
