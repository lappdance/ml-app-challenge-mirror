package com.lappdance.mlappchallenge.accounts.presenters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.accounts.models.Account;
import com.lappdance.mlappchallenge.accounts.views.AccountCardViewHolder;

public class AccountListAdapter extends ListAdapter<Account, AccountCardViewHolder> {

    public AccountListAdapter() {
        super(new DiffUtil.ItemCallback<Account>() {
            @Override
            public boolean areItemsTheSame(@NonNull Account account, @NonNull Account t1) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Account account, @NonNull Account t1) {
                return false;
            }
        });
    }

    @NonNull
    @Override
    public AccountCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_accountcard, viewGroup, false);
        return new AccountCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountCardViewHolder viewHolder, int i) {
        final Account account = getItem(i);
        viewHolder.setName(account.getDisplayName());
        viewHolder.setNumber(account.getNumber());
        viewHolder.setBalance(account.getBalance());
    }

    private static class ItemCallback extends DiffUtil.ItemCallback<Account> {
        @Override
        public boolean areItemsTheSame(@NonNull Account left, @NonNull Account right) {
            return left.getId() == right.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Account left, @NonNull Account right) {
            return left.equals(right);
        }
    }
}
