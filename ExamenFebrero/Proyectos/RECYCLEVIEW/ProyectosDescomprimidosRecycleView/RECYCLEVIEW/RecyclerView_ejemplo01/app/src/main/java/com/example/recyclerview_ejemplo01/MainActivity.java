package com.example.recyclerview_ejemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRecycler = findViewById(R.id.button);
        btnRecycler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // Objeto Intent: intención de arrancar una actividad del tipo MostrarRecyclerView
        Intent intent = new Intent(this, MostrarRecyclerView.class);

        // Llamada a la actividad MostrarRecyclerView para que se cargue en pantalla
        startActivity(intent);
    }
}