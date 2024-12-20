package com.example.gestiondeinventario.ui.firebase;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccesoFirebaseImpl implements AccesoFirebaseMateriales, AccesoFirebaseTrabajos {
    private final DatabaseReference databaseReferenceMateriales;
    private final DatabaseReference databaseReferenceTrabajos;

    public AccesoFirebaseImpl() {
        // Inicializar la referencia a la base de datos de Firebase
        databaseReferenceMateriales = FirebaseDatabase.getInstance().getReference("Materiales");
        databaseReferenceTrabajos = FirebaseDatabase.getInstance().getReference("Trabajos");
    }

    @Override
    public void guardarDatoMateriales(Materiales materiales, Context context) {
        if (materiales != null) {
            cargarDatosMateriales(new OnDataLoadedCallbackMateriales() {
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
                    if (existe == false) {//si no existe se inserta en la base de datos
                        String id = databaseReferenceMateriales.push().getKey();
                        if (id != null) {
                            databaseReferenceMateriales.child(materiales.getCodigo()).setValue(materiales);
                        }
                    } else {
                        mostrarDialogo("ERROR ", "El Codigo Utilizado Ya Existe", context);
                    }
                }

                @Override
                public void onError(String error) {
                    // En el caso error al cargar datos
                    mostrarDialogo("ERROR ", "Fallo la carga de datos", context);
                }
            });
        }
    }

    @Override
    public void cargarDatosMateriales(OnDataLoadedCallbackMateriales callback) {
        databaseReferenceMateriales.addListenerForSingleValueEvent(new ValueEventListener() {
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

    @Override
    public void actualizarCantidadMateriales(String cantidad, String codigo, Context context) {
        // Buscar el material en Firebase por su código
        databaseReferenceMateriales.child(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Obtener el material actual
                    Materiales material = snapshot.getValue(Materiales.class);
                    if (material != null) {
                        // Actualizar la cantidad
                        String cantidadActual = material.getCantidad();
                        if (Integer.parseInt(cantidadActual) + Integer.parseInt(cantidad) >= 0) {
                            material.setCantidad(String.valueOf(Integer.parseInt(cantidadActual) + Integer.parseInt(cantidad))); //
                            databaseReferenceMateriales.child(codigo).setValue(material)
                                    .addOnSuccessListener(aVoid ->
                                            Toast.makeText(context, "Cantidad actualizada exitosamente.", Toast.LENGTH_SHORT).show())
                                    .addOnFailureListener(e ->
                                            mostrarDialogo("ERROR", "No se pudo actualizar la cantidad: " + e.getMessage(), context));
                        } else {
                            mostrarDialogo("ERROR EN CANTIDADES INDICADAS", "Falte de Stock.La cantidad indicada es ERRONEA", context);
                        }
                    }
                } else {
                    // Si el material no existe
                    mostrarDialogo("ERROR", "El material con el código especificado no existe.", context);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mostrarDialogo("ERROR", "Error al buscar el material: " + error.getMessage(), context);
            }
        });
    }

    private void mostrarDialogo(String titulo, String mensaje, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Configurar el título y el mensaje
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        // Configurar botón positivo (Aceptar)
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Aceptar"
                dialog.dismiss(); // Cerrar el diálogo
            }
        });
        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void guardarDatoTrabajos(Trabajos trabajos, Context context) {
        if (trabajos != null) {
            cargarDatosTrabajos(new OnDataLoadedCallbackTrabajos() {
                @Override
                public void onDataLoaded(List<Trabajos> lista) {
                    boolean existe = false;
                    for (Trabajos trab : lista) {
                        // Comprobamos si existe
                        if (trab.getOrdenTrabajo().equals(trabajos.getOrdenTrabajo())) {
                            existe = true;
                            break;
                        }
                    }
                    if (existe == false) {//si no existe se inserta en la base de datos
                        String id = databaseReferenceTrabajos.push().getKey();
                        if (id != null) {
                            databaseReferenceTrabajos.child(trabajos.getOrdenTrabajo()).setValue(trabajos);
                        }
                    } else {
                        mostrarDialogo("ERROR ", "El Codigo Utilizado Ya Existe", context);
                    }
                }

                @Override
                public void onError(String error) {
                    // En el caso error al cargar datos
                    mostrarDialogo("ERROR ", "Fallo la carga de datos", context);
                }
            });
        }
    }

    @Override
    public void cargarDatosTrabajos(OnDataLoadedCallbackTrabajos callback) {
        databaseReferenceTrabajos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Trabajos> lista = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Trabajos dato = dataSnapshot.getValue(Trabajos.class);
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
