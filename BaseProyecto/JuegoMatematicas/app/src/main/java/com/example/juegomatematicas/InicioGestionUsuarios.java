package com.example.juegomatematicas;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegomatematicas.databinding.ActivityInicioGestionUsuariosBinding;
import com.example.juegomatematicas.ui.AdaptadroJugadoresInicio;
import com.example.juegomatematicas.ui.Jugadores;

import java.util.ArrayList;
import java.util.List;

public class InicioGestionUsuarios extends AppCompatActivity {
    private ActivityInicioGestionUsuariosBinding binding;
    private RecyclerView recyclerView;

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

        iniciarRecycler();

    }

    private void iniciarRecycler() {
        recyclerView = binding.recyclerInicioJugadores;
        // Configurar el RecyclerView con GridLayoutManager para la rejilla
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // 3 columnas
        adaptadorRecyclerMultipl = new AdaptadroJugadoresInicio(llenarListaJugadores());
        recyclerView.setAdapter(adaptadorRecyclerMultipl);
    }

    private List<Jugadores> llenarListaJugadores() {
        List<Jugadores> lista = new ArrayList<>();

        lista.add(new Jugadores("Admin", R.drawable.adimin, true));
        lista.add(new Jugadores("Nuevo", R.drawable.anadir, true));

        return lista;
    }
}