package com.example.snacks.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.snacks.R;
import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.Base64Custom;
import com.example.snacks.helper.Permissao;
import com.example.snacks.helper.UsuarioFirebase;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PerfilActivity extends AppCompatActivity {

    private String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    String ID;
    private static  final int SELECAO_CAMERA = 100;
    private static  final int SELECAO_IMAGEM = 200;
    private StorageReference storageReference;
    private ImageView imgPerfil;
    private TextView txtNome, txtEmail, txtNomeBebe, txtNascimentoBebe, txtPesoBebe, txtAlturaBebe;
    private Button btnAlterarSenha, btnFotoGaleria, btnFotoCamera, btnSair;
    private String idUser;
    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        storageReference = ConfiguracaoFirebase.getFirebaseStorage();
        idUser =  UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();

        //Validar Permissoes
        Permissao.validarPermissoes(permissoesNecessarias,this,1);

        //Encontrar id
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);

        //Seta titulo
        toolbar.setTitle("Perfil");

        //Pegar em varias versoes
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recuperar dados usuario
        FirebaseUser usuario = UsuarioFirebase.getUsuarioAtual();
        Uri url  = usuario.getPhotoUrl();
        if(url != null){
            Glide.with(PerfilActivity.this).load(url).into(imgPerfil);
        }else{
            imgPerfil.setImageResource(R.drawable.perfil_icon);
        }

        txtNome.setText(usuario.getDisplayName());
        ID = UsuarioFirebase.getIdentificadorUsuario();
        readData(ID);

        btnFotoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i,SELECAO_CAMERA);
                }

            }
        });

        btnFotoGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(i.resolveActivity(getPackageManager())!= null){
                    startActivityForResult(i,SELECAO_IMAGEM);
                }

            }
        });

        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrParaRedefinirSenha = new Intent(PerfilActivity.this,RedefinirSenhaActivity.class);
                startActivity(IrParaRedefinirSenha);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deslogarUsuario();
                Toast.makeText(PerfilActivity.this, "Deslogado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)); // Ir para a activity de login
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bitmap imagem = null;
            try{
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                    case SELECAO_IMAGEM:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagemSelecionada);
                        break;
                }
                if(imagem != null){
                    imgPerfil.setImageBitmap(imagem);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG,70,baos);
                    byte[] dadosimagem = baos.toByteArray();
                    StorageReference imagemRef = storageReference.child("imagens").child("perfil").child(idUser + "perfil.jpeg");
                    UploadTask uploadTask = imagemRef.putBytes(dadosimagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PerfilActivity.this, "Erro ao fazer upload da imagem", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(PerfilActivity.this, "Imagem uploadada", Toast.LENGTH_SHORT).show();
                            imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Uri url = task.getResult();
                                    attFoto(url);
                                }
                            });
                        }
                    });
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void attFoto(Uri url){
        UsuarioFirebase.attFotoUser(url);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int permissaoResultados: grantResults){
            if(permissaoResultados == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private  void alertaValidacaoPermissao(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle("Permissão Negada");
        builder.setMessage("Para mudar a foto é necessário aceitar as permissões");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vaipromenu();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void readData(String ID){
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        reference.child(ID).child("Conta").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String Email = String.valueOf(dataSnapshot.child("email").getValue());
                        String NomeBebe = String.valueOf(dataSnapshot.child("nomeBebe").getValue());
                        String NascimentoBebe = String.valueOf(dataSnapshot.child("nascimentoBebe").getValue());
                        String PesoBebe = String.valueOf(dataSnapshot.child("pesoBebe").getValue());
                        String AlturaBebe = String.valueOf(dataSnapshot.child("alturaBebe").getValue());
                        txtEmail.setText(Email);
                        txtNomeBebe.setText(NomeBebe);
                        txtNascimentoBebe.setText(NascimentoBebe);
                        txtPesoBebe.setText(PesoBebe + " Kg");
                        txtAlturaBebe.setText(AlturaBebe + " cm");
                    }else{
                        Toast.makeText(PerfilActivity.this, "Erro, usuário não existe!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PerfilActivity.this, "Erro, ao carregar seus dados, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void vaipromenu(){
        Intent intent = new Intent(PerfilActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    private void deslogarUsuario(){
        try{
            autenticacao.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void inicializarComponentes(){
        imgPerfil = findViewById(R.id.imgPerfil);
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtNomeBebe = findViewById(R.id.txtNomeBebe);
        txtNascimentoBebe = findViewById(R.id.txtNascimentoBebe);
        txtPesoBebe = findViewById(R.id.txtPesoBebe);
        txtAlturaBebe = findViewById(R.id.txtAlturaBebe);
        btnAlterarSenha = findViewById(R.id.btnAlterarSenha);
        btnFotoGaleria = findViewById(R.id.btnFotoGaleria);
        btnFotoCamera = findViewById(R.id.btnFotoCamera);
        btnSair = findViewById(R.id.btnSair);
    }
}