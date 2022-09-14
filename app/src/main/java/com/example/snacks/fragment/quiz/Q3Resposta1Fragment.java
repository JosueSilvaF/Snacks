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
 * Use the {@link Q3Resposta1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Q3Resposta1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Q3Resposta1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Q3Resposta1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Q3Resposta1Fragment newInstance(String param1, String param2) {
        Q3Resposta1Fragment fragment = new Q3Resposta1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_q3_resposta1, container, false);

        Button IrParaQ4 = (Button)view.findViewById(R.id.btnContinuarQ1Certa);
        IrParaQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaQ4 = getParentFragmentManager().beginTransaction();
                IrParaQ4.replace(R.id.viewGuia,new QuizPergunta4Fragment());
                IrParaQ4.commit();
            }
        });

        return view;
    }
}