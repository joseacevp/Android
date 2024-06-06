package com.example.comunicacionactivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton;
    EditText campoEditable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.botonIngresar);
        campoEditable = findViewById(R.id.editTextTextPersonName);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        Log.i("info","accion enviar");
        Intent enviar = new Intent(MainActivity.this,SegundaActividad.class);
        Bundle miDato = new Bundle();
        miDato.putString("dato",campoEditable.getText().toString());
        enviar.putExtras(miDato);
        startActivity(enviar);
    }
}