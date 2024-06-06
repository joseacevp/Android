package com.example.tarea5.appprincipal.ui.equipo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EquipoViewModel extends ViewModel {
    private MutableLiveData<List<Jugador>> jugadoresSeleccionados = new MutableLiveData<>();



    public LiveData<List<Jugador>> getJugadoresSeleccionados() {
        return jugadoresSeleccionados;
    }

    public void setJugadoresSeleccionados(List<Jugador> jugadores) {
        jugadoresSeleccionados.setValue(jugadores);
    }

}
