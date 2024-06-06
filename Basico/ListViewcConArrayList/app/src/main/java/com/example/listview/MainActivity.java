package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    TextView etiResul;
    ArrayList<String> listaDias = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listViewId);
        etiResul = findViewById(R.id.textView2);


        //anadimos los datos del ArrayList
        listaDias.add("Lunes");
        listaDias.add("Martes");
        listaDias.add("Miercoles");
        listaDias.add("Jueves");
        listaDias.add("Viernes");
        listaDias.add("Sabado");
        listaDias.add("DOMINGO");
        //ejemplo carga de datos con ciclo for ejemplo de carga de datos orientado base de datos
        for (int i=0;i<=10;i++){
            listaDias.add("Dia semana:"+i);
        }
        //crear adaptador
        ArrayAdapter adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listaDias);

        lista.setAdapter(adaptador);
        //evento de click en los elementos del ListView
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //lanza un mensaje emergente con el nombre de la selección
                Toast.makeText(adapterView.getContext(),"Selecciono: "
                        + adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

                //refleja la seleccion en una etiqueta TextView.
                etiResul.setText("Selecciono: "+adapterView.getItemAtPosition(i).toString());
            }
        });
    }
}