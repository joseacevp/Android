package com.example.gestiondeinventario.ui.firebase;

import android.content.Context;
import android.text.Editable;

import java.util.List;

public interface AccesoFirebase {
    void guardarDato(Materiales materiales, Context context);
    void cargarDatos(OnDataLoadedCallback callback);

    void actualizarCantidad(String cantidad,String codigo, Context context);


    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallback {
        void onDataLoaded(List<Materiales> materiales);
        void onError(String error);
    }
}
