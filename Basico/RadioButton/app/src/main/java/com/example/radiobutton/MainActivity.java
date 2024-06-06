package com.example.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton r1, r2;
    Button botonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonRegistrar = findViewById(R.id.botonRegistrar);
        botonRegistrar.setOnClickListener(this);
        r1 = findViewById(R.id.radioBoton1);
        r2 = findViewById(R.id.radioBoton2);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.botonRegistrar) {
            evento();
        }
    }

    private void evento() {
//        Toast.makeText(getApplicationContext(),"prueba evento",Toast.LENGTH_LONG).show();
        String cadenaTexto = "Seleccionado \n";
        if (r1.isChecked()){
            cadenaTexto += "Opción 1 \n";
        }
        if (r2.isChecked()){
            cadenaTexto += "Opción 2";
        }
        Toast.makeText(getApplicationContext(),cadenaTexto,Toast.LENGTH_SHORT).show();

    }
}