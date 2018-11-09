package com.lappdance.mlappchallenge.authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

/**
 * Classic singleton.
 * This could be replaced by a Dagger singleton; I'll do that in the next pass if I have time.
 */
public class UserSession {
    @VisibleForTesting
    static final String KEY_IS_LOGGED_IN = "c.l.m.a.usersession.isloggedin";

    /** Lock for multi-threading. */
    @NonNull
    private static final Object sLock = new Object();

    /**
     * The lazy instance.
     * Not `final` because we're going to inject fake instances during tests.
     */
    @Nullable
    private static UserSession sInstance;

    @NonNull
    private final SharedPreferences mPreferences;

    private UserSession(Context context) {
        this(PreferenceManager.getDefaultSharedPreferences(context));
    }

    @VisibleForTesting
    public UserSession(@NonNull SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @NonNull
    public static UserSession getInstance(@NonNull Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new UserSession(context);
            }
        }

        return sInstance;
    }

    @VisibleForTesting
    public static void setInstance(@Nullable UserSession instance) {
        synchronized (sLock) {
            sInstance = instance;
        }
    }

    public boolean isLoggedIn() {
        return mPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn) {
        mPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }
}
