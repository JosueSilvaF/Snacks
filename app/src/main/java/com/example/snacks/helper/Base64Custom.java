package com.example.snacks.helper;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class Base64Custom {
    public static  String codificacarbase64(String texto){
        return Base64.encodeToString(texto.getBytes(),Base64.DEFAULT).replaceAll("(\n|\r)", "");
    }
    public static String decodificarbase64(String textocodificado){
        return new String(Base64.decode(textocodificado, Base64.DEFAULT));
    }
}