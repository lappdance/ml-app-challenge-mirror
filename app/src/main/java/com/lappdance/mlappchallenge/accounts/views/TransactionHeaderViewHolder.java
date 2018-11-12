package com.lappdance.mlappchallenge.accounts.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lappdance.mlappchallenge.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionHeaderViewHolder extends RecyclerView.ViewHolder {
    private final TextView date;

    public TransactionHeaderViewHolder(@NonNull View itemView) {
        super(itemView);

        date = itemView.findViewById(R.id.date);
    }

    public void setDate(@NonNull Date date) {
        final DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        this.date.setText(formatter.format(date));
    }
}
