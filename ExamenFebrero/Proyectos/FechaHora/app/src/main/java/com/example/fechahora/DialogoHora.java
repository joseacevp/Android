package com.example.fechahora;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoHora extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    OnHoraSeleccionada hora;

    public interface OnHoraSeleccionada {
        public void onResulatadoHora(GregorianCalendar hora);
    }

    //devuelve el resultado de la llamada a la interface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hora = (OnHoraSeleccionada) context;
    }

    //crea el dialogo para seleccionar la hora y los minutos
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR);
        int minutos = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minutos, true);
    }

    //asigna la hora y los minutos a la interface en formato Gregoriano
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        GregorianCalendar g = new GregorianCalendar();
        g.set(GregorianCalendar.HOUR,i);
        g.set(GregorianCalendar.MINUTE,i1);
        hora.onResulatadoHora(g);
    }
}
