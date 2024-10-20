package com.example.entrenadormultiplicar.gestionentrenador;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.databinding.ActivitySelectorEntrenadorBinding;
import com.example.entrenadormultiplicar.firebase.AccesoFirebase;
import com.example.entrenadormultiplicar.firebase.AccesoFirebaseImpl;
import com.example.entrenadormultiplicar.gestionusuarios.Jugador;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectorEntrenador extends AppCompatActivity {
    private ActivitySelectorEntrenadorBinding binding;

    private AccesoFirebase accesoFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectorEntrenadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();


        // Instanciar la clase que implementa la interfaz
        accesoFirebase = new AccesoFirebaseImpl();

        binding.botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreJugador = binding.campoNombre.getText().toString().trim();
                if (nombreJugador.isEmpty()) {
                    Toast.makeText(SelectorEntrenador.this, "Necesitas un Nombre para Jugar", Toast.LENGTH_SHORT).show();
                } else {
                    DialogoSelectorEntrenador dialogo = new DialogoSelectorEntrenador(); // Crear el Bundle y poner el dato
                    Bundle bundle = new Bundle();
                    bundle.putString("nombre_jugador", nombreJugador);
                    dialogo.setArguments(bundle);

                    // Mostrar el DialogFragment
                    dialogo.show(getSupportFragmentManager(), "selector_entrenador");

                }
            }
        });
    }
}
