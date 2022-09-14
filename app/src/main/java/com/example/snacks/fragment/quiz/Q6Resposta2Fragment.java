package com.example.snacks.fragment.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.snacks.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Q6Resposta2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Q6Resposta2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Q6Resposta2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Q6Resposta2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Q6Resposta2Fragment newInstance(String param1, String param2) {
        Q6Resposta2Fragment fragment = new Q6Resposta2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_q6_resposta2, container, false);

        Button IrParaQ6 = (Button)view.findViewById(R.id.btnContinuarQ6Certa);
        IrParaQ6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ6 = getParentFragmentManager().beginTransaction();
                IrParaQ6.replace(R.id.viewGuia,new QuizPergunta7Fragment());
                IrParaQ6.commit();
            }
        });

        return view;
    }
}