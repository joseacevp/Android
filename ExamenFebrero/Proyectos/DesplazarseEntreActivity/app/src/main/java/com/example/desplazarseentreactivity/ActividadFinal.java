package com.example.desplazarseentreactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActividadFinal extends AppCompatActivity implements View.OnClickListener {

    Button botonAnterior2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_final);
        botonAnterior2 = findViewById(R.id.botonAnterior2);
        botonAnterior2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.botonAnterior2) {
            finish();
        }


    }
}