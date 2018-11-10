package com.lappdance.mlappchallenge.accounts.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lappdance.mlappchallenge.R;

import java.util.Locale;

public class AccountCardViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView balance;
    private final TextView number;

    public AccountCardViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        balance = itemView.findViewById(R.id.balance);
        number = itemView.findViewById(R.id.number);
    }

    public void setName(@NonNull String formattedName) {
        name.setText(formattedName);
    }

    public void setBalance(double balance) {
        this.balance.setText(String.format(Locale.getDefault(), "$%.2f", balance));
    }

    public void setNumber(@NonNull String formattedNumber) {
        number.setText(formattedNumber);
    }
}
