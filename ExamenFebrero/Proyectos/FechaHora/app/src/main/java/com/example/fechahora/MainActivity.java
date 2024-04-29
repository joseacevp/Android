package com.example.fechahora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada, DialogoHora.OnHoraSeleccionada {

    EditText nombre, fecha, edithora;
    DialogoFecha dialogoFecha = new DialogoFecha();
    DialogoHora dialogoHora = new DialogoHora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.editNombre);
        fecha = findViewById(R.id.editFecha);
        edithora = findViewById(R.id.editHora);


    }

    @Override
    public void onResultadoFecha(GregorianCalendar calendario) {
        //     Hay que indicar todos los datos implementados en la clase dia mes y año
        fecha.setText(calendario.get(Calendar.DAY_OF_MONTH)+"/"+calendario.get(Calendar.MONTH)
        +"/"+calendario.get(Calendar.YEAR));
    }

    @Override
    public void onResulatadoHora(GregorianCalendar hora) {
        //     Hay que indicar todos los datos implementados en la clase hora y minutos
        edithora.setText(hora.get(Calendar.HOUR)+":"+hora.get(Calendar.MINUTE));
    }

    public void onClickHora(View view) {
        dialogoHora.show(getFragmentManager(), "hora");
    }

    public void onClickFecha(View view) {
        dialogoFecha.show(getFragmentManager(), "fecha");
    }
}