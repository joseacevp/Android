package com.example.entrenadormultiplicar.gestionentrenador;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.entrenadormultiplicar.gestionusuarios.Jugador;

import java.util.List;

public class DialogoEntrenadorViewModel extends ViewModel {
    private MutableLiveData<Entrenador> entrenador = new MutableLiveData<>();

    public LiveData<Entrenador> getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador2) {
        entrenador.setValue(entrenador2);
    }
}
