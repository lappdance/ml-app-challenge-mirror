package com.lappdance.mlappchallenge.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lappdance.mlappchallenge.accounts.AccountActivity;
import com.lappdance.mlappchallenge.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final SplashViewModel viewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        viewModel.getUserSession().observe(this, (Boolean isLoggedIn) -> {
            if (Boolean.TRUE.equals(isLoggedIn)) {
                this.openAccountActivity();
            }
        });

        final Button open = findViewById(R.id.open);
        open.setOnClickListener((Button) -> viewModel.login());
    }

    private void openAccountActivity() {
        final Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
        finish();
    }
}
