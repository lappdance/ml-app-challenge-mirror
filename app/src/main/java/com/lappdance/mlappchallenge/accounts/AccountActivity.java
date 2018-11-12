package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.presenters.AccountListViewModel;
import com.lappdance.mlappchallenge.splash.SplashActivity;

public class AccountActivity extends AppCompatActivity {
    private AccountListViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AccountListViewModel.class);
        mViewModel.getSelectedAccount().observe(this, this::onAccountSelected);
        mViewModel.getUserSession().observe(this, (isLoggedIn) -> {
            if (Boolean.FALSE.equals(isLoggedIn)) {
                openSplashActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.quit) {
            mViewModel.logout();
        }
        return super.onOptionsItemSelected(item);
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

    private void openSplashActivity() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
