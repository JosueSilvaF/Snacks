package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.snacks.model.Anotacoes;

import android.widget.Button;

import com.example.snacks.R;

public class CriarAnotacaoActivity extends AppCompatActivity {

    private Button btnSalvarAnotacao, btnSairAnotacao;
    private EditText edtTituloAnotacoes, edtTextoAnotacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_anotacao);

        inicializarcomponentes();

        btnSairAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CriarAnotacaoActivity.this, AnotacoesSalvasActivity.class);
                startActivity(a);
                finish();
            }
        });
    }

    public void enviarAnot(Anotacoes anot){
        anot.salvarAnotacao();
        Toast.makeText(CriarAnotacaoActivity.this, "Anotação Salva!", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(CriarAnotacaoActivity.this, AnotacoesSalvasActivity.class);
        startActivity(a);
        finish();
    }

    public void validarAnot(View view){
        String txtTitle = edtTituloAnotacoes.getText().toString();
        String txtDescricao = edtTextoAnotacoes.getText().toString();
        if(!txtTitle.isEmpty()){
            if(!txtDescricao.isEmpty()){
                Anotacoes anot = new Anotacoes();
                anot.setTitulo(txtTitle);
                anot.setDescricao(txtDescricao);
                enviarAnot(anot);
            }else{
                Toast.makeText(CriarAnotacaoActivity.this, "Descriçao não preenchida!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CriarAnotacaoActivity.this, "Título não preenchido!", Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarcomponentes(){
        btnSalvarAnotacao = findViewById(R.id.btnSalvarAnotacao);
        btnSairAnotacao = findViewById(R.id.btnSairAnotacao);
        edtTituloAnotacoes = findViewById(R.id.edtTituloAnotacoes);
        edtTextoAnotacoes = findViewById(R.id.edtTextoAnotacoes);
    }
}