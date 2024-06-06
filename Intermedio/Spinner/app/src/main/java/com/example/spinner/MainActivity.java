package com.example.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView estado;
    Spinner opciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado = findViewById(R.id.estado);
        opciones = findViewById(R.id.idSpinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_dias,
                android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        //evento del spinner captura la seleccion entre la lista de opciones del Spinner
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                Toast.makeText(getApplicationContext(),"Seleccionado: "+ adapterView.getItemAtPosition(posicion).toString(),
                        Toast.LENGTH_SHORT).show();
                estado.setText("Seleccion: "+adapterView.getItemAtPosition(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                estado.setText("");
            }
        });
    }
}