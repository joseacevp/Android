package com.example.examenresolucion23.ui.mostrar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenresolucion23.databinding.FragmentMostrarBinding;

public class MostrarFragment extends Fragment {

    private FragmentMostrarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MostrarViewModel slideshowViewModel =
                new ViewModelProvider(this).get(MostrarViewModel.class);

        binding = FragmentMostrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}