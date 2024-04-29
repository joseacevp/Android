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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.entidades.Usuario;
import com.example.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaMascota extends AppCompatActivity implements View.OnClickListener {
    EditText id, nombre, raza;
    TextView idPropi;
    Button buscar, actualizar, eliminar;
    Spinner spinnerDuenios;
    ArrayList<Usuario> listaPropietarios;
    ArrayList<String> listaString;


    ConexionSQLiteHelper conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_mascota);

        conexion = new ConexionSQLiteHelper(getApplicationContext(), "db_usuarios", null, 1);

        idPropi = findViewById(R.id.textViewIdPropietario);

        buscar = findViewById(R.id.botonBuscar);
        buscar.setOnClickListener(this);

        eliminar = findViewById(R.id.botonEliminarMascota);
        eliminar.setOnClickListener(this);

        actualizar = findViewById(R.id.botonActualizarMascota);
        actualizar.setOnClickListener(this);

        id = findViewById(R.id.editTextDocumentoMascota);
        nombre = findViewById(R.id.editTextNombreMascota);
        raza = findViewById(R.id.editTextRazaMascota);

        spinnerDuenios = findViewById(R.id.spinnerConsultaPropi);

        consutarBDusuarios();

        //rellena los datos del Spinner con los datos de la lista de String que contien datos de la lista de Usuarios
        //obtenidos con una consulta a la base de datos
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaString);
        spinnerDuenios.setAdapter(adapter);

    }

    private void consutarBDusuarios() {
        //abre la base de datos para consultar
        SQLiteDatabase db = conexion.getWritableDatabase();
        Log.i("info", "Apertura base de datos para consultar");

        //instancia de usuario para recibir los datos de la base de datos
        Usuario persona = null;
        //instancia de la lista de usuarios para recibir los datos de los usuarios de la base datos
        listaPropietarios = new ArrayList<Usuario>();

        //select * from usuarios
        //Cursor para consultar la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                Utilidades.TABLA_USUARIO, null);

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
            listaPropietarios.add(persona);

        }
        db.close();
        //llamamos al metodo para pasar los datos de la lista de usuarios obtendia de la consulta
        //a la lista de String que usaremos para rellenar el Spinner
        trasladarLista();
    }

    private void trasladarLista() {

        listaString = new ArrayList<String>();

        //indicamos que el primer dato de la lista sea Seleccione para que aparezca en el Spinner
        listaString.add("Lista de Propietarios");

        //añadimos los datos de id y nombre de la lista de usuarios obtenida de la base datos
        //al la lista de String que usarimos para rellenar el Spinner
        for (int i = 0; i < listaPropietarios.size(); i++) {
            listaString.add(listaPropietarios.get(i).getId() + " - " + listaPropietarios.get(i).getNombre());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonBuscar:
                buscarMascota();
                break;
            case R.id.botonEliminarMascota:
                eliminar();
                break;
            case R.id.botonActualizarMascota:
                actualizar();
                break;

        }
    }

    private void actualizar() {
        SQLiteDatabase bd = conexion.getWritableDatabase();
        Log.i("info", "Abierta base datos para actualizar");

        int idSpinner = (int) spinnerDuenios.getSelectedItemId();//numero de id propietario

        String[] parametrosConsulta = {id.getText().toString()};
        ContentValues values = new ContentValues();
        int idDuenio;

        if (idSpinner != 0) {
            idDuenio = listaPropietarios.get(idSpinner - 1).getId();
// no se actualiza el id de la mascota por que es autoincrementable
//            values.put(Utilidades.CAMPO_ID_MASCOTA, String.valueOf(id));
            values.put(Utilidades.CAMPO_ID_DUENIO, idDuenio);
            values.put(Utilidades.CAMPO_MOMBRE_MASCOTA, nombre.getText().toString());
            values.put(Utilidades.CAMPO_RAZA, raza.getText().toString());
            bd.update(Utilidades.TABLA_MASCOTA, values, Utilidades.CAMPO_ID_MASCOTA + " = ? ", parametrosConsulta);
            Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();
            bd.close();
        } else {
            Toast.makeText(getApplicationContext(), "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }

        limpiar();
    }

    private void eliminar() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta la base de datos para leztura");
        String[] consultaParametros = {
                id.getText().toString()
        };
        bd.delete(Utilidades.TABLA_MASCOTA, Utilidades.CAMPO_ID_MASCOTA + " = ?", consultaParametros);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_SHORT).show();
        limpiar();
        bd.close();
    }

    private void buscarMascota() {
        //consulta usando Cursor con parametros
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] parametrosConsulta = {
                id.getText().toString()
        };
        String[] datosResultantes = {
                Utilidades.CAMPO_MOMBRE_MASCOTA, Utilidades.CAMPO_RAZA, Utilidades.CAMPO_ID_DUENIO
        };
        try {
            //realiza la consulta
            Cursor cursor = bd.query(Utilidades.TABLA_MASCOTA,
                    datosResultantes,
                    Utilidades.CAMPO_ID_MASCOTA + " =? ",
                    parametrosConsulta,
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            //recibe los datos
            nombre.setText(cursor.getString(0));
            raza.setText(cursor.getString(1));
            idPropi.setText("Id Propieteario actual: " + cursor.getString(2));
//            Toast.makeText(this,String.valueOf( cursor.getInt(2)), Toast.LENGTH_SHORT).show();
            bd.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No existe ninguna mascota con este ID.", Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    private void limpiar() {
        id.setText("");
        nombre.setText("");
        raza.setText("");
        idPropi.setText("");

    }
}