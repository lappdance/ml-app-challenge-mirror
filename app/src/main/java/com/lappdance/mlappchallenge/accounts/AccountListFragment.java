package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.Utils;

import java.util.Objects;

public class AccountListFragment extends Fragment {
    private static final String REQUIRES_ACTIVITY = "this fragment must be attached to an Activity";

    private AccountListViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        mViewModel = ViewModelProviders.of(getActivity()).get(AccountListViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        getActivity().setTitle(R.string.accounts_myAccounts);
    }


}
