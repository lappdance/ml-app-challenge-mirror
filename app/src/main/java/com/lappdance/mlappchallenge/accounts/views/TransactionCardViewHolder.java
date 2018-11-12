package com.lappdance.mlappchallenge.accounts.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lappdance.mlappchallenge.R;

import java.util.Locale;

public class TransactionCardViewHolder extends RecyclerView.ViewHolder {
    private final TextView amount;
    private final TextView description;

    public TransactionCardViewHolder(@NonNull View itemView) {
        super(itemView);

        amount = itemView.findViewById(R.id.amount);
        description = itemView.findViewById(R.id.description);
    }

    public void setDescription(@NonNull String formattedDescription) {
        description.setText(formattedDescription);
    }

    public void setAmount(double amount) {
        this.amount.setText(String.format(Locale.getDefault(), "$%.2f", amount));
    }
}
