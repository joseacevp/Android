package com.example.comunicacionactividades;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProvinciasActivity extends AppCompatActivity   {
    String [] listaProvincias = new String[]{"Toledo",
    "Ciudad Real",
    "Cuenca",
    "Guadalajara",
    "Albaceta"};
    ListView listVista ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincias);

        listVista = findViewById(R.id.listProvincias);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaProvincias);
        listVista.setAdapter(adapter);


        listVista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intencion = new Intent();
                intencion.putExtra("PROVINCIA",adapterView.getItemAtPosition(i).toString());
                setResult(RESULT_OK,intencion);
                finish();
            }
        });
    }


}