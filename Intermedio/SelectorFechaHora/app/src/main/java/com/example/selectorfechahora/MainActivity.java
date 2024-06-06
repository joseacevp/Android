package com.example.selectorfechahora;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,DialogoFecha.OnFechaSeleccionada,DialogoHora.OnHoraSeleccionada {
    DialogoFecha fecha = new DialogoFecha();
    DialogoHora hora = new DialogoHora();
    Button bfecha,bhora;
    EditText textFecha,textHora,textNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bfecha = findViewById(R.id.boton_fecha);
        bfecha.setOnClickListener(this);

        bhora = findViewById(R.id.boton_hora);
        bhora.setOnClickListener(this);

        textFecha = findViewById(R.id.editTextFechaNacimiento);
        textHora = findViewById(R.id.editTextHora);
        textNombre = findViewById(R.id.editTexPersonName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton_fecha:
                fecha.show(getFragmentManager(),"fecha");
                break;
            case R.id.boton_hora:
                hora.show(getFragmentManager(),"hora");
                break;
        }
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        textFecha.setText(fecha.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR));
    }

    @Override
    public void onResultadoHora(GregorianCalendar hora) {
        textHora.setText(hora.get(Calendar.HOUR)+":"+ hora.get(Calendar.MINUTE));
    }
}