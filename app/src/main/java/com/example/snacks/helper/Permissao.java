package com.example.snacks.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {
    public static  boolean validarPermissoes(String[] permissoes, Activity activity, int requestCode){
        if(Build.VERSION.SDK_INT >= 23){
            List<String> listapermissoes = new ArrayList<>();
            for(String permissao : permissoes){
                Boolean tempermissao = ContextCompat.checkSelfPermission
                        (activity,permissao) == PackageManager.PERMISSION_GRANTED;
                if(!tempermissao) listapermissoes.add(permissao);

            }
            if(listapermissoes.isEmpty()) return  true;
            String[] novasPermissoes = new String[listapermissoes.size()];
            listapermissoes.toArray(novasPermissoes);

            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }
        return  true;
    }
}