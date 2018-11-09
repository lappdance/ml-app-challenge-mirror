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
    private int number;

    @Keep
    @SerializedName("balance")
    private double balance;
}
