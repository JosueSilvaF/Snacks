package com.example.snacks.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.adapter.AdapterReceitas;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.RecyclerItemClickListener;
import com.example.snacks.helper.UsuarioFirebase;
import com.example.snacks.model.Receitas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ReceitasSalvasActivity extends AppCompatActivity {

    private ImageButton imgbtnReceitas, imgbtnVoltarReceitas;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private AlertDialog dialog;
    private Bitmap bitmap;
    RecyclerView recyclerreceitas;
    List<Receitas> receitas;
    AdapterReceitas adapterreceitas;
    DatabaseReference receitaUserRef;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas_salvas);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        inicializarcomponentes();

        dialog = new SpotsDialog.Builder().setContext(this).setMessage("Carregando Receitas").setCancelable(false).build();
        dialog.show();

        String id = UsuarioFirebase.getIdentificadorUsuario();

        recyclerreceitas.setLayoutManager(new LinearLayoutManager(this));
        receitas = new ArrayList<>();
        receitaUserRef = ConfiguracaoFirebase.getFirebase();
        receitaUserRef.child("Usuarios").child(id).child("Receitas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Receitas u = ds.getValue(Receitas.class);
                    receitas.add(u);
                }
                adapterreceitas = new AdapterReceitas(receitas);
                recyclerreceitas.setAdapter(adapterreceitas);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerreceitas.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerreceitas, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Receitas receiSelecionada = receitas.get(position);
                        Intent i = new Intent(ReceitasSalvasActivity.this,ReceitaSelecionadaActivity.class);
                        i.putExtra("Receita  Selecionada", receiSelecionada);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Receitas receiSelecionada = receitas.get(position);
                        receiSelecionada.removerReceita();
                        adapterreceitas.notifyDataSetChanged();
                        Toast.makeText( ReceitasSalvasActivity.this, "Receita Excluida!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));

        imgbtnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ReceitasSalvasActivity.this, CriarReceitaActivity.class);
                startActivity(a);
                finish();
            }
        });

        imgbtnVoltarReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String s){
        ArrayList<Receitas> recei = new ArrayList<>();
        for(Receitas object : receitas){
            if(object.getTitulo().toLowerCase().contains(s.toLowerCase())){
                recei.add(object);
            }
        }
        AdapterReceitas adapterreceitas = new AdapterReceitas(recei);
        recyclerreceitas.setAdapter(adapterreceitas);
    }

    private void inicializarcomponentes(){
        searchView = findViewById(R.id.searchView);
        imgbtnReceitas = findViewById(R.id.imgbtnReceitas);
        imgbtnVoltarReceitas = findViewById(R.id.imgbtnVoltarReceitas);
        recyclerreceitas = findViewById(R.id.recyclerreceitas);
    }
}