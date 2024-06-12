package com.example.firebaseimagenes;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //conexion a la base de datos firebase
    //crea un hijo donde se guardan los datos.
    private TextView textoFirebase;
    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRooChild = mDatabaseReference.child("texto");
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        textoFirebase = findViewById(R.id.textViewPrincipal);

// Solicitud de permisos
        if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED
                ||
                checkSelfPermission("android.permission.INTERNET") != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            "android.permission.READ_EXTERNAL_STORAGE",
                            "android.permission.INTERNET"},
                    PETICION_PERMISOS);
        }
//fin solicitud permisos







    }

    //metos que se ejecuta al iniciarse la aplicacion

    @Override
    protected void onStart() {
        super.onStart();
        //crear un evento que que escucha si se a modificado eltexto
        mRooChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //cambia el texto de la app
                String texto = snapshot.getValue(String.class);
                textoFirebase.setText(texto);
                Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                Toast.makeText(MainActivity.this, "Fallo al cambiar datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PETICION_PERMISOS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                tengo_permisos = true;
            Toast.makeText(this, "Tengo permisos", Toast.LENGTH_SHORT).show();
        } else {
            tengo_permisos = false;
            Toast.makeText(this, "Sin permisos", Toast.LENGTH_SHORT).show();

        }


    }
}