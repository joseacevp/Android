package com.example.tarea5.appprincipal.ui.fechas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea5.R;
import com.example.tarea5.databinding.FragmentEquipoBinding;
import com.example.tarea5.databinding.FragmentFechaBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FechaFragment extends Fragment implements DialogoFecha.OnFechaSeleccionada {
    private FechaViewModel mViewModel;
    private String fechaString;
    private FragmentFechaBinding binding;
    TextView textoFecha;
    ImageView calendarioImagen;
    public static FechaFragment newInstance() {
        return new FechaFragment();
    }
// fragmeto lanza un dialogo al hacer click en la imagen del calendario para selecionar una fecha
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFechaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        textoFecha = binding.textoFechaSeleccionada;
        //imagen del calendario con click
        calendarioImagen = binding.imagenCalendario;
        calendarioImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoFecha fecha = new DialogoFecha();
                // Establece ConfiguracionFragment como el oyente para los eventos de fecha
                fecha.setFechaSeleccionadaListener(FechaFragment.this);
                fecha.show(getFragmentManager(), "fecha");
            }
        });
       //instancia de ViewModel asigna la fecha si esta ya ha sido seleccionada anteriormente y
        // refleja la nueva fecha si se selecciona una nueva.
        mViewModel = new ViewModelProvider(requireActivity()).get(FechaViewModel.class);
        mViewModel.getSelectedDateString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    textoFecha.setText("Fecha Seleccionada: " + s);
                } else {
                    Toast.makeText(getContext(), "fecha perdida", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        // TODO: Use the ViewModel
    }

    //interface de DialogoFecha recibe la fecha seleccionada en el dialogo
    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        //resultado de la fecha seleccionada
        fechaString = fecha.get(Calendar.DAY_OF_MONTH) + " del " + fecha.get(Calendar.MONTH) + " del " + fecha.get(Calendar.YEAR);

        //instancia de viewModel para almacenar la fecha seleccionada, persistencia, accesibilidad
        //desde otros fragmentos
        mViewModel = new ViewModelProvider(requireActivity()).get(FechaViewModel.class);
        mViewModel.setSelectedDateString(fechaString);
        textoFecha.setText("Fecha Seleccionada:" + fechaString);
    }
}