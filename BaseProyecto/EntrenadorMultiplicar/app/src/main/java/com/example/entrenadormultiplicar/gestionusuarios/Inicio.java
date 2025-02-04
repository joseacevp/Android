package com.example.entrenadormultiplicar.gestionusuarios;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.databinding.ActivityInicioBinding;
import com.example.entrenadormultiplicar.firebase.AccesoFirebase;
import com.example.entrenadormultiplicar.firebase.AccesoFirebaseImpl;
import com.example.entrenadormultiplicar.gestionentrenador.DialogoEntrenadorViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {
    private ActivityInicioBinding binding;
    private AccesoFirebase accesoFirebase;
    private RecyclerView recyclerView;
    
    private AdaptadorJugadoresInicio adaptadorJugadoresInicio;
    private List<Jugador> listaRecu = new ArrayList<>();
    private InicioViewModel inicioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        // Instanciar la clase que implementa la interfaz
        accesoFirebase = new AccesoFirebaseImpl();

        // Cargar datos desde Firebase y actualizar la lista
        accesoFirebase.cargarDatos(new AccesoFirebase.OnDataLoadedCallback() {
            @Override
            public void onDataLoaded(List<Jugador> jugadores) {
                if (jugadores.isEmpty()){
                    llenarListaJugadores();
                }
                listaRecu.clear();
                listaRecu.addAll(jugadores);
                adaptadorJugadoresInicio.notifyDataSetChanged();

            }

            @Override
            public void onError(String error) {
                Toast.makeText(Inicio.this, "Fallo al cargar datos de Firebase: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        iniciarRecycler();
    }

    private void iniciarRecycler() {
        recyclerView = binding.recyclerInicioJugadores;
        recyclerView.setLayoutManager(new GridLayoutManager(Inicio.this, 2));
        adaptadorJugadoresInicio = new AdaptadorJugadoresInicio(listaRecu);
        recyclerView.setAdapter(adaptadorJugadoresInicio);
    }

    private void llenarListaJugadores() {
        accesoFirebase.guardarDato(new Jugador("Admin", R.drawable.admin, true));
        accesoFirebase.guardarDato(new Jugador("Nuevo", R.drawable.anadir, true));
    }
}
