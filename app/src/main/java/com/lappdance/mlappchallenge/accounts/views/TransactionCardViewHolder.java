package com.lappdance.mlappchallenge.accounts.views;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.Utils;

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
        this.amount.setText(Utils.formattedCurrency(amount));

        final @ColorRes int colour;
        if (amount < 0) {
            colour = R.color.withdrawal;
        } else {
            colour = R.color.deposit;
        }

        this.amount.setTextColor(this.amount.getContext().getResources().getColor(colour));
    }
}
