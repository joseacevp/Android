package com.example.tarea2sodoku;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MenuDificulad extends AppCompatActivity implements View.OnClickListener {
    //instancia de los botones de las opciones de dificultad y tecla volver
    RadioButton botonFacil, botonNormal, botonDificil;
    TextView ingresarDifi;
    int dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dificulad);

        botonFacil = findViewById(R.id.radioButtonFacil);
        botonFacil.setOnClickListener(this);
        botonNormal = findViewById(R.id.radioButtonNormal);
        botonNormal.setOnClickListener(this);
        botonDificil = findViewById(R.id.radioButtonDificil);
        botonDificil.setOnClickListener(this);
        ingresarDifi = findViewById(R.id.textViewVolver);
    }

    //guarda la dificultad seleccionada en disco PERSISTENCIA
    private void guardarDificultad(int dificultad) {
        SharedPreferences preferencias = getSharedPreferences
                ("nivelDificultad", Context.MODE_PRIVATE);
        //alamacenamos los datos de los campos
        //introducidos  en el archivo XML con un editor.
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("dificultad", dificultad);
        Log.i("info", "dificultad almacenada: " + dificultad);
        editor.commit();
    }

    //llama al metodo nueva partida indicando la dificultad según la selección
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioButtonFacil:
                dificultad = 10;
                MainActivity.nuevaPartida(dificultad);
                break;
            case R.id.radioButtonNormal:
                dificultad = 20;
                MainActivity.nuevaPartida(dificultad);
                break;
            case R.id.radioButtonDificil:
                dificultad = 30;
                MainActivity.nuevaPartida(dificultad);
                break;
            case R.id.textViewVolver:
                Log.i("info", "dificultad " + dificultad);
                guardarDificultad(dificultad);//graba la dificultad en disco
                finish();
                break;
        }

    }
}