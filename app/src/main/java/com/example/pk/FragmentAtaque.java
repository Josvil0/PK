package com.example.pk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAtaque#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAtaque extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button atras,ataque1,ataque2,ataque3,ataque4;
    private CombateViewModel combateViewModel;

    public FragmentAtaque() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAtaque.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAtaque newInstance(String param1, String param2) {
        FragmentAtaque fragment = new FragmentAtaque();
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
        return inflater.inflate(R.layout.fragment_ataque, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        combateViewModel = new ViewModelProvider(requireActivity()).get(CombateViewModel.class);



        atras = view.findViewById(R.id.botonAtras2);
        ataque1 = view.findViewById(R.id.ataque1);
        ataque2 = view.findViewById(R.id.ataque2);
        ataque3 = view.findViewById(R.id.ataque3);
        ataque4 = view.findViewById(R.id.ataque4);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenMain fragmentmain = new FragmenMain();

                FragmentTransaction transaction = requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.menu, fragmentmain);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        ataque1.setOnClickListener(v -> realizarAtaque(0));
        ataque2.setOnClickListener(v -> realizarAtaque(1));
        ataque3.setOnClickListener(v -> realizarAtaque(2));
        ataque4.setOnClickListener(v -> realizarAtaque(3));
    }
    private void realizarAtaque(int ataque) {
        if (combateViewModel.getJugador().getValue() != null){
            Pokemon jugador = combateViewModel.getJugador().getValue();

            List<Movimiento> movimientosJugador = jugador.getMovimientos();
            Movimiento movimiento = movimientosJugador.get(ataque);

            combateViewModel.realizarMovimiento(movimiento);
    }

    }
}