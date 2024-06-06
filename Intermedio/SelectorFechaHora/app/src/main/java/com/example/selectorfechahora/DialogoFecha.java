package com.example.selectorfechahora;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnFechaSeleccionada f;

        @Override
        public void onAttach(Context activity) {
            f = (OnFechaSeleccionada) activity;
            super.onAttach(activity);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH)+1;
            int dia = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,ano,mes,dia);
        }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        GregorianCalendar g = new GregorianCalendar(i, i1, i2);
        f.onResultadoFecha(g);
    }

    public interface OnFechaSeleccionada {
        public void onResultadoFecha(GregorianCalendar fecha);
    }
}
