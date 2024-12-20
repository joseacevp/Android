package com.example.gestiondeinventario.ui.firebase;

import android.content.Context;

import java.util.List;

public interface AccesoFirebaseMateriales {
    void guardarDatoMateriales(Materiales materiales, Context context);

    void cargarDatosMateriales(OnDataLoadedCallbackMateriales callback);

    void actualizarCantidadMateriales(String cantidad, String codigo, Context context);


    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallbackMateriales {
        void onDataLoaded(List<Materiales> materiales);

        void onError(String error);
    }


}
