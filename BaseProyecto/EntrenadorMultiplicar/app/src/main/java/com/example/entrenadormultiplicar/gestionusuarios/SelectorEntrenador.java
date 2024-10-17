package com.example.entrenadormultiplicar.gestionusuarios;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.databinding.ActivitySelectorEntrenadorBinding;
import com.example.entrenadormultiplicar.gestionentrenador.AdaptadorEntrenadores;
import com.example.entrenadormultiplicar.gestionentrenador.DialogoSelectorEntrenador;
import com.example.entrenadormultiplicar.gestionentrenador.Entrenador;

import java.util.ArrayList;
import java.util.List;

public class SelectorEntrenador extends AppCompatActivity {
    ActivitySelectorEntrenadorBinding binding;
    private String nombre = "";
    private DialogoSelectorEntrenador dialogoSelectorEntrenador = new DialogoSelectorEntrenador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectorEntrenadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Quita el titulo en esta actividad
        getSupportActionBar().hide();

        nombre = String.valueOf(binding.campoNombre.getText());
        binding.botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del campo de nombre y quitar espacios en blanco
                String nombreJugador = binding.campoNombre.getText().toString().trim();
                // Validar si el campo de nombre está vacío
                if (nombreJugador.isEmpty()) {
                    Toast.makeText(SelectorEntrenador.this, "Necesitas un Nombre para Jugar", Toast.LENGTH_SHORT).show();
                } else {
                    dialogoSelectorEntrenador.show(getSupportFragmentManager(), "entrenador");
                }

            }
        });
    }


}