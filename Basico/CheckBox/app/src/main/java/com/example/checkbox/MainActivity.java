package com.example.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1 = findViewById(R.id.check1);
        c2 = findViewById(R.id.check2);

    }

    public void onClick(View view) {
        if (view.getId()== R.id.botonValidar){
            validar();
        }
    }

    private void validar() {
        String cadena= "Seleccionada la \n";
        if (c1.isChecked()){
        cadena += " Opción 1 \n";
        }
        if (c2.isChecked()){
            cadena += " Opción 2 \n";
        }
        Toast.makeText(getApplicationContext(),cadena,Toast.LENGTH_LONG).show();
    }
}