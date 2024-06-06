package com.example.desplazarseentreactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button botonSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonSiguiente = findViewById(R.id.botonSiguiente);
        botonSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Crea una intencion para llamar a la actividad segunda
        //indicamos que vamos desde la actividad actual MainActivity a
        //la segunda actividad.
        Intent miIntent = new Intent(MainActivity.this,SegundaActividad.class);
        //lanzamos la intención creada para abrir la segunda actividad.
        startActivity(miIntent);

    }
}