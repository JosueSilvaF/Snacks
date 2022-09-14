package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.snacks.R;

public class SobreNosActivity extends AppCompatActivity {

    private Button btnInstaSnacks, btnInstaJosue, btnInstaRaissa, btnInstaRebeka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nos);

        inicializarComponentes();

        //Encontrar id
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);

        //Seta titulo
        toolbar.setTitle("Sobre Nós");

        //Pegar em varias versoes
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnInstaSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/projetosnacks/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(instagram);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/projetosnacks/")));
                }
            }
        });

        btnInstaJosue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/josue._tpnk/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(instagram);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/josue._tpnk/")));
                }
            }
        });

        btnInstaRaissa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/raissavlt/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(instagram);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/raissavlt/")));
                }
            }
        });

        btnInstaRebeka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/rebeka5__/");
                Intent instagram = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(instagram);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/rebeka5__/")));
                }
            }
        });
    }

    private void inicializarComponentes(){
        btnInstaSnacks = findViewById(R.id.btnInstaSnacks);
        btnInstaJosue = findViewById(R.id.btnInstaJosue);
        btnInstaRaissa = findViewById(R.id.btnInstaRaissa);
        btnInstaRebeka = findViewById(R.id.btnInstaRebeka);
    }
}