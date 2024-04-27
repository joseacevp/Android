package com.example.recyclerview_ejemplo01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MostrarRecyclerView extends AppCompatActivity implements View.OnClickListener {

    public static final ArrayList<Integer> ITEMS= new ArrayList<Integer>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_recycler_view);

        inicializarDataSet(100);
        mostrarDataSet();

        Button buttonVolver = findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(this);


        /*
         * INICIO CREACIÓN DEL RecyclerView
         */
        recyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        myAdapter = new MyAdapter(ITEMS);
        recyclerView.setAdapter(myAdapter);
        /*
         * FIN CREACIÓN DEL RecyclerView
         */
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    public void inicializarDataSet(int cantidad){
        for (int i=0 ; i<cantidad ; i++) {
            ITEMS.add(new Integer(i));
        }
    }

    public void mostrarDataSet(){
        for (int i=0; i < ITEMS.size(); i++){
            System.out.println(i + " --> " + ITEMS.get(i).toString());
        }
    }
}