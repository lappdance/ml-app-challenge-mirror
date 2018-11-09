package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lappdance.mlappchallenge.accounts.models.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AssetsAccountRepository implements AccountRepository {
    private static final String TAG = AssetsAccountRepository.class.getSimpleName();
    private static final String ALL_ACCOUNTS = "listOfAccounts.json";

    @Override
    @NonNull
    public List<Account> loadAccounts(Context context) {
        try {
            final InputStream in = context.getAssets().open(ALL_ACCOUNTS);

            final Type listOfAccounts = new TypeToken<List<Account>>(){}.getType();

            final Gson gson = new Gson();
            final InputStreamReader reader = new InputStreamReader(in);

            return gson.fromJson(reader, listOfAccounts);

        } catch (IOException ex) {
            Log.e(TAG, "failed to open list of accounts", ex);
        }
        return Collections.emptyList();
    }
}
