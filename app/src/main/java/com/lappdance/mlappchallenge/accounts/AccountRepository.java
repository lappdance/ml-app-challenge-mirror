package com.lappdance.mlappchallenge.accounts;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lappdance.mlappchallenge.accounts.models.Account;

import java.util.List;

public interface AccountRepository {
    @NonNull
    List<Account> loadAccounts(Context context);
}
