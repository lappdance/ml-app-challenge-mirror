package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.Utils;
import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.presenters.AccountListAdapter;
import com.lappdance.mlappchallenge.accounts.presenters.AccountListViewModel;

import java.util.List;

public class AccountListFragment extends Fragment {
    private static final String REQUIRES_ACTIVITY = "this fragment must be attached to an Activity";

    private AccountListViewModel mViewModel;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        mViewModel = ViewModelProviders.of(getActivity()).get(AccountListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accountlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        getActivity().setTitle(R.string.accounts_myAccounts);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        AccountListAdapter adapter = new AccountListAdapter();
        mRecyclerView.setAdapter(adapter);

        mViewModel.getAccounts().observe(this, adapter::submitList);
    }


}
