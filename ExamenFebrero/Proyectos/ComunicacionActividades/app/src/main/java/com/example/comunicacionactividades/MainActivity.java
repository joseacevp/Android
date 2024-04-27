package com.example.comunicacionactividades;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bonton;
    public static final int SELECCIONA_PROVINCIA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bonton = findViewById(R.id.botonSelecciona);

    }

    public void seleccionar(View view) {
        Intent intencionActi = new Intent(getApplicationContext(), ProvinciasActivity.class);
        startActivityForResult(intencionActi,SELECCIONA_PROVINCIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        TextView texto = findViewById(R.id.textResultado);

        if (requestCode == SELECCIONA_PROVINCIA) {
            if (resultCode == RESULT_OK){
                texto.setText("Se ha seleccionado: \n"+data.getStringExtra("PROVINCIA"));
            }
        }
    }
}