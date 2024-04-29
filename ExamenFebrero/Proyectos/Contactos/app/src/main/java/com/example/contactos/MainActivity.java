package com.example.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Solicitud de permisos
        if (checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        } else tengo_permisos = true;
        lista = findViewById(R.id.listaContactos);
        if (tengo_permisos) {
            listarcontactos(lista);
        }

    }

    private void listarcontactos(ListView lista) {
        // Definir las columnas que quieres recuperar
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        // Obtener el cursor con los datos de los contactos
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        // Configurar el adaptador para el ListView
        String[] fromColumns = {ContactsContract.Contacts.DISPLAY_NAME};
        int[] toViews = {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                fromColumns,
                toViews,
                0
        );

        // Asignar el adaptador al ListView
        this.lista.setAdapter(adapter);
    }

}


