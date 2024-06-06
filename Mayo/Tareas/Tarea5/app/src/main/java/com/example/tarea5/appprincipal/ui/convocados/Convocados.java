package com.example.tarea5.appprincipal.ui.convocados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.equipo.AdaptadorRecyclerMultipl;
import com.example.tarea5.appprincipal.ui.equipo.EquipoFragment;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;
import com.example.tarea5.databinding.FragmentConvocadosBinding;

import java.util.ArrayList;

public class Convocados extends Fragment {

    private FragmentConvocadosBinding binding;
    private ConvocadosViewModel convocadosViewModel;
    private ArrayList<Jugador> jugadores;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentConvocadosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.reciclerConvocados;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        convocadosViewModel = new ViewModelProvider(requireActivity()).get(ConvocadosViewModel.class);
        convocadosViewModel.getListaConvocados().observe(getViewLifecycleOwner(), new Observer<ArrayList<Jugador>>() {
            @Override
            public void onChanged(ArrayList<Jugador> jugadores) {
                if (jugadores != null && jugadores.size() > 0) {
                    AdaptadorConvocados adaptadorConvocados = new AdaptadorConvocados(jugadores);
                    recyclerView.setAdapter(adaptadorConvocados);
                } else {
                    Toast.makeText(getContext(), "¡No Hay Convocados Aun!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}