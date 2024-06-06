package com.example.tarea5.appprincipal.ui.fechas;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//persistencia y acessibilidad desde otros fragmentos Almacena la fecha seleccionada
public class FechaViewModel extends ViewModel {
    private MutableLiveData<String> selectedDateString = new MutableLiveData<>();

    public void setSelectedDateString(String dateString) {
        selectedDateString.setValue(dateString);
    }

    public MutableLiveData<String> getSelectedDateString() {
        return selectedDateString;
    }
}