package com.example.entrenadormultiplicar.firebase;

import com.example.entrenadormultiplicar.gestionusuarios.Jugador;

import java.util.List;

public interface AccesoFirebase {
    void guardarDato(Jugador jugador);
    void cargarDatos(OnDataLoadedCallback callback);

    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallback {
        void onDataLoaded(List<Jugador> jugadores);
        void onError(String error);
    }
}
