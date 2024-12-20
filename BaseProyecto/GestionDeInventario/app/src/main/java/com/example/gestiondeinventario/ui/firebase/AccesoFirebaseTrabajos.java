package com.example.gestiondeinventario.ui.firebase;

import android.content.Context;

import java.util.List;

public interface AccesoFirebaseTrabajos {
    void guardarDatoTrabajos(Trabajos trabajos, Context context);

    void cargarDatosTrabajos(AccesoFirebaseTrabajos.OnDataLoadedCallbackTrabajos callback);

    // Definir una interfaz de callback para manejar los datos cargados
    interface OnDataLoadedCallbackTrabajos {
        void onDataLoaded(List<Trabajos> trabajos);

        void onError(String error);
    }
}
