package com.example.snacks.model;

import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Receitas implements Serializable {

    private String ID;
    private String Titulo;
    private String Ingredientes;
    private String Preparo;

    public Receitas() {

    }

    public void salvarReceita(){
        ID = UsuarioFirebase.getIdentificadorUsuario();

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference Usuarios = firebaseRef.child("Usuarios").child(ID).child("Receitas").child(getTitulo());

        Usuarios.setValue(this);
    }

    public void removerReceita(){
        ID = UsuarioFirebase.getIdentificadorUsuario();

        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference Usuarios = firebaseRef.child("Usuarios").child(ID).child("Receitas").child(getTitulo());

        Usuarios.removeValue();
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        Ingredientes = ingredientes;
    }

    public String getPreparo() {
        return Preparo;
    }

    public void setPreparo(String preparo) {
        Preparo = preparo;
    }
}
