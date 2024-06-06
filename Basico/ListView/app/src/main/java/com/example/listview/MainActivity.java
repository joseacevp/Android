package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.listViewId);
        //crear adaptador
        ArrayAdapter<CharSequence> adaptador
                = ArrayAdapter.createFromResource(this,R.array.arrayDias,
                android.R.layout.simple_list_item_1);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(),"Selecciono: "
                        + adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}