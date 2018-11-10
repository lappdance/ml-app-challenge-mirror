package com.lappdance.mlappchallenge.accounts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.providers.AssetsAccountRepository;

import java.util.List;

public class AccountListViewModel extends AndroidViewModel {
    private MutableLiveData<List<Account>> accounts;
    private final AssetsAccountRepository repo = new AssetsAccountRepository();

    public AccountListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Account>> getAccounts() {
        if (accounts == null) {
            accounts = new MutableLiveData<>();
            accounts.setValue(repo.loadAccounts(getApplication()));
        }

        return accounts;
    }
}
