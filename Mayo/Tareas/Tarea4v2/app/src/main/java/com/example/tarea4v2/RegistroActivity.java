package com.example.tarea4v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    EditText nombre_formulario_usuario, pass_formulario_usuario;
    Button boton_registrar_usuario_formulario;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //capturamos los datos del formulario
        nombre_formulario_usuario = findViewById(R.id.area_nombre_formu_usua);
        pass_formulario_usuario = findViewById(R.id.area_password_formu_usua);

        boton_registrar_usuario_formulario = findViewById(R.id.boton_registrar_usuario_formulario);

        //evento click boton registrar usuario
        boton_registrar_usuario_formulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
                limpiarFormulario();
            }
        });

    }

    private void limpiarFormulario() {
        nombre_formulario_usuario.setText("");
        pass_formulario_usuario.setText("");
        Intent intent = new Intent(RegistroActivity.this,InicioActivity.class);
        startActivity(intent);
    }

    private void registrarUsuarios() {
        try {
            db = openOrCreateDatabase("BaseDatosTarea4", Context.MODE_PRIVATE, null);
            db.execSQL("INSERT INTO Usuarios VALUES ('"+nombre_formulario_usuario.getText()
                    +"','"+pass_formulario_usuario.getText() +"')");
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Fallo al registrar Usuario."+e, Toast.LENGTH_SHORT).show();
        }
    }
}