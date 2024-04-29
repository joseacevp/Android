package com.example.desplazarseentreactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SegundaActividad extends AppCompatActivity implements View.OnClickListener {

    Button siguiente2, anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        siguiente2 = findViewById(R.id.botonSiguiente2);
        siguiente2.setOnClickListener(this);
        anterior = findViewById(R.id.botonAnterior);
        anterior.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent miIntencion = null;
        switch (view.getId()) {
            case R.id.botonAnterior:
                Log.i("info", "estoy en Anterior");
                miIntencion = new Intent(SegundaActividad.this, MainActivity.class);
                break;
            case R.id.botonSiguiente2:
                Log.i("info", "estoy en Siguiente2");
                miIntencion = new Intent(SegundaActividad.this, ActividadFinal.class);
                break;

        }
        startActivity(miIntencion);

    }
}