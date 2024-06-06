package com.example.binding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.binding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /*
    Una alternativa a localizar todas las vistas de una actividad mediante el método findViewById es
    utilizar el binding
     */
    ActivityMainBinding binding;//o para activity_main.xml se genera ActivityMainBinding.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        crear un objeto ActivityMainBinding e inflar la vista raíz (es decir, el layout al que
        está asociada la clase) y ya tienes disponibles todos los objetos.
         */
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    public void enviar(View view) {
        binding.txtEdad.setText("23");
        binding.txtApellido.setText("Ramiro Portuera");
        binding.txtNombre.setText("Popelle");

    }
}