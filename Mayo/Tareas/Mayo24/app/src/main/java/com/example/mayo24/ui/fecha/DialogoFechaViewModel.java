package com.example.mayo24.ui.fecha;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DialogoFechaViewModel extends ViewModel {
    private MutableLiveData<String> selecFechaData = new MutableLiveData<>();

    public void setSelecFechaData(String dateString) {
        selecFechaData.setValue(dateString);
    }

    public MutableLiveData<String> getSelecFechaData() {
        return selecFechaData;
    }
}
