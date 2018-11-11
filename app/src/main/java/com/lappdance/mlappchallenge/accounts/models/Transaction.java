package com.lappdance.mlappchallenge.accounts.models;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Transaction {
    @Keep
    @SerializedName("id")
    private long id;

    @Keep
    @SerializedName("date")
    private Date date;

    @Keep
    @SerializedName("description")
    private String description;

    @Keep
    @SerializedName("withdrawal_amount")
    private double amountWithdrawn;

    @Keep
    @SerializedName("deposit_amount")
    private double amountDeposited;

    @Keep
    @SerializedName("balance")
    private double balance;

    @Keep
    @SerializedName("transaction_uid")
    private long uid;

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public double getAmountDeposited() {
        return amountDeposited;
    }

    public double getBalance() {
        return balance;
    }

    public long getUid() {
        return uid;
    }
}
