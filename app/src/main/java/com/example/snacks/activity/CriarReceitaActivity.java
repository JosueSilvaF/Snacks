package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.model.Anotacoes;
import com.example.snacks.model.Receitas;

public class CriarReceitaActivity extends AppCompatActivity {

    private Button btnSalvarReceita, btnSairReceita;
    private EditText edtTituloReceitas, edtIngredientesReceitas, edtPreparoReceitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_receita);

        inicializarcomponentes();

        btnSairReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(CriarReceitaActivity.this, ReceitasSalvasActivity.class);
                startActivity(a);
                finish();
            }
        });
    }

    public void enviarRecei(Receitas recei){
        recei.salvarReceita();
        Toast.makeText(CriarReceitaActivity.this, "Receita Salva!", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(CriarReceitaActivity.this, ReceitasSalvasActivity.class);
        startActivity(a);
        finish();
    }

    public void validarRecei(View view){
        String txtTitle = edtTituloReceitas.getText().toString();
        String txtIngredientes = edtIngredientesReceitas.getText().toString();
        String txtPreparo = edtPreparoReceitas.getText().toString();
        if(!txtTitle.isEmpty()){
            if(!txtIngredientes.isEmpty()){
                if(!txtPreparo.isEmpty()) {
                    Receitas recei = new Receitas();
                    recei.setTitulo(txtTitle);
                    recei.setIngredientes(txtIngredientes);
                    recei.setPreparo(txtPreparo);
                    enviarRecei(recei);
                }else{
                    Toast.makeText(CriarReceitaActivity.this, "Modo de Preparo não preenchido!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CriarReceitaActivity.this, "Ingredientes não preenchido!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CriarReceitaActivity.this, "Título não preenchido!", Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarcomponentes(){
        btnSalvarReceita = findViewById(R.id.btnSalvarReceita);
        btnSairReceita = findViewById(R.id.btnSairReceita);
        edtTituloReceitas = findViewById(R.id.edtTituloReceitas);
        edtIngredientesReceitas = findViewById(R.id.edtIngredientesReceitas);
        edtPreparoReceitas = findViewById(R.id.edtPreparoReceitas);
    }
}