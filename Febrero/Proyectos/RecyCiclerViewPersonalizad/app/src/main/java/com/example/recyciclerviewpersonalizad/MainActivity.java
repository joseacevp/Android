package com.example.recyciclerviewpersonalizad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //3
    ArrayList<Personaje> lista;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        construirRecycleView();
        llenarPersonajes();
    }

    private void construirRecycleView() {
        lista = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdaptadorRecycleViewPers adaptador = new AdaptadorRecycleViewPers(lista);

        //metodo para manejar los eventos al click en los intem del RecycleView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pulsaste : "
                        + lista.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adaptador);
    }

    private void llenarPersonajes() {
        lista.add(new Personaje("Hulk", "El forzudo Verde", R.drawable.hulk));
        lista.add(new Personaje("Capitan America", "Heroe Americano", R.drawable.capitan));
        lista.add(new Personaje("Iron Man", "Rico Bueno", R.drawable.iron));
    }
}