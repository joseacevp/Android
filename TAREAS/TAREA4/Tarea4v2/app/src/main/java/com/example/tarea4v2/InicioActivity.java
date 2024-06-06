package com.example.tarea4v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity {
    View view;
    String nombreUsuarioInicio;
    Spinner usuarios;
    Button botonInicio, botonRegistro;
    private ArrayList<String> lista_usuarios;
    SQLiteDatabase db;
    static boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        // Evitar el giro automático
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Solicitud de permisos
        if (getApplicationContext().getApplicationContext().checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                getApplicationContext().getApplicationContext().checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        } else {
            InicioActivity.tengo_permisos = true;
        }
        usuarios = findViewById(R.id.spinner_usuarios_inicio);
        botonInicio = findViewById(R.id.botonSeleccionInicio);
        botonRegistro = findViewById(R.id.botonRegistroInico);

        crearBaseDatos();

//Spinner usuarios
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_item, cargarDatosUsuariosSQL());
        usuarios.setAdapter(adapter);
        usuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //llama a la actividad con la configuracion del usuario seleccionado
                nombreUsuarioInicio = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //boton inicio
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nombreUsuarioInicio.equals("Administrador") ) {
                    Intent enviar = new Intent(InicioActivity.this, MainActivity.class);
                    Bundle miDato = new Bundle();
                    miDato.putString("nombreUsuarioInicio", nombreUsuarioInicio);
                    enviar.putExtras(miDato);
                    startActivity(enviar);
                } else {
                    Intent enviar = new Intent(InicioActivity.this, LoginActivity.class);
                    Bundle miDato = new Bundle();
                    miDato.putString("nombreUsuarioInicio", nombreUsuarioInicio);
                    enviar.putExtras(miDato);
                    startActivity(enviar);
                }
            }
        });


        //boton crear usuario
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enviar = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(enviar);
            }
        });
    }

    //crea una base de datos con un usario Administrador y un password 1234
    private void crearBaseDatos() {
        db = openOrCreateDatabase("BaseDatosTarea4", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuarios(Nombre VARCHAR ,Password VARCHAR);");
        db.execSQL("INSERT OR IGNORE INTO Usuarios (Nombre, Password)\n" +
                "SELECT 'Administrador', '1234'\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1 FROM Usuarios WHERE Nombre = 'Administrador'\n" +
                "); ");

    }

    //carga en el spinner de selección de usuarios los usuarios reguistrados en la base de datos
    private ArrayList<String> cargarDatosUsuariosSQL() {
        lista_usuarios = new ArrayList<>();
        ArrayAdapter<String> adaptador;
        Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);
        if (c.getCount() == 0)
            lista_usuarios.add("No hay registros");
        else {
            while (c.moveToNext())
                lista_usuarios.add(c.getString(0));
        }
        c.close();
        return lista_usuarios;
    }
}