package com.lappdance.mlappchallenge.accounts.presenters;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;
import com.lappdance.mlappchallenge.accounts.providers.AssetsAccountRepository;

import java.util.List;

public class AccountListViewModel extends AndroidViewModel {
    private MutableLiveData<List<Account>> accounts;
    private MutableLiveData<List<DailyActivity>> accountActivity = new MutableLiveData<>();
    private MutableLiveData<Account> selectedAccount = new MutableLiveData<>();

    private final AssetsAccountRepository repo = new AssetsAccountRepository();

    public AccountListViewModel(@NonNull Application application) {
        super(application);

        selectedAccount.setValue(null);
    }

    public LiveData<List<Account>> getAccounts() {
        if (accounts == null) {
            accounts = new MutableLiveData<>();
            accounts.setValue(repo.loadAccounts(getApplication()));
        }

        return accounts;
    }

    public LiveData<List<DailyActivity>> getAccountActivity() {
        return accountActivity;
    }

    public LiveData<Account> getSelectedAccount() {
        return selectedAccount;
    }

    public void selectAccount(@NonNull Account account) {
        selectedAccount.setValue(account);
        accountActivity.setValue(repo.getAccountActivity(getApplication(), account.getId()));
    }
}
