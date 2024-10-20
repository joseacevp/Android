package com.example.entrenadormultiplicar.gestionentrenador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.entrenadormultiplicar.gestionusuarios.Jugador;

import java.util.List;

public class DialogoEntrenadorViewModel extends ViewModel {
    private final MutableLiveData<Jugador> entrenador = new MutableLiveData<>();

    public LiveData<Jugador> getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Jugador entrenador2) {
        entrenador.setValue(entrenador2);
    }
}
