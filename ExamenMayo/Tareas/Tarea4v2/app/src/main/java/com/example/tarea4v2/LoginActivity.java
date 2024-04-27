package com.example.tarea4v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea4v2.ui.ConfigurarFragment;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private String usuario;
    EditText passwordLog;
    Button botonRegistroLog;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Evitar el giro automático
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//recibe el nombre del usuario desde actividad inicio
        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido != null) {
            usuario = datoRecivido.getString("nombreUsuarioInicio");
//            Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show();
        }

        passwordLog = findViewById(R.id.area_Password_login);
        botonRegistroLog = findViewById(R.id.boton_login);

        botonRegistroLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conprobarCredenciales(passwordLog.getText().toString(), usuario)) {
                    Toast.makeText(getApplicationContext(), "DATOS CORRECTOS", Toast.LENGTH_SHORT).show();
                    //llamar a jugo con el dato del nombre de usuario
                    Intent enviar = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle miDato = new Bundle();
                    miDato.putString("nombreUsuarioInicio", usuario);
                    enviar.putExtras(miDato);
                    startActivity(enviar);
                } else {
                    Toast.makeText(getApplicationContext(), "Los datos no son correctos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean conprobarCredenciales(String password,
                                          String nombre) {
        //parametros de la consulta
        String passwordRecu;
        String[] parametros = {nombre};
        db = openOrCreateDatabase("BaseDatosTarea4", Context.MODE_PRIVATE, null);
        try {
            Cursor c = db.rawQuery("SELECT Password FROM Usuarios WHERE nombre =?", parametros);
            // Verificar si el cursor contiene algún resultado
            if (c != null && c.moveToFirst()) {
                passwordRecu = c.getString(0);
                System.out.println(passwordRecu);
                // Comprobar si la contraseña recuperada coincide con la contraseña ingresada
                if (password.equals(passwordRecu)) {
                    System.out.println("VALIDO");
                    return true;
                }
            } c.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error en datos indicados"+e, Toast.LENGTH_SHORT).show();
        }
   return false;
    }
}