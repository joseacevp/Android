package com.example.botones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // implementacion del metodo implementado en el clase
    // 1 Implement añadir implemets View.OnClickListener a la clase
    Button anonima, implement, onClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anonima = findViewById(R.id.anonimo);
        implement = findViewById(R.id.implement);
        //3 implement
        implement.setOnClickListener(this);
        onClick = findViewById(R.id.onClick);
        onClick.setOnClickListener(this);

        anonima.setOnClickListener(this);

        // implementacion del metodo OnClick anonimo
        //1 anonimo
//        anonima.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(getApplicationContext(), "Desde anonima", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    //2 Implement
    // añadido el metodo onClick tras implementar OnClickListener a la clase Main
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.anonimo:
                Toast.makeText(getApplicationContext(), "Desde anonima", Toast.LENGTH_LONG).show();
                break;
            case R.id.onClick:
                Toast.makeText(getApplicationContext(), "llamado desde metodo Onclick", Toast.LENGTH_LONG).show();
                break;
            case R.id.implement:
                Toast.makeText(getApplicationContext(), "llamado desde metodo Implementado", Toast.LENGTH_LONG).show();
                break;

        }
        if (view.getId()== implement.getId()){
//            Toast.makeText(getApplicationContext(), "llamado desde metodo Implementado", Toast.LENGTH_LONG).show();
        }

    }

    //2 OnClick
    // creamos un metodo que reciva el evento del boton OnClick
//    public void EventoOnClick(View view) {
//        Toast.makeText(getApplicationContext(), "Llamado desde evento OnClick ", Toast.LENGTH_LONG).show();
//    }
}