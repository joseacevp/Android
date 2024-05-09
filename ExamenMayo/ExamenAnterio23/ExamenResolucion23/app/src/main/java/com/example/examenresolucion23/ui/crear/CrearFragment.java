package com.example.examenresolucion23.ui.crear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenresolucion23.databinding.FragmentCrearBinding;


public class CrearFragment extends Fragment {

    private FragmentCrearBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CrearViewModel galleryViewModel =
                new ViewModelProvider(this).get(CrearViewModel.class);

        binding = FragmentCrearBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}