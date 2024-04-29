package com.example.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.utilidades.Utilidades;

public class ConsultaUsuario extends AppCompatActivity implements View.OnClickListener {

    EditText editId, editNombre, editTelefono;

    ConexionSQLiteHelper conexion;
    Button botonBuscar, botonActualizar, botonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuario);
        botonBuscar = findViewById(R.id.botonBuscar);
        botonBuscar.setOnClickListener(this);
        botonActualizar = findViewById(R.id.botonActualizar);
        botonActualizar.setOnClickListener(this);
        botonEliminar = findViewById(R.id.botonEliminar);
        botonEliminar.setOnClickListener(this);

        editId = findViewById(R.id.editTextDocumento);
        editNombre = findViewById(R.id.editTextNombre);
        editTelefono = findViewById(R.id.editTextTelefono);
        conexion = new ConexionSQLiteHelper(getApplicationContext(), "db_usuarios", null, 1);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonBuscar:
                Log.i("info", "Pulsado boton buscar");
//                consultar();
                consultarSQL();
                break;
            case R.id.botonActualizar:
                Log.i("info", "Pulsado boton actualizar");
                actualizar();
                break;
            case R.id.botonEliminar:
                Log.i("info", "Pulsado boton buscar");
                eliminar();
                break;
        }
    }

    private void consultarSQL() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta base datos para consulta");
        String[] consultaParametros = {
                editId.getText().toString()};//parametros de la consulta, pueden ser varios
        try {
            //select nombre,telefono from usuario where codigo = ?
            Cursor cursor = bd.rawQuery("SELECT " + Utilidades.CAMPO_NOMBRE_PROPIETARIO
                    + " , "
                    + Utilidades.CAMPO_TELEFONO + " FROM "
                    + Utilidades.TABLA_USUARIO + " WHERE "
                    + Utilidades.CAMPO_ID_PROPIETARIO + " =?", consultaParametros);
            cursor.moveToFirst();
            editNombre.setText(cursor.getString(0));//inserta los resultados de la posicion 0 en el editext
            editTelefono.setText(cursor.getString(1));
            cursor.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }


    }

    private void actualizar() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta base datos para actualizar");
        String[] consultaParametros = {
                editId.getText().toString()};//parametros de la consulta, pueden ser varios
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_PROPIETARIO,editNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,editTelefono.getText().toString());
        //sustituye a la consulta SQL select  nombre,telefono from usuario
        bd.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID_PROPIETARIO +" =?",consultaParametros);
        Toast.makeText(getApplicationContext(),"Datos Actualizados",Toast.LENGTH_SHORT).show();
        bd.close();
        limpiar();

    }

    private void eliminar() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta base datos para eliminar");
        String[] consultaParametros = {
                editId.getText().toString()};//parametros de la consulta, pueden ser varios
        bd.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID_PROPIETARIO + "=?",consultaParametros);
        Toast.makeText(getApplicationContext(),"Datos Eliminados",Toast.LENGTH_SHORT).show();
        limpiar();
        bd.close();

    }

    private void consultar() {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta base datos para consulta");

        String[] consultaParametros = {
                editId.getText().toString()};//parametros de la consulta, pueden ser varios
        String[] consultaResultados = {
                Utilidades.CAMPO_NOMBRE_PROPIETARIO, Utilidades.CAMPO_TELEFONO
        };
        try {
            Cursor cursor = bd.query(Utilidades.TABLA_USUARIO, consultaResultados,
                    Utilidades.CAMPO_ID_PROPIETARIO + "=?",
                    consultaParametros, null, null, null);
            cursor.moveToFirst();
            editNombre.setText(cursor.getString(0));//inserta los resultados en la posicion 0
            editTelefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    private void limpiar() {
        editId.setText("");
        editNombre.setText("");
        editTelefono.setText("");
    }


}