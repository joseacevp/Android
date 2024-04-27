package com.example.servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
/*
CASO PRÁCTICO: Crear un servicio no vinculado (Unbounded) que cada tres segundos compruebe si hay conexión
Wifi, reportándolo al LogCat.

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void detener(View view) {
        stopService(new Intent(getBaseContext(), WirelessTester.class));
    }

    public void arrancar(View view) {
        startService(new Intent(getBaseContext(), WirelessTester.class));
    }


    }