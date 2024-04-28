package com.example.webemailmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;

import com.example.webemailmap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    VentanaEmail ventanaEmail = new VentanaEmail();
    VentanaWeb ventanaWeb = new VentanaWeb();
    VentanaMap ventanaMap = new VentanaMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ventanaEmail.show(getSupportFragmentManager(),"email");
            }
        });

        binding.botonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaWeb.show(getSupportFragmentManager(),"Web");
            }
        });
        binding.botonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaMap.show(getSupportFragmentManager(),"mapa");
            }
        });
    }
}