package com.lappdance.mlappchallenge.accounts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FragmentTransaction tr = getSupportFragmentManager().beginTransaction();

        final Fragment accountList = new AccountListFragment();
        tr.replace(android.R.id.content, accountList);
        tr.commit();
    }
}
