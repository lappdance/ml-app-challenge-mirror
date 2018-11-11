package com.lappdance.mlappchallenge.accounts.models;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class DailyActivity {
    @Keep
    @SerializedName("date")
    private Date date;

    @Keep
    @SerializedName("activity")
    private List<Transaction> activity;

    public Date getDate() {
        return date;
    }

    public List<Transaction> getActivity() {
        return activity;
    }
}
