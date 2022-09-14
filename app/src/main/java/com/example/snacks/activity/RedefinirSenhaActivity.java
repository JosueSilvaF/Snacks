package com.example.snacks.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RedefinirSenhaActivity extends AppCompatActivity {

    private Button RedefinirSenha, VoltarParaLogin;
    private EditText SeuEmail;
    private FirebaseAuth autenticacao;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

         RedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenha();
            }
        });

        VoltarParaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrParaRedefinirSenha = new Intent(RedefinirSenhaActivity.this,LoginActivity.class);
                startActivity(IrParaRedefinirSenha);
                finish();
            }
        });
    }

    private void recuperarSenha(){
        String email = SeuEmail.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(getBaseContext(), "Preencha os campos obrigatórios", Toast.LENGTH_LONG).show();
        }else {
            enviarEmail(email);
        }
    }

    private void enviarEmail(String email){
        autenticacao.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getBaseContext(), "Email para redefinição de senha enviado!", Toast.LENGTH_LONG).show();

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent VoltarParaLogin = new Intent(RedefinirSenhaActivity.this, LoginActivity.class);
                        startActivity(VoltarParaLogin);
                        finish();
                    }
                }, 1000);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getBaseContext(), "Erro ao enviar o email!", Toast.LENGTH_LONG).show();

                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent VoltarParaLogin = new Intent(RedefinirSenhaActivity.this, LoginActivity.class);
                        startActivity(VoltarParaLogin);
                        finish();
                    }
                }, 1000);
            }
        });
    }

    private void inicializarComponentes(){
        SeuEmail = findViewById(R.id.etSeuEmail);
        RedefinirSenha = findViewById(R.id.btnRedefinirSenha);
        VoltarParaLogin = findViewById(R.id.btnVoltarParaLogin);
    }

}