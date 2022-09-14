package com.example.snacks.fragment.guia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.snacks.R;
import com.example.snacks.activity.QuizActivity;

public class GuiaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public GuiaFragment() {

    }

    public static GuiaFragment newInstance(String param1, String param2) {
        GuiaFragment fragment = new GuiaFragment();
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
        View view = inflater.inflate(R.layout.fragment_guia,container,false);

        Button QuandoComecar = (Button)view.findViewById(R.id.btnQuandoComecar);
        QuandoComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQuandoComecar = getParentFragmentManager().beginTransaction();
                IrParaQuandoComecar.replace(R.id.viewPager,new QuandoComecarFragment());
                IrParaQuandoComecar.addToBackStack(null);
                IrParaQuandoComecar.commit();
            }
        });

        Button ComoComecar = (Button)view.findViewById(R.id.btnComoComecar);
        ComoComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaComoComecar = getParentFragmentManager().beginTransaction();
                IrParaComoComecar.replace(R.id.viewPager,new ComoComecarFragment());
                IrParaComoComecar.addToBackStack(null);
                IrParaComoComecar.commit();
            }
        });

        Button RotinaAlimentar = (Button)view.findViewById(R.id.btnRotinaAlimentar);
        RotinaAlimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaRotinaAlimentar = getParentFragmentManager().beginTransaction();
                IrParaRotinaAlimentar.replace(R.id.viewPager,new RotinaAlimentarFragment());
                IrParaRotinaAlimentar.addToBackStack(null);
                IrParaRotinaAlimentar.commit();
            }
        });

        Button NaoOferecidos1Ano = (Button)view.findViewById(R.id.btnNaoOferecidos1Ano);
        NaoOferecidos1Ano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaNaoOferecidos = getParentFragmentManager().beginTransaction();
                IrParaNaoOferecidos.replace(R.id.viewPager,new NaoOferecidosFragment());
                IrParaNaoOferecidos.addToBackStack(null);
                IrParaNaoOferecidos.commit();
            }
        });

        Button ComoQuandoDarAgua = (Button)view.findViewById(R.id.btnComoQuandoDarAgua);
        ComoQuandoDarAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaComoQuandoDarAgua = getParentFragmentManager().beginTransaction();
                IrParaComoQuandoDarAgua.replace(R.id.viewPager,new ComoQuandoDarAguaFragment());
                IrParaComoQuandoDarAgua.addToBackStack(null);
                IrParaComoQuandoDarAgua.commit();
            }
        });

        Button SaudeBucal = (Button)view.findViewById(R.id.btnSaudeBucal);
        SaudeBucal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaSaudeBucal = getParentFragmentManager().beginTransaction();
                IrParaSaudeBucal.replace(R.id.viewPager,new SaudeBucalFragment());
                IrParaSaudeBucal.addToBackStack(null);
                IrParaSaudeBucal.commit();
            }
        });

        Button SeletividadeAlimentar = (Button)view.findViewById(R.id.btnSeletividadeAlimentar);
        SeletividadeAlimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaSeletividadeAlimentar = getParentFragmentManager().beginTransaction();
                IrParaSeletividadeAlimentar.replace(R.id.viewPager,new SeletividadeAlimentarFragment());
                IrParaSeletividadeAlimentar.addToBackStack(null);
                IrParaSeletividadeAlimentar.commit();
            }
        });

        Button Quiz = (Button)view.findViewById(R.id.btnQuiz);
        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, QuizActivity.class));
                }
            }
        });

        return view;
    }
}