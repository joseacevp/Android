package com.example.examenmayo24.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<List<Jugador>> jugadoresSeleccionados = new MutableLiveData<>();


    public LiveData<List<Jugador>> getJugadoresSeleccionados() {
        return jugadoresSeleccionados;
    }

    public void setJugadoresSeleccionados(List<Jugador> jugadores) {
        jugadoresSeleccionados.setValue(jugadores);

    }
    //////////////////jugador
    private MutableLiveData<Jugador> jugadorSeleccionado = new MutableLiveData<>();


    public LiveData<Jugador> getJugadorSeleccionado() {
        return jugadorSeleccionado;
    }

    public void setJugadorSeleccionado(Jugador jugador) {
        jugadorSeleccionado.setValue(jugador);

    }
}