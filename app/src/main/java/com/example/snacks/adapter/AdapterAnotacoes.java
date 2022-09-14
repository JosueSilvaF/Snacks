package com.example.snacks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snacks.R;
import com.example.snacks.model.Anotacoes;

import java.util.List;

public class AdapterAnotacoes extends RecyclerView.Adapter{
    List<Anotacoes> anots;

    public AdapterAnotacoes(List<Anotacoes> anots) {
        this.anots = anots;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapteranotacoes,parent,false);
        ViewHolderClass vhClass = new ViewHolderClass(view);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Anotacoes anotacoes = anots.get(position);

        vhClass.tvTitulo.setText(anotacoes.getTitulo());
        vhClass.tvDescricao.setText(anotacoes.getDescricao());
    }

    @Override
    public int getItemCount() {
        return anots.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tvTitulo, tvDescricao;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.txtTitleCardView);
            tvDescricao = itemView.findViewById(R.id.txtDescCardView);
        }
    }
}
