package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.presenters.AccountListViewModel;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AccountListViewModel viewModel = ViewModelProviders.of(this).get(AccountListViewModel.class);
        viewModel.getSelectedAccount().observe(this, this::onAccountSelected);
    }

    private void onAccountSelected(@Nullable Account account) {
        final FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

        final Fragment fragment;
        if (account == null) {
            fragment = new AccountListFragment();
        } else {
            fragment = new AccountActivityFragment();
            tr.addToBackStack(String.valueOf(account.getId()));
        }
        tr.replace(android.R.id.content, fragment);
        tr.commit();
    }
}
