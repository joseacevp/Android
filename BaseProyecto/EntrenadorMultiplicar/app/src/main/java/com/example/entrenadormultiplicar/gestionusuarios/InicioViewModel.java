package com.example.entrenadormultiplicar.gestionusuarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.entrenadormultiplicar.R;

import java.util.ArrayList;
import java.util.List;

public class InicioViewModel extends ViewModel {

    private MutableLiveData<List<Jugador>> jugadoresInicio = new MutableLiveData<>();

    public LiveData<List<Jugador>> getJugadoresInicio() {
        return jugadoresInicio;
    }

    public void setJugadoresInicio(List<Jugador> jugadores) {
        jugadoresInicio.setValue(jugadores);
    }
}
