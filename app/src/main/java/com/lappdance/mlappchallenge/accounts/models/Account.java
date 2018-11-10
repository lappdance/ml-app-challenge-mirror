package com.lappdance.mlappchallenge.accounts.models;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Account {
    @Keep
    @SerializedName("id")
    private int id;

    @Keep
    @SerializedName("display_name")
    private String displayName;

    @Keep
    @SerializedName("account_number")
    private String number;

    @Keep
    @SerializedName("balance")
    private double balance;

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }

        final Account other = (Account)obj;
        return displayName.equals(other.displayName) &&
                number.equals(other.number) &&
                id == other.id &&
                balance == other.balance;
    }
}
