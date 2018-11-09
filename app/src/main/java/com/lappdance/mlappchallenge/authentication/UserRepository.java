package com.lappdance.mlappchallenge.authentication;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public interface UserRepository {
    @NonNull
    LiveData<Boolean> isLoggedIn();
    void login();
    void logout();
}
