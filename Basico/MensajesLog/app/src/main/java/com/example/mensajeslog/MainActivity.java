package com.example.mensajeslog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //1
    //creamos una etiqueta para poder identificar los mensajes en el Logcat a traves del
    //buscador de la terminal Logcat
    //seleccionando la pestaña info buscamos por "Seguimiento"
    public static final String  TAG ="Seguimiento ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //para que podamos escribir en consola usamos Log. como
        // se hace en Java con Print
        Log.i("Info","Valor de información");
        Log.d("Debug","Valor de Debug");
        Log.w("Warning","Valor de Warning");
        Log.e("Error","Valor Error");
        Log.v("Verbose","Valor Verbose");

        //2
        //TAG o etiqueta creada para poder filtrar en el buscador del Logcat
        Log.i(TAG,"Mensaje del Seguimiento 1");


    }
}