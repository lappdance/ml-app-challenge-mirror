package com.lappdance.mlappchallenge.authentication;

import android.arch.lifecycle.MutableLiveData;
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
public class DiskBasedUserRepository implements UserRepository {
    private static final String KEY_IS_LOGGED_IN = "c.l.m.a.usersession.isloggedin";

    /** Lock for multi-threading. */
    @NonNull
    private static final Object sLock = new Object();

    /**
     * The lazy instance.
     * Not `final` because we're going to inject fake instances during tests.
     */
    @Nullable
    private static DiskBasedUserRepository sInstance;

    @NonNull
    private final SharedPreferences mPreferences;

    @Nullable
    private MutableLiveData<Boolean> mSessionData;

    private DiskBasedUserRepository(Context context) {
        this(PreferenceManager.getDefaultSharedPreferences(context));
    }

    private DiskBasedUserRepository(@NonNull SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @NonNull
    public static DiskBasedUserRepository getInstance(@NonNull Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new DiskBasedUserRepository(context);
            }
        }

        return sInstance;
    }

    @VisibleForTesting
    public static void setInstance(@Nullable DiskBasedUserRepository instance) {
        synchronized (sLock) {
            sInstance = instance;
        }
    }

    @NonNull
    @Override
    public MutableLiveData<Boolean> isLoggedIn() {
        if (mSessionData == null) {
            mSessionData = new MutableLiveData<>();

            final boolean isLoggedIn = mPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
            mSessionData.setValue(isLoggedIn);
        }

        return mSessionData;
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        mPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
        isLoggedIn().setValue(isLoggedIn);
    }

    @VisibleForTesting
    public void setLoggedIn(MutableLiveData<Boolean> sessionData) {
        mSessionData = sessionData;
    }
}
