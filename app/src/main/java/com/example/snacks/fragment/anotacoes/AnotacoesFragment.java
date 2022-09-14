package com.example.snacks.fragment.anotacoes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.snacks.R;
import com.example.snacks.activity.AnotacoesSalvasActivity;
import com.example.snacks.activity.HomeActivity;
import com.example.snacks.fragment.guia.GuiaFragment;
import com.example.snacks.fragment.quiz.Q2Resposta1Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnotacoesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnotacoesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnotacoesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnotacoesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnotacoesFragment newInstance(String param1, String param2) {
        AnotacoesFragment fragment = new AnotacoesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    CheckBox checkAbacate, checkAbacaxi, checkAmeixa, checkBanana, checkCarambola, checkFigo, checkJabuticaba, checkKiwi, checkLaranja, checkMaca;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    private class inicializarComponentes extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_anotacoes, container, false);

            checkAbacate = (CheckBox)view.findViewById(R.id.checkAbacate);
            checkAbacaxi = (CheckBox)view.findViewById(R.id.checkAbacaxi);
            checkAmeixa = (CheckBox)view.findViewById(R.id.checkAmeixa);
            checkBanana = (CheckBox)view.findViewById(R.id.checkBanana);
            checkCarambola = (CheckBox)view.findViewById(R.id.checkCarambola);
            checkFigo = (CheckBox)view.findViewById(R.id.checkFigo);
            checkJabuticaba = (CheckBox)view.findViewById(R.id.checkJabuticaba);
            checkKiwi = (CheckBox)view.findViewById(R.id.checkKiwi);
            checkLaranja = (CheckBox)view.findViewById(R.id.checkLaranja);
            checkAbacate = (CheckBox)view.findViewById(R.id.checkAbacate);

            return view;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anotacoes, container, false);

        ImageButton CriarAnotacao = (ImageButton)view.findViewById(R.id.imgbtnAnotacoes);
        CriarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, AnotacoesSalvasActivity.class));
                }
            }
        });

        return view;
    }
}