package com.lappdance.mlappchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final Button open = findViewById(R.id.open);
        open.setOnClickListener((Button) -> {
            final Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
