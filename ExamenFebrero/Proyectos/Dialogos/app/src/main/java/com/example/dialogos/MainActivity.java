package com.example.dialogos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dialogos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MensajeEmerg mensajeEmerg = new MensajeEmerg();
    MensajeSeleccion mensajeSeleccion = new MensajeSeleccion();
    MensajeLista mensajeLista = new MensajeLista();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonEmerg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeEmerg.show(getSupportFragmentManager(),"emergente");
            }
        });
        binding.botonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeSeleccion.show(getSupportFragmentManager(),"seleccionble");
            }
        });
        binding.botonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeLista.show(getSupportFragmentManager(),"listado");
            }
        });
    }
}