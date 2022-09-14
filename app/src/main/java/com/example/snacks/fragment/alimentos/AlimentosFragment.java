package com.example.snacks.fragment.alimentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.snacks.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlimentosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlimentosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlimentosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlimentosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlimentosFragment newInstance(String param1, String param2) {
        AlimentosFragment fragment = new AlimentosFragment();
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
        View view = inflater.inflate(R.layout.fragment_alimentos, container, false);

        Button Abacate = (Button)view.findViewById(R.id.btnAbacate);
        Abacate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaAbacate = getParentFragmentManager().beginTransaction();
                IrParaAbacate.replace(R.id.viewPager,new AbacateFragment());
                IrParaAbacate.addToBackStack(null);
                IrParaAbacate.commit();
            }
        });

        Button Abacaxi = (Button)view.findViewById(R.id.btnAbacaxi);
        Abacaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaAbacaxi = getParentFragmentManager().beginTransaction();
                IrParaAbacaxi.replace(R.id.viewPager,new AbacaxiFragment());
                IrParaAbacaxi.addToBackStack(null);
                IrParaAbacaxi.commit();
            }
        });

        Button Ameixa = (Button)view.findViewById(R.id.btnAmeixa);
        Ameixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaAmeixa = getParentFragmentManager().beginTransaction();
                IrParaAmeixa.replace(R.id.viewPager,new AmeixaFragment());
                IrParaAmeixa.addToBackStack(null);
                IrParaAmeixa.commit();
            }
        });

        Button Banana = (Button)view.findViewById(R.id.btnBanana);
        Banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaBanana = getParentFragmentManager().beginTransaction();
                IrParaBanana.replace(R.id.viewPager,new BananaFragment());
                IrParaBanana.addToBackStack(null);
                IrParaBanana.commit();
            }
        });

        Button Carambola = (Button)view.findViewById(R.id.btnCarambola);
        Carambola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaCarambola = getParentFragmentManager().beginTransaction();
                IrParaCarambola.replace(R.id.viewPager,new CarambolaFragment());
                IrParaCarambola.addToBackStack(null);
                IrParaCarambola.commit();
            }
        });

        Button Figo = (Button)view.findViewById(R.id.btnFigo);
        Figo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaFigo = getParentFragmentManager().beginTransaction();
                IrParaFigo.replace(R.id.viewPager,new FigoFragment());
                IrParaFigo.addToBackStack(null);
                IrParaFigo.commit();
            }
        });

        Button Goiaba = (Button)view.findViewById(R.id.btnGoiaba);
        Goiaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaGoiaba = getParentFragmentManager().beginTransaction();
                IrParaGoiaba.replace(R.id.viewPager,new GoiabaFragment());
                IrParaGoiaba.addToBackStack(null);
                IrParaGoiaba.commit();
            }
        });

        Button Jabuticaba = (Button)view.findViewById(R.id.btnJabuticaba);
        Jabuticaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaJabuticaba = getParentFragmentManager().beginTransaction();
                IrParaJabuticaba.replace(R.id.viewPager,new JabuticabaFragment());
                IrParaJabuticaba.addToBackStack(null);
                IrParaJabuticaba.commit();
            }
        });

        Button Kiwi = (Button)view.findViewById(R.id.btnKiwi);
        Kiwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaKiwi = getParentFragmentManager().beginTransaction();
                IrParaKiwi.replace(R.id.viewPager,new KiwiFragment());
                IrParaKiwi.addToBackStack(null);
                IrParaKiwi.commit();
            }
        });

        Button Laranja = (Button)view.findViewById(R.id.btnLaranja);
        Laranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaLaranja = getParentFragmentManager().beginTransaction();
                IrParaLaranja.replace(R.id.viewPager,new LaranjaFragment());
                IrParaLaranja.addToBackStack(null);
                IrParaLaranja.commit();
            }
        });

        Button Maca = (Button)view.findViewById(R.id.btnMaca);
        Maca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaMaca = getParentFragmentManager().beginTransaction();
                IrParaMaca.replace(R.id.viewPager,new MacaFragment());
                IrParaMaca.addToBackStack(null);
                IrParaMaca.commit();
            }
        });

        Button Mamao = (Button)view.findViewById(R.id.btnMamao);
        Mamao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaMamao = getParentFragmentManager().beginTransaction();
                IrParaMamao.replace(R.id.viewPager,new MamaoFragment());
                IrParaMamao.addToBackStack(null);
                IrParaMamao.commit();
            }
        });

        Button Manga = (Button)view.findViewById(R.id.btnManga);
        Manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaManga = getParentFragmentManager().beginTransaction();
                IrParaManga.replace(R.id.viewPager,new MangaFragment());
                IrParaManga.addToBackStack(null);
                IrParaManga.commit();
            }
        });

        Button Melancia = (Button)view.findViewById(R.id.btnMelancia);
        Melancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaMelancia = getParentFragmentManager().beginTransaction();
                IrParaMelancia.replace(R.id.viewPager,new MelanciaFragment());
                IrParaMelancia.addToBackStack(null);
                IrParaMelancia.commit();
            }
        });

        Button Melao = (Button)view.findViewById(R.id.btnMelao);
        Melao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaMelao = getParentFragmentManager().beginTransaction();
                IrParaMelao.replace(R.id.viewPager,new MelaoFragment());
                IrParaMelao.addToBackStack(null);
                IrParaMelao.commit();
            }
        });

        Button Pera = (Button)view.findViewById(R.id.btnPera);
        Pera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaPera = getParentFragmentManager().beginTransaction();
                IrParaPera.replace(R.id.viewPager,new PeraFragment());
                IrParaPera.addToBackStack(null);
                IrParaPera.commit();
            }
        });

        Button Verduras = (Button)view.findViewById(R.id.btnVerduras);
        Verduras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction IrParaVerduras = getParentFragmentManager().beginTransaction();
                IrParaVerduras.replace(R.id.viewPager,new VerdurasFragment());
                IrParaVerduras.addToBackStack(null);
                IrParaVerduras.commit();
            }
        });

        return view;
    }
}