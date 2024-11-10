package com.example.entrenadormultiplicar.ui.configurar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.entrenadormultiplicar.databinding.FragmentConfigurarBinding;

public class ConfigurarFragment extends Fragment {

    private FragmentConfigurarBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConfigurarViewModel configurarViewModel=
                new ViewModelProvider(this).get(ConfigurarViewModel.class);

        binding = FragmentConfigurarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}