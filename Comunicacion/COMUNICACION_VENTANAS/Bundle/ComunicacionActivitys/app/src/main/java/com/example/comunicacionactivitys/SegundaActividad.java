package com.example.comunicacionactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundaActividad extends AppCompatActivity implements View.OnClickListener {

    Button botonRegresar;
    TextView etiqueta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);
        botonRegresar = findViewById(R.id.botonRegresar);
        botonRegresar.setOnClickListener(this);
        etiqueta = findViewById(R.id.textView);
        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido!=null){
          String datoString = datoRecivido.getString("dato");
          etiqueta.setText("Bienvenido: "+datoString);

        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}