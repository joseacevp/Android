package com.example.bundelparcelables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    Contacto contacto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            contacto = new Contacto("Pedro Pérez","66655566",2,simpleDateFormat.parse("29/10/1975"),true);
            contacto.addFamiliar(new Contacto("Elena Pérez","55434455",0,simpleDateFormat.parse("02/02/2002"),false));
        }catch (ParseException e){
            Toast.makeText(this, "Error en el formato de la Fecha", Toast.LENGTH_SHORT).show();
        }
        TextView texto = findViewById(R.id.textoContacto);
        texto.setText(contacto.mNombre+ contacto.mFechaNacimiento);
    }

    public void enviar(View view) {
        Intent intent = new Intent(this,ActivityB.class);
        intent.putExtra("pedro",contacto);
        startActivity(intent);
    }
}