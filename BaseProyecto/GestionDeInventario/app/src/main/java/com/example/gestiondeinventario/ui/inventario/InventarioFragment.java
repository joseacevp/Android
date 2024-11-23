package com.example.gestiondeinventario.ui.inventario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.databinding.FragmentInventarioBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebase;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;

import java.util.ArrayList;
import java.util.List;

public class InventarioFragment extends Fragment {

    private FragmentInventarioBinding binding;
    private AccesoFirebase accesoFirebase;
    private RecyclerView recyclerView;
    private InventarioViewModel  inventarioViewModel ;
    private InventarioAdapter inventarioAdapter;
    private  List<Materiales> lista;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInventarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        accesoFirebase = new AccesoFirebaseImpl();
        inventarioViewModel = new ViewModelProvider(this).get(InventarioViewModel.class);
        //fireBase
        // Cargar datos desde Firebase y actualizar la lista
        accesoFirebase.cargarDatos(new AccesoFirebase.OnDataLoadedCallback() {
            @Override
            public void onDataLoaded(List<Materiales> materiales) {
                if (materiales.isEmpty()){
                    llenarListaMateriales();
                }
                lista.clear();
                lista.addAll(materiales);
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


    private void iniciarRecycler() {
        recyclerView = binding.recyclerInventario;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        inventarioAdapter = new InventarioAdapter(llenarListaMateriales(),inventarioViewModel);
        recyclerView.setAdapter(inventarioAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private List<Materiales> llenarListaMateriales() {
        lista = new ArrayList<>();

        lista.add(new Materiales("SIN DATOS","0000","SIN DATOS DE LOCALIZACION","SIN DATOS DE USO ",R.drawable.ic_home_black_24dp,true));
        //ublic Materiales(String nombre, String codigo, String localizacion, String uso, int foto, Boolean isSelected) {

        return lista;
    }

}