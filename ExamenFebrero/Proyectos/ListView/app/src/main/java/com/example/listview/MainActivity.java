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
    ArrayList<String> listaStarts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listView);
        //anadimos los datos del ArrayList
        listaStarts.add("Robb Stark");
        listaStarts.add("Ned Stark");
        listaStarts.add("Brandon Stark");
        listaStarts.add("Sansa Stark");
        listaStarts.add("Aria Stark");

        //crear adaptador
        ArrayAdapter adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listaStarts);

        lista.setAdapter(adaptador);
        //evento de click en los elementos del ListView
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Pulso: "+listaStarts.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}