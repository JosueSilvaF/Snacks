package com.example.snacks.activity;

import androidx.annotation.NonNull;

import android.Manifest;
import android.graphics.Color;
import android.os.Environment;
import android.util.LruCache;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.example.snacks.adapter.AdapterAnotacoes;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.CriaArquivo;
import com.example.snacks.helper.Permissao;
import com.example.snacks.helper.RecyclerItemClickListener;
import com.example.snacks.helper.UsuarioFirebase;
import com.example.snacks.model.Anotacoes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.snacks.R;

import dmax.dialog.SpotsDialog;

public class AnotacoesSalvasActivity extends AppCompatActivity {

    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ImageButton imgbtnAnotacoes, imgbtnVoltar, btnPNG, btnPDF;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private AlertDialog dialog;
    private Bitmap bitmap;
    RecyclerView recycleranotacoes;
    List<Anotacoes> anotacoes;
    AdapterAnotacoes adapteranotacoes;
    DatabaseReference anotacaoUserRef;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes_salvas);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        inicializarcomponentes();
        Permissao.validarPermissoes(permissoesNecessarias,this,1);

        dialog = new SpotsDialog.Builder().setContext(this).setMessage("Carregando Anotações").setCancelable(false).build();
        dialog.show();

        String id = UsuarioFirebase.getIdentificadorUsuario();

        recycleranotacoes.setLayoutManager(new LinearLayoutManager(this));
        anotacoes = new ArrayList<>();
        anotacaoUserRef = ConfiguracaoFirebase.getFirebase();

        anotacaoUserRef.child("Usuarios").child(id).child("Anotacoes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Anotacoes u = ds.getValue(Anotacoes.class);
                    anotacoes.add(u);
                }
                adapteranotacoes = new AdapterAnotacoes(anotacoes);
                recycleranotacoes.setAdapter(adapteranotacoes);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recycleranotacoes.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recycleranotacoes, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Anotacoes anotSelecionada = anotacoes.get(position);
                        anotSelecionada.removerAnotacao();
                        adapteranotacoes.notifyDataSetChanged();
                        Toast.makeText(AnotacoesSalvasActivity.this, "Anotação Excluida!", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));

        imgbtnAnotacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AnotacoesSalvasActivity.this, CriarAnotacaoActivity.class);
                startActivity(a);
                finish();
            }
        });

        imgbtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmapRecycler = fotoDoRecyclerView(recycleranotacoes);
                String nomeDoArquivo = "AnotacoesSnacks";
                File pasta = new File(Environment.getExternalStorageDirectory() + "/Download");

                CriaArquivo CriaPDF = new CriaArquivo(pasta, getApplicationContext());
                String criando = CriaPDF.salvarPDF(bitmapRecycler,nomeDoArquivo);

                if(criando.equals("sucesso")){
                    Toast.makeText( AnotacoesSalvasActivity.this, "PDF salvo, verifique sua pasta de downloads!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText( AnotacoesSalvasActivity.this, criando, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPNG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmapRecycler = fotoDoRecyclerView(recycleranotacoes);
                String nomeDoArquivo = "AnotacoesSnacks";
                File pasta = new File(Environment.getExternalStorageDirectory() + "/Download");

                CriaArquivo CriaPNG = new CriaArquivo(pasta, getApplicationContext());
                String criando = CriaPNG.salvarPNG(bitmapRecycler,nomeDoArquivo);

                if(criando.equals("sucesso")){
                    Toast.makeText( AnotacoesSalvasActivity.this, "PNG salvo, verifique sua pasta de downloads!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText( AnotacoesSalvasActivity.this, criando, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Bitmap fotoDoRecyclerView(RecyclerView view){
        RecyclerView.Adapter adapter = view.getAdapter();

        Bitmap bitmapPronto = null;

        if(adapter != null){
            Paint paint = new Paint();
            int tamanhoDaLista = adapter.getItemCount();
            int altura = 0;
            int alturaVolatil = 0;
            final int tamanhoMaximoDoArquivo = (int)(Runtime.getRuntime().maxMemory() / 1024);
            final int tamanhoDoCache = tamanhoMaximoDoArquivo / 8;
            LruCache<String, Bitmap> bitmapCache = new LruCache<>(tamanhoDoCache);

            for(int i = 0; i < tamanhoDaLista; i++){
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter. getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();

                Bitmap cacheDoBitmap = holder.itemView.getDrawingCache();
                if(cacheDoBitmap != null){
                    bitmapCache.put(String.valueOf(i), cacheDoBitmap);
                }

                altura += holder.itemView.getMeasuredHeight();
            }

            bitmapPronto = Bitmap.createBitmap(view.getMeasuredWidth(), altura, Bitmap.Config.ARGB_8888);

            Canvas pagina = new Canvas(bitmapPronto);
            pagina.drawColor(Color.WHITE);

            for(int i = 0; i < tamanhoDaLista; i++){
                Bitmap bitmap = bitmapCache.get(String.valueOf(i));
                pagina.drawBitmap(bitmap, 0, alturaVolatil, paint);
                alturaVolatil += bitmap.getHeight();
                bitmap.recycle();
            }
        }

        return bitmapPronto;
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
        ArrayList<Anotacoes> anot = new ArrayList<>();
        for(Anotacoes object : anotacoes){
            if(object.getTitulo().toLowerCase().contains(s.toLowerCase()) || object.getDescricao().toLowerCase().contains(s.toLowerCase())){
                anot.add(object);
            }
        }
        AdapterAnotacoes adapteranotacoes = new AdapterAnotacoes(anot);
        recycleranotacoes.setAdapter(adapteranotacoes);
    }

    private void inicializarcomponentes(){
        searchView = findViewById(R.id.searchView);
        imgbtnAnotacoes = findViewById(R.id.imgbtnAnotacoes);
        imgbtnVoltar = findViewById(R.id.imgbtnVoltar);
        btnPNG = findViewById(R.id.btnPNG);
        btnPDF = findViewById(R.id.btnPDF);
        recycleranotacoes = findViewById(R.id.recycleranotacoes);
    }
}