package com.lappdance.mlappchallenge.accounts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lappdance.mlappchallenge.R;
import com.lappdance.mlappchallenge.Utils;

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
        this.balance.setText(Utils.formattedCurrency(balance));

        final Context context = this.balance.getContext();
        final @ColorRes int balanceColour;
        if (balance < 0) {
            balanceColour = R.color.negativeBalance;
        } else {
            final int[] attrs = {android.R.attr.textColor};
            TypedArray ta = context.obtainStyledAttributes(attrs);
            balanceColour = ta.getResourceId(0, android.R.color.black);

            ta.recycle();
        }

        this.balance.setTextColor(context.getResources().getColor(balanceColour));
    }

    public void setNumber(@NonNull String formattedNumber) {
        number.setText(formattedNumber);
    }
}
