package com.example.gestiondeinventario.ui.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccesoFirebaseImpl implements AccesoFirebase {
    private final DatabaseReference databaseReference;

    public AccesoFirebaseImpl() {
        // Inicializar la referencia a la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Materiales");
    }

    @Override
    public void guardarDato(Materiales materiales) {
        if (materiales != null) {
            cargarDatos(new OnDataLoadedCallback() {
                @Override
                public void onDataLoaded(List<Materiales> lista) {
                    boolean existe = false;
                    for (Materiales mate : lista) {
                        // Comprobamos si existe
                        if (mate.getCodigo().equals(materiales.getCodigo())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {//si existe
                        String id = databaseReference.push().getKey();
                        if (id != null) {
                            databaseReference.child(materiales.getCodigo()).setValue(materiales);
                        }
                    } else {
                        // Aquí puedes manejar el caso en que el material ya existe
                        System.out.println("El material ya existe y no se insertará.");
                    }
                }

                @Override
                public void onError(String error) {
                    // Manejar el error al cargar datos
                    System.out.println("Error al cargar datos: " + error);
                }
            });
        }
    }

    @Override
    public void cargarDatos(OnDataLoadedCallback callback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Materiales> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Materiales dato = dataSnapshot.getValue(Materiales.class);
                    lista.add(dato);
                }
                callback.onDataLoaded(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError("Error al cargar datos: " + error.getMessage());
            }
        });
    }
}
