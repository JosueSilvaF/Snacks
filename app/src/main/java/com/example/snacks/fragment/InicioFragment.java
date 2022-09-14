package com.example.snacks.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.snacks.R;
import com.example.snacks.fragment.alimentos.AlimentosFragment;
import com.example.snacks.fragment.guia.GuiaFragment;
import com.example.snacks.fragment.receitas.ReceitasFragment;

public class InicioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InicioFragment() {

    }

    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio,container,false);

        Button Guia = (Button)view.findViewById(R.id.btnGuia);
        Guia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaGuia = getParentFragmentManager().beginTransaction();
                IrParaGuia.addToBackStack(null); // essa linha é responsável por adicionar o fragment ao stack
                IrParaGuia.replace(R.id.viewPager,new GuiaFragment());
                IrParaGuia.commit();
            }
        });

        Button Alimentos = (Button)view.findViewById(R.id.btnAlimentos);
        Alimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaAlimentos = getParentFragmentManager().beginTransaction();
                IrParaAlimentos.addToBackStack(null); // essa linha é responsável por adicionar o fragment ao stack
                IrParaAlimentos.replace(R.id.viewPager,new AlimentosFragment());
                IrParaAlimentos.commit();
            }
        });

        Button Receitas = (Button)view.findViewById(R.id.btnReceitas);
        Receitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaReceitas = getParentFragmentManager().beginTransaction();
                IrParaReceitas.addToBackStack(null); // essa linha é responsável por adicionar o fragment ao stack
                IrParaReceitas.replace(R.id.viewPager,new ReceitasFragment());
                IrParaReceitas.commit();
            }
        });

        return view;
    }
}