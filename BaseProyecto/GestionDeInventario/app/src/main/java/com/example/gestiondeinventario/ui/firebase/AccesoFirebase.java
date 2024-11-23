package com.example.gestiondeinventario.ui.firebase;



import java.util.List;

public interface AccesoFirebase {
    void guardarDato(Materiales materiales);
    void cargarDatos(OnDataLoadedCallback callback);

    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallback {
        void onDataLoaded(List<Materiales> materiales);
        void onError(String error);
    }
}
