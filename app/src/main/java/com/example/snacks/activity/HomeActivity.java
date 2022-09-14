package com.example.snacks.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.fragment.anotacoes.AnotacoesFragment;
import com.example.snacks.fragment.InicioFragment;
import com.example.snacks.fragment.receitas.ReceitasFragment;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.UsuarioFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    DatabaseReference Dataref;
    StorageReference Storageref;
    String txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        FirebaseUser usuario = UsuarioFirebase.getUsuarioAtual();

        //Configuração Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        if (autenticacao.getCurrentUser() != null) {
            txtNome = (usuario.getDisplayName());
            toolbar.setTitle("Olá, "+ txtNome +"!");
        } else {
            toolbar.setTitle("Olá!");
        }
        setSupportActionBar(toolbar);

        //Configuração Bottom Navigation View
        configuraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        if (autenticacao.getCurrentUser() == null) {
            menu.setGroupVisible(R.id.Logado,false);
            menu.setGroupVisible(R.id.Deslogado, true);
        } else {
            menu.setGroupVisible(R.id.Logado, true);
            menu.setGroupVisible(R.id.Deslogado, false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_perfil:
                Intent intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);// Ir para a activity de login
                break;

            case R.id.menu_aboutus:
                Intent a = new Intent(this, SobreNosActivity.class);
                startActivity(a);
                break;

            case R.id.menu_sair:
                deslogarUsuario();
                Toast.makeText(HomeActivity.this, "Deslogado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)); // Ir para a activity de login
                finish();

            case R.id.menu_login:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)); // Ir para a activity de login
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Deslogar Usuario Firebase no App
    private void deslogarUsuario(){
        try{
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Metodo Responsável por criar a Bottom Navigation
    private void configuraBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottom_navigation);

        /* //Configurações iniciais do Bottom Navigation
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(true); */

        //Habilitar navegação
        habilitarNavegacao(bottomNavigationViewEx);

        //Configura item selecionado inicialmente
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

    }

    //Metodo Responsável por habilitar a navegação
    private void habilitarNavegacao(BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(item -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch(item.getItemId()){
                case R.id.ic_inicio:
                    fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit(); // muda o fragment do frame layout
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;

                case R.id.ic_anotacoes:
                    fragmentTransaction.replace(R.id.viewPager, new AnotacoesFragment()).addToBackStack(null).commit(); // muda o fragment do frame layout
                    return true;

                case R.id.ic_receitas:
                    fragmentTransaction.replace(R.id.viewPager, new ReceitasFragment()).addToBackStack(null).commit(); // muda o fragment do frame layout
                    return true;
            }
            return false;
        });
    }
}