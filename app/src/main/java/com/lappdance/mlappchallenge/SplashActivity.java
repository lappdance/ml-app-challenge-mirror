package com.lappdance.mlappchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lappdance.mlappchallenge.authentication.UserSession;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final Button open = findViewById(R.id.open);
        open.setOnClickListener((Button) -> {
            UserSession.getInstance(SplashActivity.this).setLoggedIn(true);

            openAcccountActivity();
        });

        if (UserSession.getInstance(this).isLoggedIn()) {
            openAcccountActivity();
        }
    }

    private void openAcccountActivity() {
        final Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
