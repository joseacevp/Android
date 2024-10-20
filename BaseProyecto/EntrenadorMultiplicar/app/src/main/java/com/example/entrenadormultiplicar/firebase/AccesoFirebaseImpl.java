package com.example.entrenadormultiplicar.firebase;

import androidx.annotation.NonNull;

import com.example.entrenadormultiplicar.gestionusuarios.Jugador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccesoFirebaseImpl implements AccesoFirebase{
    private final DatabaseReference databaseReference;

    public AccesoFirebaseImpl() {
        // Inicializar la referencia a la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Jugadores");
    }

    @Override
    public void guardarDato(Jugador jugador) {
        if (jugador != null) {
            String id = databaseReference.push().getKey();
            if (id != null) {
                databaseReference.child(id).setValue(jugador);
            }
        }
    }

    @Override
    public void cargarDatos(OnDataLoadedCallback callback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Jugador> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Jugador dato = dataSnapshot.getValue(Jugador.class);
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
