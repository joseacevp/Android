package com.example.gestiondeinventario.ui.inventario;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.databinding.FragmentInventarioBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseMateriales;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventarioFragment extends Fragment {

    private FragmentInventarioBinding binding;
    private AccesoFirebaseMateriales accesoFirebaseMateriales;
    private RecyclerView recyclerView;
    private InventarioViewModel inventarioViewModel;
    private InventarioAdapter inventarioAdapter;
    private ArrayList<Materiales> listaMateriales = new ArrayList<>();
    private String codigo;
    private List<Materiales> materiales = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        accesoFirebaseMateriales = new AccesoFirebaseImpl();

        inventarioViewModel = new ViewModelProvider(this).get(InventarioViewModel.class);
        //fireBase
        // Cargar datos desde Firebase y actualizar la lista
        accesoFirebaseMateriales.cargarDatosMateriales(new AccesoFirebaseMateriales.OnDataLoadedCallbackMateriales() {
            @Override
            public void onDataLoaded(List<Materiales> materialesCargados) {
                if (materialesCargados.isEmpty()) {
                    listaMateriales.addAll(cargarMaterialesDesdeFirebase());
                } else {
                    listaMateriales.clear();
                    listaMateriales.addAll(materialesCargados);
                }
                inventarioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                mostrarDialogo("Error", "Fallo al cargar datos de Firebase ", getContext());
            }
        });


        iniciarRecycler();
        binding.buttonBuscarInve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarMateriales(listaMateriales);
                limpiarCampo();
            }
        });
        return root;
    }

    private void limpiarCampo() {
        binding.editTextBuscarInve.setText("");
    }


    private List<Materiales> cargarMaterialesDesdeFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Materiales");
        List<Materiales> listaMateriales = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMateriales.clear(); // Limpia la lista antes de agregar nuevos datos
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Materiales material = dataSnapshot.getValue(Materiales.class);
                    if (material != null) {
                        listaMateriales.add(material);
                        // Registrar para depuración
                        Log.d("FirebaseCarga", "Material cargado: " + material.getCodigo());
                    }
                }
                // Actualiza el adaptador con los nuevos datos
                inventarioAdapter.setListaMateriales(listaMateriales);
                inventarioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });

        return listaMateriales;
    }

    private void iniciarRecycler() {
        recyclerView = binding.recyclerInventario;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        inventarioAdapter = new InventarioAdapter(listaMateriales, inventarioViewModel);
        recyclerView.setAdapter(inventarioAdapter);
        cargarMaterialesDesdeFirebase(); // Carga los datos después de configurar el adaptador
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

    private void buscarMateriales(List<Materiales> materiales) {
        // Obtener el código ingresado en el campo de texto
        String codigo = binding.editTextBuscarInve.getText().toString().trim();
        // Validar que el campo no esté vacío
        if (codigo.isEmpty()) {
            mostrarDialogo("Advertencia", "Por favor, ingresa un código para buscar.", getContext());
            return;
        }
        // Registrar los códigos en la lista para depuración
        Log.d("BuscarMateriales", "Código buscado: " + codigo);
        for (Materiales material : materiales) {
            Log.d("BuscarMateriales", "Código en lista: " + material.getCodigo());
        }
        // Buscar el material en la lista
        Materiales materialEncontrado = null;
        for (Materiales material : materiales) {
            if (material.getCodigo() != null && material.getCodigo().trim().equalsIgnoreCase(codigo)) {
                materialEncontrado = material;
                break;
            }
        }
        // Mostrar el resultado
        if (materialEncontrado != null) {
            mostrarDialogoResultado(getContext(), materialEncontrado);
        } else {
            // Si no se encontró el material, muestra un mensaje de error
            mostrarDialogo("Error", "No se encontró un material con el código: " + codigo, getContext());
        }
    }

    private void mostrarDialogoResultado(Context context, Materiales matarial) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View dialogoDetalle = layoutInflater.inflate(R.layout.dialog_detalles_material, null);
        // Crea el diálogo
        // Encuentra las vistas dentro del diseño personalizado
        ImageView imageView = dialogoDetalle.findViewById(R.id.imageViewFotoDetalle);
        TextView dialogNombre = dialogoDetalle.findViewById(R.id.textViewNombreItemDet);
        TextView dialogCodigo = dialogoDetalle.findViewById(R.id.textViewCodItemDet);
        TextView dialogLocalizacion = dialogoDetalle.findViewById(R.id.textViewLocaliItemDet);
        TextView dialogUso = dialogoDetalle.findViewById(R.id.textViewUsoItemDet);
        TextView dialogCantidad = dialogoDetalle.findViewById(R.id.textViewCantItemDet);
        EditText ediIncreCantidad = dialogoDetalle.findViewById(R.id.editTextIncreCantDet);
        // Establece los valores en las vistas del diálogo
        // Cargar imagen desde Firebase Storage usando Glide
        Glide.with(context)
                .load(matarial.getFotoUri()) // URL de Firebase Storage
                .placeholder(R.drawable.ic_dashboard_black_24dp) // Imagen de carga
                .into(imageView);
        dialogNombre.setText("Nombre: " + matarial.getNombre());
        dialogCodigo.setText("Código: " + matarial.getCodigo());
        dialogLocalizacion.setText("Localización: " + matarial.getLocalizacion());
        dialogUso.setText("Uso: " + matarial.getUso());
        dialogCantidad.setText("Cantidad disponible: " + matarial.getCantidad());
        // Configura y muestra el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogoDetalle)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    // Acción al presionar "Aceptar" añade la cantidad de material a la base de datos
                    if (ediIncreCantidad.getText().length() != 0) {
                        accesoFirebaseMateriales.actualizarCantidadMateriales(ediIncreCantidad.getText().toString(), matarial.getCodigo(), context);
                    }
                    dialog.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
//                Toast.makeText(context, "SELECCIONADO: " + materiales.getNombre(), Toast.LENGTH_SHORT).show();
    }


}