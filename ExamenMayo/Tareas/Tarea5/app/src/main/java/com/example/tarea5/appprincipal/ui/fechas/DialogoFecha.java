package com.example.tarea5.appprincipal.ui.fechas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;
// clase para lanzar un dialogo donde seleccionar la fecha en un calendario devuelve la fecha
// seleccionada
public class DialogoFecha extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnFechaSeleccionada fechaSeleccionadaListener;

    public interface OnFechaSeleccionada {
        public void onResultadoFecha(GregorianCalendar fecha);
    }
    public void setFechaSeleccionadaListener(OnFechaSeleccionada listener) {
        fechaSeleccionadaListener = listener;
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        GregorianCalendar fecha = new GregorianCalendar(i, i1, i2);
        fechaSeleccionadaListener.onResultadoFecha(fecha);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH)+1;
        int dia = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }
}
