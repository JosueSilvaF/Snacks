package com.example.snacks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.snacks.R;
import com.example.snacks.model.Receitas;

public class ReceitaSelecionadaActivity extends AppCompatActivity {

    private ImageButton imgbtnVoltarReceitaSelecionada;
    TextView txtTitulo, txtIngredientes, txtPreparo;
    private Receitas receiSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_selecionada);

        inicializarcomponentes();
        receiSelecionada = (Receitas)getIntent().getSerializableExtra("Receita  Selecionada");

        if(receiSelecionada!=null){
            txtTitulo.setText(receiSelecionada.getTitulo());
            txtIngredientes.setText(receiSelecionada.getIngredientes());
            txtPreparo.setText(receiSelecionada.getPreparo());

        }

        imgbtnVoltarReceitaSelecionada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void inicializarcomponentes(){
        txtTitulo = findViewById(R.id.txtTituloSelecionado);
        txtIngredientes = findViewById(R.id.txtIngredientesSelecionada);
        txtPreparo = findViewById(R.id.txtPreparoSelecionada);
        imgbtnVoltarReceitaSelecionada = findViewById(R.id.imgbtnVoltarReceitaSelecionada);
    }
}