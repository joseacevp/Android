package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConsultaPreferencias extends AppCompatActivity {

    TextView campoUsuario,campoPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_preferencias);

        campoPassword = findViewById(R.id.textoPassword);
        campoUsuario = findViewById(R.id.textoNombre);
//        campoUsuario = findViewById(R.id.textoUsuario);

        cargarPreferencias();

    }
    //metodo que carga los datos previamente almacenados en un XML de preferencias
    private void cargarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("usuario","Sin información");
        String password = preferencias.getString("password","Sin información");
        //muestra los datos en campos de texto para su control de prueba
        campoUsuario.setText(usuario);
        campoPassword.setText(password);
    }
}