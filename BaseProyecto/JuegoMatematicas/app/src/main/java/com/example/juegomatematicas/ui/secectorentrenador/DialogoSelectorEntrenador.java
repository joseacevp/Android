package com.example.juegomatematicas.ui.secectorentrenador;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegomatematicas.R;

import java.util.ArrayList;
import java.util.List;

public class DialogoSelectorEntrenador extends DialogFragment {

    private RecyclerView recyclerView;
    private AdaptadorEntrenadores adaptadorEntrenadores;

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialogo_lista_entrenadores, null);
        AlertDialog.Builder construptor = new AlertDialog.Builder(getActivity());
        construptor.setView(dialogView);
        recyclerView = dialogView.findViewById(R.id.recyclerEntrenador);


        AlertDialog dialog = construptor.create();

        iniciarRecycler();
        //
        return dialog;
    }

    private void iniciarRecycler() {

        // Configurar el RecyclerView con GridLayoutManager para la rejilla
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));  // 3 columnas
        adaptadorEntrenadores = new AdaptadorEntrenadores(llenarListaEntrenadores());
        recyclerView.setAdapter(adaptadorEntrenadores);
    }

    private List<Entrenador> llenarListaEntrenadores() {
        List<Entrenador> lista = new ArrayList<>();

        lista.add(new Entrenador(R.drawable.uno));
        lista.add(new Entrenador(R.drawable.dos));
        lista.add(new Entrenador(R.drawable.tres));
        lista.add(new Entrenador(R.drawable.cuatro));
        lista.add(new Entrenador(R.drawable.cinco));
        lista.add(new Entrenador(R.drawable.seis));
        return lista;
    }

}
