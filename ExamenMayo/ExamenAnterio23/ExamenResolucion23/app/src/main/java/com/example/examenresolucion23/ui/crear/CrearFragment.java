package com.example.examenresolucion23.ui.crear;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.examenresolucion23.AdaptadorAleatorios;
import com.example.examenresolucion23.AdapterDatos;
import com.example.examenresolucion23.Contacto;
import com.example.examenresolucion23.MyDataSet;
import com.example.examenresolucion23.databinding.FragmentCrearBinding;

import java.util.ArrayList;


public class CrearFragment extends Fragment {

    private FragmentCrearBinding binding;
    private ArrayList<Contacto> listaContactoF ;
    MyDataSet lista = new MyDataSet(25);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CrearViewModel galleryViewModel =
                new ViewModelProvider(this).get(CrearViewModel.class);

        binding = FragmentCrearBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listaContactoF = lista.ITEMS;
        System.out.println(listaContactoF.size());
        binding.recyclerAleatorios.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        //15. enviamos la lista con los datos al adaptador
        AdaptadorAleatorios adapter = new AdaptadorAleatorios(listaContactoF, getContext());
        //16. indicamos al recycleView que
        binding.recyclerAleatorios.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}