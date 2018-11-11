package com.lappdance.mlappchallenge.accounts.providers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;

import java.util.List;

public interface AccountRepository {
    @NonNull
    List<Account> loadAccounts(@NonNull Context context);

    @NonNull
    List<DailyActivity> getAccountActivity(@NonNull Context context, @NonNull Account account);
}
