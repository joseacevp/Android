package com.example.menuflotante;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
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
        //crear adaptador menu flotante lista1
        ArrayAdapter adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaStarts);
        lista.setAdapter(adaptador);
        //evento de click en los elementos del ListView
        registerForContextMenu(lista);
    }
    //gestiona la selecciona de los intent de la lista
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.matar:
                Toast.makeText(getApplicationContext(), "Hemos matado a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            case R.id.sanar:
                Toast.makeText(getApplicationContext(), "Hemos sanado a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            case R.id.enviarmensaje:
                Toast.makeText(getApplicationContext(), "Le hemos enviado un mensaje a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Le hemos hecho otra cosa a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_flotante, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

}