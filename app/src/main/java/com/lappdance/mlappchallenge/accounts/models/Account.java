package com.lappdance.mlappchallenge.accounts.models;

import android.support.annotation.Keep;

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
}
