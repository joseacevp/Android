package com.example.tableroajedred;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( new ChessboardView(this));//indica que se use el diseñó de la Clase
        //ChessboardView en la inserface grafica de la aplicación. y no el diseño del XML activity_main

    }

}