package com.example.tarea5.appprincipal.ui.convocados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tarea5.appprincipal.ui.equipo.Jugador;

import java.util.ArrayList;

public class ConvocadosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Jugador>> listaConvocados = new MutableLiveData<>();

    public void setListaConvocados(ArrayList<Jugador> convocados) {
        listaConvocados.setValue(convocados);
    }

    public LiveData<ArrayList<Jugador>> getListaConvocados() {
        return listaConvocados;
    }
}
