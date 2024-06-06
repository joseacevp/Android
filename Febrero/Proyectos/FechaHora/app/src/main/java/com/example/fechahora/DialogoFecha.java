package com.example.fechahora;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    OnFechaSeleccionada fecha;

    public interface OnFechaSeleccionada {
        public void onResultadoFecha(GregorianCalendar calendario);
    }

    @Override
    public void onAttach(Context context) {
        fecha = (OnFechaSeleccionada) context;
        super.onAttach(context);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int allo = calendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, dia, mes, allo);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        GregorianCalendar g = new GregorianCalendar(i, i1, i2);
        fecha.onResultadoFecha(g);

    }


}
