package com.example.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlite.entidades.Usuario;
import com.example.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class RegistroMascotas extends AppCompatActivity {
    Spinner spinnerPropietarios;
    EditText campoNombreMascota, raza;
    ConexionSQLiteHelper conexion;
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascotas);

        spinnerPropietarios = findViewById(R.id.spinnerPropietario);

        campoNombreMascota = findViewById(R.id.editTextNombreMascota);
        raza = findViewById(R.id.editTextRaza);

        conexion = new ConexionSQLiteHelper(this, "db_usuarios", null, 1);
        Log.i("info", "base de datos creada");

        consultarListaPersonas();
        //rellena los datos del Spinner con los datos de la lista de String que contien datos de la lista de Usuarios
        //obtenidos con una consulta a la base de datos
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaString);
        spinnerPropietarios.setAdapter(adapter);


    }

    public void onClickRegistroMascotas(View view) {

        registrarMascotas();

    }

    private void consultarListaPersonas() {
        //abre la base de datos para consultar
        SQLiteDatabase db = conexion.getWritableDatabase();
        Log.i("info", "Apertura base de datos para consultar");

        //instancia de usuario para recibir los datos de la base de datos
        Usuario persona = null;
        //instancia de la lista de usuarios para recibir los datos de los usuarios de la base datos
        listaUsuarios = new ArrayList<Usuario>();

        //select * from usuarios
        //Cursor para consultar la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

        //recorremos el cursor para optener los datos obtenidos
        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            //control de datos por consola
            Log.i("info", persona.getId().toString());
            Log.i("info", persona.getNombre().toString());
            Log.i("info", persona.getTelefono().toString());

            //añadimos los usuarios a la lista de usuarios
            listaUsuarios.add(persona);

        }
        db.close();
        //llamamos al metodo para pasar los datos de la lista de usuarios obtendia de la consulta
        //a la lista de String que usaremos para rellenar el Spinner
        trasladarLista();
    }

    private void trasladarLista() {
        listaString = new ArrayList<String>();

        //indicamos que el primer dato de la lista sea Seleccione para que aparezca en el Spinner
        listaString.add("Seleccione");

        //añadimos los datos de id y nombre de la lista de usuarios obtenida de la base datos
        //al la lista de String que usarimos para rellenar el Spinner
        for (int i = 0; i < listaUsuarios.size(); i++) {
            listaString.add(listaUsuarios.get(i).getId() + " - " + listaUsuarios.get(i).getNombre());
        }
    }

    private void registrarMascotas() {

        SQLiteDatabase bd = conexion.getWritableDatabase();
        int idSpinner = (int) spinnerPropietarios.getSelectedItemId();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_MOMBRE_MASCOTA, campoNombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA, raza.getText().toString());

        int idDuenio;

        if (idSpinner != 0) {
            idDuenio = listaUsuarios.get(idSpinner - 1).getId();
            values.put(Utilidades.CAMPO_ID_DUENIO, idDuenio);
            int idResultado = (int) bd.insert(Utilidades.TABLA_MASCOTA, Utilidades.CAMPO_ID_MASCOTA, values);
            Toast.makeText(getApplicationContext(),
                    "El id del Propietario es : " + idResultado,
                    Toast.LENGTH_SHORT).show();
            bd.close();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Fallo al escribir en la Base de Datos.",
                    Toast.LENGTH_SHORT).show();
        }
        limpieza();
    }

    private void limpieza() {
        campoNombreMascota.setText("");
        raza.setText("");
    }
}