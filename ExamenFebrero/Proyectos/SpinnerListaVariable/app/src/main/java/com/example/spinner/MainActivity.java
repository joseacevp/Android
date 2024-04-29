package com.example.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView estado;
    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        estado = findViewById(R.id.estado);
        opciones = findViewById(R.id.idSpinner);

        ArrayList<String> comboDias = new ArrayList<String>();
        comboDias.add("Seleccione");
        comboDias.add("Lunes");
        comboDias.add("Martes");
        comboDias.add("Miercoles");
        comboDias.add("Jueves");
        comboDias.add("Viernes");
        comboDias.add("Sabado");
        comboDias.add("Domingo");

        //forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, comboDias);
        opciones.setAdapter(adapter);

        //evento del spinner captura la seleccion entre la lista de opciones del Spinner
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                Toast.makeText(getApplicationContext(), "Seleccionado: " + adapterView.getItemAtPosition(posicion).toString(),
                        Toast.LENGTH_SHORT).show();
                estado.setText("Seleccion: " + adapterView.getItemAtPosition(posicion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

    }
}