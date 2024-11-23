package com.example.gestiondeinventario.ui.altaMaterial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.databinding.FragmentAltaMaterialBinding;

public class AltaMaterialFragment extends Fragment {

    private FragmentAltaMaterialBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AltaMaterialViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AltaMaterialViewModel.class);

        binding = FragmentAltaMaterialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}