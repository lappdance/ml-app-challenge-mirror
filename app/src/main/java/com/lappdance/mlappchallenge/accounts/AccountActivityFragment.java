package com.lappdance.mlappchallenge.accounts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.Utils;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;
import com.lappdance.mlappchallenge.accounts.models.Transaction;
import com.lappdance.mlappchallenge.accounts.presenters.AccountActivityAdapter;
import com.lappdance.mlappchallenge.accounts.presenters.AccountListViewModel;

import java.util.ArrayList;
import java.util.List;

public class AccountActivityFragment extends Fragment {
    private static final String REQUIRES_ACTIVITY = "this fragment must be attached to an Activity";

    private AccountListViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        mViewModel = ViewModelProviders.of(getActivity()).get(AccountListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accountactivity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.requireNonNull(getActivity(), REQUIRES_ACTIVITY);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        final AccountActivityAdapter adapter = new AccountActivityAdapter();
        recyclerView.setAdapter(adapter);

        mViewModel.getAccountActivity().observe(this, (activities) ->
                adapter.submitList(flattenTransactions(activities))
        );

        mViewModel.getSelectedAccount().observe(this, (account) -> {
            if (account == null) {
                getActivity().setTitle("");
            } else {
                getActivity().setTitle(account.getDisplayName());
            }
        });
    }

    @VisibleForTesting
    @NonNull
    List<AccountActivityAdapter.HeaderOrData> flattenTransactions(@Nullable List<DailyActivity> activity) {
        List<AccountActivityAdapter.HeaderOrData> flattenedWithHeaders = new ArrayList<>();
        if (activity == null) {
            return flattenedWithHeaders;
        }


        for (DailyActivity dailyActivity : activity) {
            flattenedWithHeaders.add(new AccountActivityAdapter.HeaderOrData(dailyActivity));
            for (Transaction tr : dailyActivity.getActivity()) {
                flattenedWithHeaders.add(new AccountActivityAdapter.HeaderOrData(tr));
            }
        }

        return flattenedWithHeaders;
    }
}
