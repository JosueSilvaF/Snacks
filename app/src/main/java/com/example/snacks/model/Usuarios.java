package com.example.snacks.model;

import com.example.snacks.config.ConfiguracaoFirebase;
import com.example.snacks.helper.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuarios {

    private String id;
    private String Email;
    private String Senha;
    private String Nome;
    private String NomeBebe;
    private String NascimentoBebe;
    private String PesoBebe;
    private String AlturaBebe;

    public Usuarios() {
    }

    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference Usuarios = firebaseRef.child("Usuarios").child(getId()).child("Conta");

        Usuarios.setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Exclude
    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNomeBebe() {
        return NomeBebe;
    }

    public void setNomeBebe(String nomeBebe) {
        NomeBebe = nomeBebe;
    }

    public String getNascimentoBebe() {
        return NascimentoBebe;
    }

    public void setNascimentoBebe(String nascimentoBebe) {
        NascimentoBebe = nascimentoBebe;
    }

    public String getPesoBebe() {
        return PesoBebe;
    }

    public void setPesoBebe(String pesoBebe) {
        PesoBebe = pesoBebe;
    }

    public String getAlturaBebe() {
        return AlturaBebe;
    }

    public void setAlturaBebe(String alturaBebe) {
        AlturaBebe = alturaBebe;
    }
}
