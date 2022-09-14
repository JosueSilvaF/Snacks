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
import com.example.snacks.helper.Base64Custom;
import com.example.snacks.helper.UsuarioFirebase;
import com.example.snacks.model.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private Button Prosseguir;
    private Button VoltarParaLogin;
    private EditText etEmail, etSenha, etNome, etNomeBebe, etNascimentoBebe, etPesoBebe, etAlturaBebe;
    public String id;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        VoltarParaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrParaRedefinirSenha = new Intent(CadastroActivity.this,LoginActivity.class);
                startActivity(IrParaRedefinirSenha);
                finish();
            }
        });
    }

    public void cadastrarusuario(Usuarios usuarios){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuarios.getEmail(),usuarios.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                            UsuarioFirebase.attNomeUser(usuarios.getNome());
                            Intent a = new Intent(CadastroActivity.this, HomeActivity.class);
                            startActivity(a);
                            finish();
                            try{
                                String idUser = Base64Custom.codificacarbase64(usuarios.getEmail());
                                usuarios.setId(idUser);
                                usuarios.salvar();

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }else{
                            String excessao = "";
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                excessao = "Digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                excessao = "Por Favor, digite um e-mail válido!";
                            }catch(FirebaseAuthUserCollisionException e){
                                excessao = "Esta conta já foi criada!";
                            }catch (Exception e){
                                excessao = "Erro ao cadastrar usuario: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastroActivity.this, excessao, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void validarUsuario(View v) {
        String txtNome = etNome.getText().toString();
        String txtEmail = etEmail.getText().toString();
        String txtSenha = etSenha.getText().toString();
        String txtNomeBebe = etNomeBebe.getText().toString();
        String txtNascimentoBebe = etNascimentoBebe.getText().toString();
        String txtPesoBebe = etPesoBebe.getText().toString();
        String txtAlturaBebe = etAlturaBebe.getText().toString();

        if (!txtNome.isEmpty()) {
            if (!txtEmail.isEmpty()) {
                if (!txtSenha.isEmpty()) {
                    if (!txtNomeBebe.isEmpty()) {
                        if (!txtNascimentoBebe.isEmpty()) {
                            if (!txtPesoBebe.isEmpty()) {
                                if (!txtAlturaBebe.isEmpty()) {
                                    Usuarios usuarios = new Usuarios();
                                    usuarios.setNome(txtNome);
                                    usuarios.setEmail(txtEmail);
                                    usuarios.setSenha(txtSenha);
                                    usuarios.setNomeBebe(txtNomeBebe);
                                    usuarios.setNascimentoBebe(txtNascimentoBebe);
                                    usuarios.setPesoBebe(txtPesoBebe);
                                    usuarios.setAlturaBebe(txtAlturaBebe);
                                    cadastrarusuario(usuarios);
                                } else {
                                    Toast.makeText(CadastroActivity.this, "Altura do Bebê não preenchido!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CadastroActivity.this, "Peso do Bebê não preenchido!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CadastroActivity.this, "Nascimento do Bebê não preenchido!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Nome do Bebê não preenchido!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Senha não preenchida!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CadastroActivity.this, "E-mail não preenchido!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CadastroActivity.this, "Nome não preenchido!", Toast.LENGTH_SHORT).show();
        }
    }

    private void inicializarComponentes(){
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etNome = findViewById(R.id.etNome);
        etNomeBebe = findViewById(R.id.etNomeBebe);
        etNascimentoBebe = findViewById(R.id.etNascimentoBebe);
        etPesoBebe = findViewById(R.id.etPesoBebe);
        etAlturaBebe = findViewById(R.id.etAlturaBebe);
        Prosseguir = findViewById(R.id.btnProsseguir);
        VoltarParaLogin = findViewById(R.id.btnVoltarParaLogin);
    }
}