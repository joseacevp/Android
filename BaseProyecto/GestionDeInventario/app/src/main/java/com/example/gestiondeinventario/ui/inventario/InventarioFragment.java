package com.example.gestiondeinventario.ui.inventario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.databinding.FragmentInventarioBinding;

public class InventarioFragment extends Fragment {

    private FragmentInventarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InventarioViewModel homeViewModel =
                new ViewModelProvider(this).get(InventarioViewModel.class);

        binding = FragmentInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}