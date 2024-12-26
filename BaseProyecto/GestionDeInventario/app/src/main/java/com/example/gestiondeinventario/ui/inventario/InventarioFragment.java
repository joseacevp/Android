package com.example.gestiondeinventario.ui.inventario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ArrayList<Materiales> listaMateriales;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accesoFirebaseMateriales = new AccesoFirebaseImpl();
        listaMateriales = new ArrayList<>();
        inventarioViewModel = new ViewModelProvider(this).get(InventarioViewModel.class);
        //fireBase
        // Cargar datos desde Firebase y actualizar la lista
        accesoFirebaseMateriales.cargarDatosMateriales(new AccesoFirebaseMateriales.OnDataLoadedCallbackMateriales() {
            @Override
            public void onDataLoaded(List<Materiales> materiales) {
                if (materiales.isEmpty()) {
                    cargarMaterialesDesdeFirebase();
                }
                listaMateriales.addAll(cargarMaterialesDesdeFirebase());
                //lista.clear();
                //lista.addAll(materiales);
                inventarioAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), "Fallo al cargar datos de Firebase: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        iniciarRecycler();
        return root;
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
                    listaMateriales.add(material);
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
//
}