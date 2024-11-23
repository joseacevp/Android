package com.example.gestiondeinventario.ui.altaMaterial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.databinding.FragmentAltaMaterialBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebase;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;

public class AltaMaterialFragment extends Fragment {
    private AltaMaterialViewModel altaMaterialViewModel;
    private AccesoFirebase accesoFirebase;

    private FragmentAltaMaterialBinding binding;

    private String nombre,codigo,localizacion,uso;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AltaMaterialViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AltaMaterialViewModel.class);

        binding = FragmentAltaMaterialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accesoFirebase = new AccesoFirebaseImpl();
        nombre = binding.ediTextNombreAltaMaterial.getText().toString();
        codigo = "0000";
        localizacion = binding.ediTextLocalizacionAltaMaterial.getText().toString();
        uso = binding.ediTextUsoAltaMaterial.getText().toString();
        binding.imageViewAltaMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materiales materiales = new Materiales(nombre,codigo,localizacion,uso,R.drawable.ic_home_black_24dp,true);
                accesoFirebase.guardarDato(materiales);
                limpiarFormulario();
            }
        });

        return root;
    }

    private void limpiarFormulario() {
        binding.ediTextLocalizacionAltaMaterial.setText("");
        binding.ediTextNombreAltaMaterial.setText("");
        binding.ediTextUsoAltaMaterial.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}