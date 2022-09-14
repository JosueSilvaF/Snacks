package com.example.snacks.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.Base64Custom;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button EsqueceuSenha, Entrar, Cadastrar;
    private FirebaseAuth autenticacao;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes(); 
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Redefinir Senha
        EsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrParaRedefinirSenha = new Intent(LoginActivity.this,RedefinirSenhaActivity.class);
                startActivity(IrParaRedefinirSenha);
                finish();
            }
        });

        //Login com email e senha
        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String senha = etSenha .getText().toString();

                //Validar se os campos foram preenchidos
                if(!email.isEmpty()){
                    if(!senha.isEmpty()){
                        autenticacao.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent VoltarParaHome = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(VoltarParaHome);
                                            finish();
                                        }
                                    }, 1000);
                                }else{
                                    Toast.makeText(LoginActivity.this, "Erro ao fazer o login!" + task.getException(), Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha o e-mail!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Fazer o cadastro
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrParaCadastro = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(IrParaCadastro);
            }
        });
    }

    private void inicializarComponentes(){
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        EsqueceuSenha = findViewById(R.id.btnEsqueceuSenha);
        Entrar = findViewById(R.id.btnEntrar);
        Cadastrar = findViewById(R.id.btnCadastrar);
    }
}