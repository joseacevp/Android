package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonGuardar, botonEnviar;
    EditText campoUsuario, campoPassword;
    TextView textoUsuario, textoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(this);
        botonEnviar = findViewById(R.id.botonEnviar);
        botonEnviar.setOnClickListener(this);

        campoUsuario = findViewById(R.id.campoNombre);
        campoPassword = findViewById(R.id.campoPassword);

        textoPassword = findViewById(R.id.textoPassword);
        textoUsuario = findViewById(R.id.textoUsuario);


        //cargamos los datos de XML preferencias
        cargarPreferencias();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonEnviar:
                Log.i("info", "Accionado boton enviar");
                //abre nueva actividad
                Intent intencio = new Intent(getApplicationContext(), ConsultaPreferencias.class);
                startActivity(intencio);
                break;
            case R.id.botonGuardar:
                Log.i("info", "Accionado boton guardar");
                guardarPreferencias();
                break;
        }

    }
    //metodo que carga los datos previamente almacenados en un XML de preferencias
    private void cargarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("usuario","Sin información");
        String password = preferencias.getString("password","Sin información");
        //muestra los datos en campos de texto para su control de prueba
        textoUsuario.setText(usuario);
        textoPassword.setText(password);
    }

    //metodo que almacena los datos de los campos de texto en un archivo .XML para compartirlos
    //con otra actividad de la aplicación.
    private void guardarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);
        //alamacenamos los datos de los campos
        String usuario = campoUsuario.getText().toString();
        String password = campoPassword.getText().toString();
        //introduciomos los en el archivo XML con un editor.
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        //muestra los datos en campos de texto para su control de prueba
        textoUsuario.setText(usuario);
        textoPassword.setText(password);
        editor.commit();

    }
}