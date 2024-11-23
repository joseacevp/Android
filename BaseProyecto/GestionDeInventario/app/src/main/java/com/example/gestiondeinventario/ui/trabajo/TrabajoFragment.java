package com.example.gestiondeinventario.ui.trabajo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.databinding.FragmentTrabajoBinding;

public class TrabajoFragment extends Fragment {

    private FragmentTrabajoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrabajoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(TrabajoViewModel.class);

        binding = FragmentTrabajoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}