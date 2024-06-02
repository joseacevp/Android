package com.example.mayo24.ui.fecha;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mayo24.R;

public class SeleccionarJugador extends Fragment {

    private SeleccionarJugadorViewModel mViewModel;

    public static SeleccionarJugador newInstance() {
        return new SeleccionarJugador();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seleccionar_jugador, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SeleccionarJugadorViewModel.class);
        // TODO: Use the ViewModel
    }

}