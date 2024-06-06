package com.example.contactosdetalle;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactosdetalle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;
    static final int SELECCIONAR_CONTACTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Solicitud de permisos
        if (checkSelfPermission("android.permission.READ_CONTACTS") != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission("android.permission.WRITE_CONTACTS") != PackageManager.PERMISSION_GRANTED
                ||
                checkSelfPermission("android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_CONTACTS",
                            "android.permission.SEND_SMS"},
                    PETICION_PERMISOS);
        } else tengo_permisos = true;
        if (tengo_permisos) {
            Toast.makeText(this, "tengo permisos", Toast.LENGTH_SHORT).show();
        }
        binding.texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirContactos();
                Toast.makeText(MainActivity.this, "Pulso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void abrirContactos() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, SELECCIONAR_CONTACTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECCIONAR_CONTACTO) {
            if (resultCode == RESULT_OK) {
                // El usuario ha seleccionado un contacto
                // Puedes manejar el contacto seleccionado aquí
                Toast.makeText(this, "Contacto seleccionado", Toast.LENGTH_SHORT).show();

                //ver el contacto -> el intent recibe además,
                // el URI del contacto seleccionado
                //lanza la aplicacion de detalles de contacto de android
                Uri contactUri = data.getData();
                startActivity(new Intent(Intent.ACTION_VIEW, contactUri));
                
            } else {
                // El usuario ha cancelado la selección
                Toast.makeText(this, "Selección cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}