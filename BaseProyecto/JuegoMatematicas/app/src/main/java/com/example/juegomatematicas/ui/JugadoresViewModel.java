package com.example.juegomatematicas.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JugadoresViewModel extends ViewModel {
    //datos nombre jugador
    private MutableLiveData<String> datoNombre = new MutableLiveData<>();
    public LiveData<String> getNombre() {
        return datoNombre;
    }
    public void setNombre(String value) {
        datoNombre.setValue(value);
    }

    //datos foto

    private MutableLiveData<String> datoFoto = new MutableLiveData<>();
    public LiveData<String> getfoto() {
        return datoFoto;
    }
    public void setFoto(String value) {
        datoFoto.setValue(value);
    }

}
