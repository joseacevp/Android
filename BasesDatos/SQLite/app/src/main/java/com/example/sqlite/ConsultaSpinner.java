package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sqlite.entidades.Usuario;
import com.example.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaSpinner extends AppCompatActivity {

    Spinner comboPersonas;
    EditText id, nombre, telefono;
    ConexionSQLiteHelper conexion;
    ArrayList<Usuario> listaUsuarios;
    ArrayList<String> listaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);
        comboPersonas = findViewById(R.id.idSpinner);
        id = findViewById(R.id.editTextid);
        nombre = findViewById(R.id.editTextNombre);
        telefono = findViewById(R.id.editTextTelefono);

        //conexion con la base de datos para consultar los usuarios
        conexion = new ConexionSQLiteHelper(getApplicationContext(),
                "db_usuarios", null, 1);
        consutarBDusuarios();

        //rellena los datos del Spinner con los datos de la lista de String que contien datos de la lista de Usuarios
        //obtenidos con una consulta a la base de datos
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item,listaString);
        comboPersonas.setAdapter(adapter);


        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicion, long l) {
                Toast.makeText(getApplicationContext(), "Seleccionado: " + parent.getItemAtPosition(posicion).toString(),
                        Toast.LENGTH_SHORT).show();
                //como en el Spinner tenemos una posicion mas que en la lista de Usuarios
                //para que aparezca la palabra Selecciona en la posicion 0 tenemos que validar
                //la posicion distinta de cero si no se cuelga porque Selecciona no es un objeto
                //de la lista de Usuarios
                //e indicar -1 en la posicion para compensar la posición.
                if (posicion!=0){
                    id.setText(listaUsuarios.get(posicion-1).getId().toString());
                    nombre.setText(listaUsuarios.get(posicion-1).getNombre());
                    telefono.setText(listaUsuarios.get(posicion-1).getTelefono());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consutarBDusuarios() {
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
            listaString.add(listaUsuarios.get(i).getId() +" - " + listaUsuarios.get(i).getNombre());
        }
    }

}