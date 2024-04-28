package com.example.servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.servicios.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    WirelessTester wirelessTester = new WirelessTester();
    /*
    CASO PRÁCTICO: Crear un servicio no vinculado (Unbounded) que cada tres segundos compruebe si hay conexión
    Wifi, reportándolo al LogCat.

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnArrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrancar(view);
            }
        });

    }

    public void detener(View view) {
        stopService(new Intent(getBaseContext(), WirelessTester.class));
    }

    public void arrancar(View view) {
        startService(new Intent(getBaseContext(), WirelessTester.class));
    }


}