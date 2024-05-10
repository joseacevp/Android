package com.example.examenresolucion23.ui.cargar;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.example.examenresolucion23.ContactoReal;
import com.example.examenresolucion23.databinding.FragmentCargarBinding;

import java.util.ArrayList;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private boolean tengo_permisos = false;
    private final int PETICION_PERMISOS = 1;
    private ArrayList<ContactoReal> listaDatos, listaResultados;

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
        AdapterDatos adapter = new AdapterDatos(listaDatos, getContext());
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
    private ArrayList<ContactoReal> llenarContactos() {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID
        };
        //lista de contactos reales del telefono
        ArrayList<ContactoReal> lista_contactos_recycle = new ArrayList<>();
        //contenedor de resultados consulta a servicios de movil
        ContentResolver contentResolver = getActivity().getContentResolver();
        //cursor para optener los datos basicos de contacto real
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, null, null, null);
        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //Obtener el id del contacto
                String contactoId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                //fecha de nacimiento
                String fechaNacimiento = null;
                //cursor para obtener los detalles del contacto con la fecha de nacimiento o cumpleaños
                Cursor detalleCursor = contentResolver.query(
                        ContactsContract.Data.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Event.START_DATE},
                        ContactsContract.Data.CONTACT_ID + " = ? AND " +
                                ContactsContract.Data.MIMETYPE + " = ? AND " +
                                ContactsContract.CommonDataKinds.Event.TYPE + " = " +
                                ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY,
                        new String[]{contactoId, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE},
                        null
                );
                //si el cursor encuentra detalles con la fecha de nacimiento
                if (detalleCursor != null && detalleCursor.moveToFirst()) {
                    fechaNacimiento = detalleCursor.getString(detalleCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.DATA));
                    detalleCursor.close();
                }

                //foto de perfil
                String fotoId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                Uri fotoUri = null;
                if (fotoId != null) {
                    fotoUri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, Long.parseLong(fotoId));
                }

                //numero de telefono
                String telefono = null;
                String hasPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                // Verificar si el contacto tiene número de teléfono si es así hasPhoneNumber devuelve 1
                if (Integer.parseInt(hasPhoneNumber) > 0) {
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contactoId},
                            null
                    );

                    // Obtener el primer número de teléfono (puedes iterar si hay varios)
                    if (phoneCursor != null && phoneCursor.moveToFirst()) {
                        telefono = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        phoneCursor.close();
                    }
                }
                // Crear objeto Contacto y agregarlo a la lista
                ContactoReal contacto = new ContactoReal(name, telefono, fechaNacimiento, fotoUri);
                lista_contactos_recycle.add(contacto);
                // Si tiene teléfono lo agregamos a la lista de contactos

            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos_recycle;
    }

}