package com.example.entrenadormultiplicar.gestionusuarios;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.databinding.ActivityInicioBinding;
import com.example.entrenadormultiplicar.gestionentrenador.AdaptadorEntrenadores;
import com.example.entrenadormultiplicar.gestionentrenador.DialogoEntrenadorViewModel;
import com.example.entrenadormultiplicar.gestionentrenador.Entrenador;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {
    private ActivityInicioBinding binding;
    InicioViewModel inicioViewModel;
    private DialogoEntrenadorViewModel dialogoEntrenadorViewModel;
    private RecyclerView recyclerView;
    private AdaptadorJugadoresInicio adaptadorJugadoresInicio;
    private List<Jugador> lista ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Quita el titulo en esta actividad
        getSupportActionBar().hide();

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
        inicioViewModel.getJugadoresInicio().observe(this, new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> jugadores) {
                // Actualizar el RecyclerView con la lista de jugadores seleccionados
                adaptadorJugadoresInicio.setListaJugadores(jugadores);
                adaptadorJugadoresInicio.notifyDataSetChanged();
            }
        });
        lista =  new ArrayList<>();
        dialogoEntrenadorViewModel.getEntrenador().observe(this, new Observer<Entrenador>() {
            @Override
            public void onChanged(Entrenador entrenador) {
                lista.add(entrenador)
            }
        });
        iniciarRecycler();

    }

    private void iniciarRecycler() {
        recyclerView = binding.recyclerInicioJugadores;
        // Configurar el RecyclerView con GridLayoutManager para la rejilla
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // 3 columnas
        adaptadorJugadoresInicio = new AdaptadorJugadoresInicio(llenarListaJugadores());
        recyclerView.setAdapter(adaptadorJugadoresInicio);

    }

    private List<Jugador> llenarListaJugadores() {

        lista.add(new Jugador("Admin", R.drawable.admin, true));
        lista.add(new Jugador("Nuevo", R.drawable.anadir, true));
        return lista;
    }

}