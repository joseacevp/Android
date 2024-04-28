package com.example.selectorfechahora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.selectorfechahora.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada, DialogoHora.OnHoraSeleccionada {
    ActivityMainBinding binding;
    DialogoFecha fecha = new DialogoFecha();
    DialogoHora hora = new DialogoHora();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.botonFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecha.show(getFragmentManager(), "fecha");
            }
        });
        binding.botonHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora.show(getFragmentManager(), "hora");
            }
        });
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
//     Hay que indicar todos los datos implementados en la clase dia mes y año
        binding.editTextFechaNacimiento.setText(fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH)
                + "/" + fecha.get(Calendar.YEAR));
    }

    @Override
    public void onResultadoHora(GregorianCalendar hora) {
        //     Hay que indicar todos los datos implementados en la clase hora y minutos
        binding.editTextHora.setText(hora.get(Calendar.HOUR) + " : " + Calendar.MINUTE);
    }
}