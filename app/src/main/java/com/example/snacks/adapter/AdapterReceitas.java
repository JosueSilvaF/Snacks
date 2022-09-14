package com.example.snacks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snacks.R;
import com.example.snacks.model.Receitas;

import java.util.List;

public class AdapterReceitas extends RecyclerView.Adapter{
    List<Receitas> recei;

    public AdapterReceitas(List<Receitas> recei) {
        this.recei = recei;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterreceitas,parent,false);
        AdapterReceitas.ViewHolderClass vhClass = new AdapterReceitas.ViewHolderClass(view);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterReceitas.ViewHolderClass vhClass = (AdapterReceitas.ViewHolderClass) holder;
        Receitas receitas = recei.get(position);

        vhClass.tvTitulo.setText(receitas.getTitulo());
    }

    @Override
    public int getItemCount() {
        return recei.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvTitulo;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.txtTituloCardView);
        }
    }
}
