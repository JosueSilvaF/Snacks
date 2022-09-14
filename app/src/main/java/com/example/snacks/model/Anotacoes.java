package com.example.snacks.model;

import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;

public class Anotacoes {

    private String ID;
    private String Titulo;
    private String Descricao;

    public Anotacoes() {

    }

    public void salvarAnotacao(){
        ID = UsuarioFirebase.getIdentificadorUsuario();

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference Usuarios = firebaseRef.child("Usuarios").child(ID).child("Anotacoes").child(getTitulo());

        Usuarios.setValue(this);
    }

    public void removerAnotacao(){
        ID = UsuarioFirebase.getIdentificadorUsuario();

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference Usuarios = firebaseRef.child("Usuarios").child(ID).child("Anotacoes").child(getTitulo());

        Usuarios.removeValue();
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
