package com.example.sqlite;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sqlite.entidades.Usuario;

import java.util.Locale;

public class DetallePersona extends AppCompatActivity {
    TextView id, nombre, telefono;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);
        id = findViewById(R.id.ediId);
        nombre = findViewById(R.id.editNombre);
        telefono = findViewById(R.id.editTelefono);

        recuperarPersona();

    }

    private void recuperarPersona() {
        //creamos el bundle recuperando los datos recibidos por la intencion
        Bundle bundle = getIntent().getExtras();
        //creamos el usuario que recibira los datos del intent
        Usuario usuarioRecuperado = null;

        //validadmos si recibimos datos
        if (bundle != null) {
            usuarioRecuperado = (Usuario) bundle.getSerializable("Persona");
            id.setText("Id usuario: "+usuarioRecuperado.getId().toString());
            nombre.setText("Nombre: "+usuarioRecuperado.getNombre());
            telefono.setText("Telefono: "+usuarioRecuperado.getTelefono());
        }


    }
}