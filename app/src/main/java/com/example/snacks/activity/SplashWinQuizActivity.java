package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.snacks.R;

public class SplashWinQuizActivity extends AppCompatActivity {
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_win_quiz);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent a = new Intent(SplashWinQuizActivity.this, HomeActivity.class);
                startActivity(a);
                finish();
            }
        }, 2000);
    }
}