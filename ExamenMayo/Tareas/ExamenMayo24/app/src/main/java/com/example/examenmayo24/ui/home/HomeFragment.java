package com.example.examenmayo24.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenmayo24.DialogoFecha;
import com.example.examenmayo24.databinding.FragmentHomeBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment implements DialogoFecha.OnFechaSeleccionada {

    private FragmentHomeBinding binding;
    private String fechaString;
    ImageView calendarioImagen;
    TextView textoFecha;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        calendarioImagen = binding.imagenCalendario;
        textoFecha = binding.textoFechaSeleccionada;
        calendarioImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoFecha fecha = new DialogoFecha();
                // Establece ConfiguracionFragment como el oyente para los eventos de fecha
                fecha.setFechaSeleccionadaListener(HomeFragment.this);
                fecha.show(getFragmentManager(), "fecha");
            }
        });
        //instancia de ViewModel asigna la fecha si esta ya ha sido seleccionada anteriormente y
        // refleja la nueva fecha si se selecciona una nueva.
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getSelectedDateString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    textoFecha.setText("Fecha Seleccionada: " + s);
                } else {
                    Toast.makeText(getContext(), "fecha perdida", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        //resultado de la fecha seleccionada
        fechaString = fecha.get(Calendar.DAY_OF_MONTH) + " del " + fecha.get(Calendar.MONTH) + " del " + fecha.get(Calendar.YEAR);

        //instancia de viewModel para almacenar la fecha seleccionada, persistencia, accesibilidad
        //desde otros fragmentos
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.setSelectedDateString(fechaString);
        textoFecha.setText("Fecha Seleccionada:" + fechaString);
    }
//    //interface de DialogoFecha recibe la fecha seleccionada en el dialogo
//    @Override
//    public void onResultadoFecha(GregorianCalendar fecha) {

//    }
}