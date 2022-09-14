package com.example.snacks.fragment.receitas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.snacks.R;
import com.example.snacks.activity.AnotacoesSalvasActivity;
import com.example.snacks.activity.ReceitasSalvasActivity;
import com.example.snacks.databinding.ActivityReceitasSalvasBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceitasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceitasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceitasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceitasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceitasFragment newInstance(String param1, String param2) {
        ReceitasFragment fragment = new ReceitasFragment();
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
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);

        Button PapinhaCarneBatataAbobora = (Button)view.findViewById(R.id.btnPapinhaCarneBatataAbobora);
        PapinhaCarneBatataAbobora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaPapinhaCarneBatataAbobora = getParentFragmentManager().beginTransaction();
                IrParaPapinhaCarneBatataAbobora.replace(R.id.viewPager,new PapinhaCarneBatataAboboraFragment());
                IrParaPapinhaCarneBatataAbobora.addToBackStack(null);
                IrParaPapinhaCarneBatataAbobora.commit();
            }
        });

        Button BolinhoArrozFeijao = (Button)view.findViewById(R.id.btnBolinhoArrozFeijao);
        BolinhoArrozFeijao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaBolinhoArrozFeijao = getParentFragmentManager().beginTransaction();
                IrParaBolinhoArrozFeijao.replace(R.id.viewPager,new BolinhoArrozFeijaoFragment());
                IrParaBolinhoArrozFeijao.addToBackStack(null);
                IrParaBolinhoArrozFeijao.commit();
            }
        });

        Button CroqueteFrangoQuinoa = (Button)view.findViewById(R.id.btnCroqueteFrangoQuinoa);
        CroqueteFrangoQuinoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaCroqueteFrangoQuinoa = getParentFragmentManager().beginTransaction();
                IrParaCroqueteFrangoQuinoa.replace(R.id.viewPager,new CroqueteFrangoQuinoaFragment());
                IrParaCroqueteFrangoQuinoa.addToBackStack(null);
                IrParaCroqueteFrangoQuinoa.commit();
            }
        });


        Button OmeleteTomateCenoura = (Button)view.findViewById(R.id.btnOmeleteTomateCenoura);
        OmeleteTomateCenoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaOmeleteTomateCenoura = getParentFragmentManager().beginTransaction();
                IrParaOmeleteTomateCenoura.replace(R.id.viewPager,new OmeleteTomateCenouraFragment());
                IrParaOmeleteTomateCenoura.addToBackStack(null);
                IrParaOmeleteTomateCenoura.commit();
            }
        });

        Button BolinhoMilho = (Button)view.findViewById(R.id.btnBolinhoMilho);
        BolinhoMilho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaBolinhoMilho = getParentFragmentManager().beginTransaction();
                IrParaBolinhoMilho.replace(R.id.viewPager,new BolinhoMilhoFragment());
                IrParaBolinhoMilho.addToBackStack(null);
                IrParaBolinhoMilho.commit();
            }
        });

        Button BolinhoArrozCouveflorAbobrinha = (Button)view.findViewById(R.id.btnBolinhoArrozCouveflorAbobrinha);
        BolinhoArrozCouveflorAbobrinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaBolinhoArrozCouveflorAbobrinha = getParentFragmentManager().beginTransaction();
                IrParaBolinhoArrozCouveflorAbobrinha.replace(R.id.viewPager,new BolinhoArrozCouveflorAbobrinhaFragment());
                IrParaBolinhoArrozCouveflorAbobrinha.addToBackStack(null);
                IrParaBolinhoArrozCouveflorAbobrinha.commit();
            }
        });

        Button PanquequinhaMaca = (Button)view.findViewById(R.id.btnPanquequinhaMaca);
        PanquequinhaMaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaPanquequinhaMaca = getParentFragmentManager().beginTransaction();
                IrParaPanquequinhaMaca.replace(R.id.viewPager,new PanquequinhaMacaFragment());
                IrParaPanquequinhaMaca.addToBackStack(null);
                IrParaPanquequinhaMaca.commit();
            }
        });

        Button BolinhoBananaAmeixa = (Button)view.findViewById(R.id.btnBolinhoBananaAmeixa);
        BolinhoBananaAmeixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaBolinhoBananaAmeixa = getParentFragmentManager().beginTransaction();
                IrParaBolinhoBananaAmeixa.replace(R.id.viewPager,new BolinhoBananaAmeixaFragment());
                IrParaBolinhoBananaAmeixa.addToBackStack(null);
                IrParaBolinhoBananaAmeixa.commit();
            }
        });

        Button CrepiocaCenoura = (Button)view.findViewById(R.id.btnCrepiocaCenoura);
        CrepiocaCenoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaCrepiocaCenoura = getParentFragmentManager().beginTransaction();
                IrParaCrepiocaCenoura.replace(R.id.viewPager,new CrepiocaCenouraFragment());
                IrParaCrepiocaCenoura.addToBackStack(null);
                IrParaCrepiocaCenoura.commit();
            }
        });

        Button SorveteManga = (Button)view.findViewById(R.id.btnSorveteManga);
        SorveteManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaSorveteManga = getParentFragmentManager().beginTransaction();
                IrParaSorveteManga.replace(R.id.viewPager,new SorveteMangaFragment());
                IrParaSorveteManga.addToBackStack(null);
                IrParaSorveteManga.commit();
            }
        });

        Button MinhasReceitas = (Button)view.findViewById(R.id.btnMinhasReceitas);
        MinhasReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity act = getActivity();
                if (act != null) {
                    startActivity(new Intent(act, ReceitasSalvasActivity.class));
                }
            }
        });

        return view;
    }
}