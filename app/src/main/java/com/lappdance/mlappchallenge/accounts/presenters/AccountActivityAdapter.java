package com.lappdance.mlappchallenge.accounts.presenters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.accounts.models.DailyActivity;
import com.lappdance.mlappchallenge.accounts.models.Transaction;
import com.lappdance.mlappchallenge.accounts.views.TransactionCardViewHolder;
import com.lappdance.mlappchallenge.accounts.views.TransactionHeaderViewHolder;

public class AccountActivityAdapter extends ListAdapter<AccountActivityAdapter.HeaderOrData, RecyclerView.ViewHolder> {
    static final int TYPE_HEADER = 0;
    static final int TYPE_TRANSACTION = 1;

    public AccountActivityAdapter() {
        super(new ItemCallback());
    }

    @Override
    public int getItemViewType(int position) {
        final HeaderOrData hd = getItem(position);
        if (hd.header != null) {
            return TYPE_HEADER;
        }
        return TYPE_TRANSACTION;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_transactionheader, viewGroup, false);
            return new TransactionHeaderViewHolder(view);
        }

        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_transactioncard, viewGroup, false);
        return new TransactionCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final HeaderOrData hd = getItem(position);

        if (viewHolder instanceof TransactionCardViewHolder && hd.data != null) {
            final TransactionCardViewHolder vh = (TransactionCardViewHolder)viewHolder;

            vh.setDescription(hd.data.getDescription());
            if (hd.data.getAmountWithdrawn() > 0) {
                vh.setAmount(hd.data.getAmountWithdrawn());
            } else {
                vh.setAmount(hd.data.getAmountDeposited());
            }

        } else if (viewHolder instanceof TransactionHeaderViewHolder && hd.header != null) {
            final TransactionHeaderViewHolder vh = (TransactionHeaderViewHolder)viewHolder;
            vh.setDate(hd.header.getDate());
        }
    }

    public static class HeaderOrData {
        @Nullable
        public final DailyActivity header;

        @Nullable
        public final Transaction data;

        public HeaderOrData(@NonNull DailyActivity header) {
            this.header = header;
            this.data = null;
        }

        public HeaderOrData(@NonNull Transaction data) {
            this.header = null;
            this.data = data;
        }
    }

    private static class ItemCallback extends DiffUtil.ItemCallback<HeaderOrData> {
        @Override
        public boolean areItemsTheSame(@NonNull HeaderOrData left, @NonNull HeaderOrData right) {
            return left == right;
        }

        @Override
        public boolean areContentsTheSame(@NonNull HeaderOrData left, @NonNull HeaderOrData right) {
            if (left.header != null) {
                return left.header.equals(right.header);
            } else if (left.data != null) {
                return left.data.equals(right.data);
            }

            return false;
        }
    }
}
