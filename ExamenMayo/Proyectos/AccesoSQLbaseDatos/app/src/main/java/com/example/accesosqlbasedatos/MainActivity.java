package com.example.accesosqlbasedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.accesosqlbasedatos.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<Objeto> listaObjetos;
    private SQLiteDatabase db;
    AdaptadorRecyclerMultipl adaptadorRecyclerMultipl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaObjetos = new ArrayList<>();

        //Crea si no existe la base de datos SQLite
        db = openOrCreateDatabase("MisDiscos", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS misDiscos(Grupo VARCHAR,Disco VARCHAR);");

        //acción de añadir un nuevo dato a la base de datos
        binding.imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadir();
            }
        });
        binding.imageButtonDelecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });
        actualizarRecycler();


    }

    private void actualizarRecycler() {
        limpiarCampos();
        llenarDatosRecycler();
        construirRecycleView();
    }

    private void limpiarCampos() {
        binding.editAlbum.setText("");
        binding.editArtista.setText("");
    }

    public void actualizarCampos(String nombre,String album) {
        binding.editAlbum.setText(album);
        binding.editArtista.setText(nombre);
    }

    private void borrar() {
        try {
            db.execSQL("DELETE FROM MisDiscos WHERE Grupo = '" +
                    binding.editArtista.getText().toString() + "' AND Disco='" +
                    binding.editAlbum.getText().toString() + "'");
            Toast.makeText(this, "Se borró el disco " +
                    binding.editAlbum.getText().toString(), Toast.LENGTH_LONG).show();
        } catch (SQLException s) {
            Toast.makeText(this, "Error al borrar! - " + s.toString(), Toast.LENGTH_LONG).show();
        }
        actualizarRecycler();
    }

    private void anadir() {
        db.execSQL("INSERT INTO MisDiscos VALUES ('" +
                binding.editArtista.getText().toString() + "','" +
                binding.editAlbum.getText().toString()
                + "')");
        Toast.makeText(this, "Se añadió el disco " +
                binding.editAlbum.getText().toString(), Toast.LENGTH_LONG).show();
        actualizarRecycler();
    }

    private void llenarDatosRecycler() {
        listaObjetos.clear();
        Cursor c = db.rawQuery("SELECT * FROM MisDiscos", null);
        if (c.getCount() == 0) {
//            listaObjetos.add(new Objeto("no hay registro", "no hay registro"));
            Toast.makeText(this, "Sin registros", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {
                Objeto objeto = new Objeto(c.getString(0), c.getString(1));
                listaObjetos.add(objeto);
            }
        }

        c.close();
//       b.close();

//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));
//        listaObjetos.add(new Objeto("nombre del Artista", "Titulo del Album"));

    }

    private void construirRecycleView() {
        binding.recyclerLista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adaptadorRecyclerMultipl = new AdaptadorRecyclerMultipl(listaObjetos,this);
        binding.recyclerLista.setAdapter(adaptadorRecyclerMultipl);

    }


}