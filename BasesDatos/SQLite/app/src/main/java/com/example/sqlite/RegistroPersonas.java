package com.example.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.utilidades.Utilidades;

public class RegistroPersonas extends AppCompatActivity implements View.OnClickListener {

    EditText campoId, campoNombre, campoTelef;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_persona);
        campoId = findViewById(R.id.editTextTextId);
        campoNombre = findViewById(R.id.editTextTextNombre);
        campoTelef = findViewById(R.id.editTextTextTelefono);

        registrar = findViewById(R.id.botonRegistrarUsuario);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

//        registroUsuarios();
    registrarUsuarioSQL();

    }

    private void registrarUsuarioSQL() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,
                "db_usuarios", null, 1);
        Log.i("info", "base datos Creada");
        SQLiteDatabase db = conn.getWritableDatabase();//abre la base de datos para editarla

        //insert into usuario (id,nombre,telefono) values (123,'Jose','55566677')
        String insert = "insert into " + Utilidades.TABLA_USUARIO +
                " ( "+ Utilidades.CAMPO_ID_PROPIETARIO +
                " , "+ Utilidades.CAMPO_NOMBRE_PROPIETARIO +
                " ,"+ Utilidades.CAMPO_TELEFONO +
                " )" + " values (" + campoId.getText().toString() +
                ", ' "+ campoNombre.getText().toString() + " ' , ' " + campoTelef.getText().toString() + " ' )";

        db.execSQL(insert);
        db.close();
        Toast.makeText(getApplicationContext(), "Registro realizado", Toast.LENGTH_SHORT).show();
        limpiar();
    }

    private void limpiar() {
        campoId.setText("");
        campoNombre.setText("");
        campoTelef.setText("");
    }

    private void registroUsuarios() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,
                "bd_usuarios", null, 1);
        Log.i("info", "base datos Creada");
        SQLiteDatabase db = conn.getWritableDatabase();//abre la base de datos para editarla

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID_PROPIETARIO, campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE_PROPIETARIO, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, campoTelef.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID_PROPIETARIO, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();

    }
}