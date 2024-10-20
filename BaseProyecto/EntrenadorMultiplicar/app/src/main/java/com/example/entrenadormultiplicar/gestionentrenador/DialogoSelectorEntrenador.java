package com.example.entrenadormultiplicar.gestionentrenador;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.gestionusuarios.Jugador;

import java.util.ArrayList;
import java.util.List;

public class DialogoSelectorEntrenador extends DialogFragment {

    private RecyclerView recyclerView;
    private AdaptadorEntrenadores adaptadorEntrenadores;
    private DialogoEntrenadorViewModel dialogoEntrenadorViewModel;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialogo_lista_entrenadores, null);
        AlertDialog.Builder construptor = new AlertDialog.Builder(getActivity());
        construptor.setView(dialogView);
        recyclerView = dialogView.findViewById(R.id.recyclerEntrenador);


        AlertDialog dialog = construptor.create();

        iniciarRecycler();
        dialogoEntrenadorViewModel = new ViewModelProvider(requireActivity()).get(DialogoEntrenadorViewModel.class);
        dialogoEntrenadorViewModel.setEntrenador(adaptadorEntrenadores.getEntrenadorSeleccionado());
//        System.out.println(dialogoEntrenadorViewModel.getEntrenador().getValue().getFoto());
        //
        return dialog;

    }

    private void iniciarRecycler() {

        // Configurar el RecyclerView con GridLayoutManager para la rejilla
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));  // 3 columnas
        adaptadorEntrenadores = new AdaptadorEntrenadores(llenarListaEntrenadores());
        recyclerView.setAdapter(adaptadorEntrenadores);
    }

    private List<Jugador>  llenarListaEntrenadores() {
        List<Jugador> lista = new ArrayList<>();

        lista.add(new Jugador("",R.drawable.uno, true));
        lista.add(new Jugador("",R.drawable.dos, true));
        lista.add(new Jugador("",R.drawable.tres, true));
        lista.add(new Jugador("",R.drawable.cuatro, true));
        lista.add(new Jugador("",R.drawable.cinco, true));
        lista.add(new Jugador("",R.drawable.seis, true));

        return lista;
    }

}
