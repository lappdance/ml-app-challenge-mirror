package com.lappdance.mlappchallenge.accounts.providers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AssetsAccountRepository implements AccountRepository {
    private static final String TAG = AssetsAccountRepository.class.getSimpleName();
    private static final String ALL_ACCOUNTS = "listOfAccounts.json";
    private static final SparseArray<String> ACCOUNT_MAP = new SparseArray<>(3);

    static {
        ACCOUNT_MAP.put(10, "chequingAccount.json");
        ACCOUNT_MAP.put(12, "savingsAccount.json");
        ACCOUNT_MAP.put(19, "TsaAccount.json");
    }

    @Override
    @NonNull
    public List<Account> loadAccounts(@NonNull Context context) {
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

    @NonNull
    @Override
    public List<DailyActivity> getAccountActivity(@NonNull Context context, int id) {
        try {
            final String filename = ACCOUNT_MAP.get(id);
            final InputStream in = context.getAssets().open(filename);

            final Type listOfDailyActivities = new TypeToken<List<DailyActivity>>(){}.getType();

            final Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            final InputStreamReader reader = new InputStreamReader(in);

            return gson.fromJson(reader, listOfDailyActivities);

        } catch (IOException ex) {
            Log.e(TAG, "failed to open account activity", ex);
        }
        return Collections.emptyList();
    }
}
