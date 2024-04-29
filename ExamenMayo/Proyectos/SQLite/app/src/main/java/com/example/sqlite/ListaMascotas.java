package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.entidades.Mascotas;
import com.example.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ListaMascotas extends AppCompatActivity {

    ListView vistaListMasconta;
    ConexionSQLiteHelper con;
    ArrayList<Mascotas> listaMascotas = null;
    ArrayList<String> listaString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
        vistaListMasconta = (ListView) findViewById(R.id.vistaMascotas);
        con = new ConexionSQLiteHelper(getApplicationContext(), "db_usuarios", null, 1);

        recuperarMascotasBd();

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaString);
        vistaListMasconta.setAdapter(adapter);

        vistaListMasconta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                String informacion = "id: " + listaMascotas.get(posicion).getIdMascota().toString()
                        + "\n";
                informacion += "Nombre: " + listaMascotas.get(posicion).getNombreMascato();
                Toast.makeText(getApplicationContext(),  informacion, Toast.LENGTH_SHORT).show();

                Mascotas mascota = listaMascotas.get(posicion);

                Intent eventoDetalleMascota = new Intent(ListaMascotas.this,DetalleMascota.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Mascotas",mascota);

                eventoDetalleMascota.putExtras(bundle);
                startActivity(eventoDetalleMascota);

            }
        });
    }

    private void recuperarMascotasBd() {
        SQLiteDatabase bd = con.getReadableDatabase();
        Mascotas mascota;
        listaMascotas = new ArrayList<Mascotas>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.TABLA_MASCOTA, null);

        while (cursor.moveToNext()) {
            mascota = new Mascotas();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascato(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));

            listaMascotas.add(mascota);

        }
        bd.close();
        recuperarLista();
    }

    private void recuperarLista() {

        listaString = new ArrayList<String>();
        for (int i = 0 ; i < listaMascotas.size() ; i++){
            listaString.add("Id Mascota: "+listaMascotas.get(i).getIdMascota().toString()
            + "\nNombre: " + listaMascotas.get(i).getNombreMascato());
        }
    }
}