package com.example.snacks.fragment.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.snacks.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizPergunta5Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizPergunta5Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizPergunta5Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizPergunta5Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizPergunta5Fragment newInstance(String param1, String param2) {
        QuizPergunta5Fragment fragment = new QuizPergunta5Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_pergunta5, container, false);

        Button Resposta1Errada = (Button)view.findViewById(R.id.btnQ5Resposta1);
        Resposta1Errada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ5Resposta1 = getParentFragmentManager().beginTransaction();
                IrParaQ5Resposta1.replace(R.id.viewGuia,new Q5Resposta1Fragment());
                IrParaQ5Resposta1.commit();
                Toast.makeText(getActivity(),"Você errou, pratique mais!",Toast.LENGTH_SHORT).show();
            }
        });

        Button Resposta2Errada = (Button)view.findViewById(R.id.btnQ5Resposta2);
        Resposta2Errada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ5Resposta2 = getParentFragmentManager().beginTransaction();
                IrParaQ5Resposta2.replace(R.id.viewGuia,new Q5Resposta2Fragment());
                IrParaQ5Resposta2.commit();
                Toast.makeText(getActivity(),"Você errou, pratique mais!",Toast.LENGTH_SHORT).show();
            }
        });

        Button Resposta3Errada = (Button)view.findViewById(R.id.btnQ5Resposta3);
        Resposta3Errada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ5Resposta3 = getParentFragmentManager().beginTransaction();
                IrParaQ5Resposta3.replace(R.id.viewGuia,new Q5Resposta3Fragment());
                IrParaQ5Resposta3.commit();
                Toast.makeText(getActivity(),"Você errou, pratique mais!",Toast.LENGTH_SHORT).show();
            }
        });

        Button Resposta4Certa = (Button)view.findViewById(R.id.btnQ5Resposta4);
        Resposta4Certa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ5Resposta4 = getParentFragmentManager().beginTransaction();
                IrParaQ5Resposta4.replace(R.id.viewGuia,new Q5Resposta4Fragment());
                IrParaQ5Resposta4.commit();
                Toast.makeText(getActivity(),"Você acertou, parabéns!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}