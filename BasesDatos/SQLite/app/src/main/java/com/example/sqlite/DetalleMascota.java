package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite.entidades.Mascotas;
import com.example.sqlite.entidades.Usuario;
import com.example.sqlite.utilidades.Utilidades;

public class DetalleMascota extends AppCompatActivity {
    TextView idMascota, nombreMascota, razaMascota;
    TextView idPersona, nombrePersona, telefonoPersona;

    ConexionSQLiteHelper con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        idMascota = findViewById(R.id.ediIdMascota);
        nombreMascota = findViewById(R.id.editNombreMascota);
        razaMascota = findViewById(R.id.editRazaMascota);

        idPersona = findViewById(R.id.ediIdPersona);
        nombrePersona = findViewById(R.id.editNombrePersona);
        telefonoPersona = findViewById(R.id.editTelefonoPersona);

        con = new ConexionSQLiteHelper(getApplicationContext(),
                "db_usuarios", null, 1);

        recuperarMascota();


    }

    private void recuperarMascota() {

        Bundle bundle = getIntent().getExtras();
        Mascotas mascota = null;

        if (bundle != null) {
            mascota = (Mascotas) bundle.getSerializable("Mascotas");
            idMascota.setText("Id Mascota: "+ mascota.getIdMascota().toString());
            nombreMascota.setText("Nombre: "+ mascota.getNombreMascato());
            razaMascota.setText("Raza: "+mascota.getRaza());
            consultaPersona(mascota.getIdDuenio());
        } else {
            Toast.makeText(getApplicationContext(), "Fallo en los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultaPersona(Integer idDuenio) {
        SQLiteDatabase bd = con.getReadableDatabase();

        Usuario usuario = null;
        String[] consultaParametros = {
                idDuenio.toString()};
        String[] consultaResultado = {
                Utilidades.CAMPO_NOMBRE_PROPIETARIO, Utilidades.CAMPO_TELEFONO
        };

        try {
            Cursor cursor = bd.query(Utilidades.TABLA_USUARIO,
                    consultaResultado, Utilidades.CAMPO_ID_PROPIETARIO + " = ? ",
                    consultaParametros, null, null, null);
            cursor.moveToFirst();
            idPersona.setText("Id Dueño: "+idDuenio.toString());
            nombrePersona.setText("Nombre: "+cursor.getString(0));
            telefonoPersona.setText("Telefono: "+cursor.getString(1));
            cursor.close();


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"fallo en la consulta a la base de datos"
            ,Toast.LENGTH_SHORT).show();
        }

    }
}