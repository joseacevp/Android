package com.example.accesosqlbasedatos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.accesosqlbasedatos.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<Objeto> listaObjetos;
    AdaptadorRecyclerMultipl adaptadorRecyclerMultipl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        listaObjetos = new ArrayList<>();
        construirRecycleView();
        llenarDatosRecycler();
    }

    private void llenarDatosRecycler() {
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));

    }

    private void construirRecycleView() {
        binding.recyclerLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adaptadorRecyclerMultipl = new AdaptadorRecyclerMultipl(listaObjetos);
        binding.recyclerLista.setAdapter(adaptadorRecyclerMultipl);
    }


}