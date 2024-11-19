package com.example.gestiondeinventario.ui.firebase;



import java.util.List;

public interface AccesoFirebase {
    void guardarDato(Materiales jugador);
    void cargarDatos(OnDataLoadedCallback callback);

    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallback {
        void onDataLoaded(List<Materiales> jugadores);
        void onError(String error);
    }
}
