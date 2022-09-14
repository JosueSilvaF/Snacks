package com.example.snacks.helper;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.snacks.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UsuarioFirebase {
    public static  String getIdentificadorUsuario(){
        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String email = usuario.getCurrentUser().getEmail();
        String iduser = Base64Custom.codificacarbase64(email);

        return iduser;
    }

    public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return usuario.getCurrentUser();
    }

    public static  boolean attNomeUser(String nome){
        try{
            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nome)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Log.d("Perfil","erro ao atualizar nome de perfil");
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    public static  boolean attFotoUser(Uri url){
        try{
            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setPhotoUri(url).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Log.d("Perfil","erro ao atualizar foto de perfil");
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }
}