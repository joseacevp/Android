package com.example.juegomatematicas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegomatematicas.databinding.ActivityCrearUsuarioBinding;
import com.example.juegomatematicas.ui.secectorentrenador.DialogoSelectorEntrenador;

public class CrearUsuario extends AppCompatActivity {
    private ActivityCrearUsuarioBinding binding;
   private DialogoSelectorEntrenador selectorEntrenador = new DialogoSelectorEntrenador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del campo de nombre y quitar espacios en blanco
                String nombreJugador = binding.campoNombre.getText().toString().trim();

                // Validar si el campo de nombre está vacío
                if (nombreJugador.isEmpty()) {
                    Toast.makeText(CrearUsuario.this, "Necesitas un Nombre para Jugar", Toast.LENGTH_SHORT).show();
                } else {
                    // Si el nombre es válido, iniciar la actividad
//                    Intent volver = new Intent(getApplicationContext(), InicioGestionUsuarios.class);
//                    startActivity(volver);
//
//                    // Finalizar la actividad actual
//                    finish(); // Cierra la actividad actual después de lanzar la nueva
                    selectorEntrenador.show(getSupportFragmentManager(),"entrenador");
//                    finish();
                }

            }
        });

    }
}