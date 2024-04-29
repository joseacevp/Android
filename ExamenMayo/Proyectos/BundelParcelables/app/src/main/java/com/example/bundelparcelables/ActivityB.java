package com.example.bundelparcelables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        TextView textView = findViewById(R.id.textView2);

        Contacto c = (Contacto) getIntent().getParcelableExtra("pedro");
        //definimos un formato de fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        //pasamos la fecha recivida de long a string con el formato de fecha definido
        String formatoFecha = dateFormat.format(c.mFechaNacimiento);

        textView.setText(c.mNombre + " " + formatoFecha);

    }
}