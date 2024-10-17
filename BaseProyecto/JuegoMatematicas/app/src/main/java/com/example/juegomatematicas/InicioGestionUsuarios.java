package com.example.juegomatematicas;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegomatematicas.databinding.ActivityInicioGestionUsuariosBinding;
import com.example.juegomatematicas.ui.AdaptadroJugadoresInicio;
import com.example.juegomatematicas.ui.Jugador;
import com.example.juegomatematicas.ui.JugadoresViewModel;

import java.util.ArrayList;
import java.util.List;

public class InicioGestionUsuarios extends AppCompatActivity {
    private ActivityInicioGestionUsuariosBinding binding;
    private RecyclerView recyclerView;
    private boolean hayNuevoFotoEntrenador = false;
    private String nombreNuevo, fotoNuev;
    private boolean hayNuevoNombreEntrenador = false;
    //    EntrenadorViewModel entrenadorViewModel = new EntrenadorViewModel();
    JugadoresViewModel jugadoresViewModel;
    private AdaptadroJugadoresInicio adaptadorRecyclerMultipl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioGestionUsuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Evitar el giro automático
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Quita el titulo en esta actividad
        getSupportActionBar().hide();
        jugadoresViewModel =
                new ViewModelProvider(this).get(JugadoresViewModel.class);
        iniciarRecycler();

    }

    private void iniciarRecycler() {
        recyclerView = binding.recyclerInicioJugadores;
        // Configurar el RecyclerView con GridLayoutManager para la rejilla
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // 3 columnas
        adaptadorRecyclerMultipl = new AdaptadroJugadoresInicio(llenarListaJugadores());
        recyclerView.setAdapter(adaptadorRecyclerMultipl);
    }

    private List<Jugador> llenarListaJugadores() {

        List<Jugador> lista = new ArrayList<>();

        lista.add(new Jugador("Admin", R.drawable.admin, true));
        lista.add(new Jugador("Nuevo", R.drawable.anadir, true));
        jugadoresViewModel.getfoto().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    hayNuevoFotoEntrenador = true;
                    fotoNuev = jugadoresViewModel.getfoto().toString();
                } else {
                    hayNuevoFotoEntrenador = false;
                }
            }
        });
        jugadoresViewModel.getNombre().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    hayNuevoNombreEntrenador = true;
                    nombreNuevo = jugadoresViewModel.getNombre().toString();

                } else {
                    hayNuevoNombreEntrenador = false;
                }
            }
        });
        if (hayNuevoFotoEntrenador && hayNuevoNombreEntrenador) {
            lista.add(new Jugador(nombreNuevo, Integer.parseInt(fotoNuev), true));
        }


        return lista;
    }
}