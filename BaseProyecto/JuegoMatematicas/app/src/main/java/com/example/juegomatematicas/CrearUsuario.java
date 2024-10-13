package com.example.juegomatematicas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.juegomatematicas.databinding.ActivityCrearUsuarioBinding;
import com.example.juegomatematicas.ui.JugadoresViewModel;
import com.example.juegomatematicas.ui.secectorentrenador.DialogoSelectorEntrenador;

public class CrearUsuario extends AppCompatActivity {
    private ActivityCrearUsuarioBinding binding;
    private DialogoSelectorEntrenador selectorEntrenador = new DialogoSelectorEntrenador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //para presistencia de dados NOMBRE JUGADOR
        JugadoresViewModel jugadoresViewModel =
                new ViewModelProvider(this).get(JugadoresViewModel.class);
        binding.botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del campo de nombre y quitar espacios en blanco
                String nombreJugador = binding.campoNombre.getText().toString().trim();

                // Validar si el campo de nombre está vacío
                if (nombreJugador.isEmpty()) {
                    Toast.makeText(CrearUsuario.this, "Necesitas un Nombre para Jugar", Toast.LENGTH_SHORT).show();
                } else {
                    jugadoresViewModel.setNombre(nombreJugador);

                    selectorEntrenador.show(getSupportFragmentManager(), "entrenador");

                }

            }
        });

    }
}