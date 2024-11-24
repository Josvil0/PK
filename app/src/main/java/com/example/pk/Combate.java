package com.example.pk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Combate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Combate extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView gifLucario,gifBraviary;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CombateViewModel combateViewModel;
    private TextView textoCombate;
    private TextView saludJugador,saludOponente;

    public Combate() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment combate.
     */
    // TODO: Rename and change types and number of parameters
    public static Combate newInstance(String param1, String param2) {
        Combate fragment = new Combate();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar componentes
        gifLucario = view.findViewById(R.id.lucario);
        gifBraviary = view.findViewById(R.id.braviary);
        textoCombate = view.findViewById(R.id.texto);
        saludJugador = view.findViewById(R.id.SaludLucario);
        saludOponente = view.findViewById(R.id.SaludBraviary);

        // Cargar las imágenes de los Pokémon
        Glide.with(this).load(R.drawable.lucario_espalda).into(gifLucario);
        Glide.with(this).load(R.drawable.braviary_frente).into(gifBraviary);

        // Obtener el ViewModel
        combateViewModel = new ViewModelProvider(requireActivity()).get(CombateViewModel.class);

        combateViewModel.getTextoCombate().observe(getViewLifecycleOwner(), nuevoTexto -> {
            textoCombate.setText(nuevoTexto);
        });

        combateViewModel.getJugador().observe(getViewLifecycleOwner(), jugador -> {
            if (jugador != null) {
                saludJugador.setText(jugador.getHp()+"/"+ jugador.getHpMax());
            }
        });

        combateViewModel.getOponente().observe(getViewLifecycleOwner(), oponente -> {
            if (oponente != null) {
                saludOponente.setText(oponente.getHp()+"/"+oponente.getHpMax());
            }
        });


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combate, container, false);
        return view;


    }

    public void actualizarTexto(String nuevoTexto) {
        if (textoCombate != null) {
            textoCombate.setText(nuevoTexto);
        }
    }


}