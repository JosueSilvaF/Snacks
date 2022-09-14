package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.snacks.R;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    Handler h = new Handler();
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(autenticacao.getCurrentUser() != null){
                    Intent a = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(a);
                }else{
                    Intent a = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(a);
                }
                finish();
            }
        }, 2500);
    }
}