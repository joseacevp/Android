package com.example.smscontactos;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smscontactos.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<String> listaDatos, listaResultados;
    static String datoRecivido;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
// Solicitud de permisos
        if (checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        } else tengo_permisos = true;
//fin solicitud permisos
        listaDatos = llenarContactos();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contacto = "";
                listaResultados = buscarContacto(binding.editextBuscar.getText().toString());
                for (int i = 0; i < listaResultados.size(); i++) {

                    contacto += listaResultados.get(i);
                }
                if (listaResultados.size() != 0) {
                    Toast.makeText(MainActivity.this, "Encontro :" + contacto, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Contacto no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.reciclerContactos.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
        //15. enviamos la lista con los datos al adaptador
        AdapterDatos adapter = new AdapterDatos(listaDatos);
        //16. indicamos al recycleView que
        binding.reciclerContactos.setAdapter(adapter);
    }

    @SuppressLint("Range")
    private ArrayList<String> llenarContactos() {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID
        };

        ArrayList<String> lista_contactos_recycle = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, null, null, null);
        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                // Si tiene teléfono lo agregamos a la lista de contactos
                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    lista_contactos_recycle.add(name);
                }
            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos_recycle;
    }
    public static void reenviarDatos(TextView dato) {
        Toast.makeText(dato.getContext(), dato.getText() + " Seleccionado ", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PETICION_PERMISOS)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                tengo_permisos = true;
            else
                tengo_permisos = false;
    }
    @SuppressLint("Range")
    private ArrayList<String> buscarContacto(String contacto) {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID
        };
        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
//        String args_filtro[] = {"%" + contacto + "%"};
        String args_filtro[] = {contacto};

        ArrayList<String> lista_contactos = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, filtro, args_filtro, null);

        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                // Si tiene teléfono lo agregamos a la lista de contactos
                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    lista_contactos.add(name);
                }
            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos;
    }
}