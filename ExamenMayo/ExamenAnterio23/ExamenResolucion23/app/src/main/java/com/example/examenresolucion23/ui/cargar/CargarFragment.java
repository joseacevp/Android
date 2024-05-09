package com.example.examenresolucion23.ui.cargar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.examenresolucion23.AdapterDatos;
import com.example.examenresolucion23.databinding.FragmentCargarBinding;

import java.util.ArrayList;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;
    private ArrayList<String> listaDatos, listaResultados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CargarViewModel homeViewModel =
                new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listaDatos = llenarContactos();
        binding.recyclerContactosReales.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        //15. enviamos la lista con los datos al adaptador
        AdapterDatos adapter = new AdapterDatos(listaDatos);
        //16. indicamos al recycleView que
        binding.recyclerContactosReales.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("Range")
    private ArrayList<String> llenarContactos() {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID
        };

        ArrayList<String> lista_contactos_recycle = new ArrayList<>();
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, null, null, null);
        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                // Si tiene teléfono lo agregamos a la lista de contactos
                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    lista_contactos_recycle.add(name);
                }
            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos_recycle;
    }

}