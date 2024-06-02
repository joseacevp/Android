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
import com.example.mayo24.databinding.FragmentSeleccionarJugadorBinding;

public class SeleccionarJugador extends Fragment {

    private SeleccionarJugadorViewModel mViewModel;
    private FragmentSeleccionarJugadorBinding binding;

    public static SeleccionarJugador newInstance() {
        return new SeleccionarJugador();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSeleccionarJugadorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SeleccionarJugadorViewModel.class);
        // TODO: Use the ViewModel
    }

}