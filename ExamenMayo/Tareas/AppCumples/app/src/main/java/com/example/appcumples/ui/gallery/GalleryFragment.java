package com.example.appcumples.ui.gallery;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcumples.Contacto;
import com.example.appcumples.databinding.FragmentGalleryBinding;
import com.example.appcumples.ui.gallery.GalleryViewModel;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    GalleryViewModel galleryViewModel;
    ArrayList<Contacto> listaContactosTelefono = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //datos de los contactos del telefono almacenados en viewModel set añade los datos
        galleryViewModel = new ViewModelProvider(requireActivity()).get(GalleryViewModel.class);
        galleryViewModel.setListaContactosTelefono(llenarContactos());

        //get consulta los datos de los contactos del telefono almacendos en viewModel
        galleryViewModel.getListaContactosTelefono().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contacto>>() {
            @Override
            public void onChanged(ArrayList<Contacto> contactos) {
                if (contactos != null && contactos.size() > 0) {
                    listaContactosTelefono = contactos;
                    //comprobar datos por pantalla
//                    for (int i = 0 ; i<listaContactosTelefono.size(); i++){
//                        System.out.println(listaContactosTelefono.get(i).getNombre());
//                    }
                    crearDatosBaseDatos(listaContactosTelefono);
                } else {
                    Toast.makeText(getContext(), "¡No Hay  Aun!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return root;
    }
//crea y añade los datos del los contactos del telefono en la base de datos
    private void crearDatosBaseDatos(ArrayList<Contacto> contactos) {
        for (int i = 0 ; i<contactos.size(); i++){
            System.out.println(contactos.get(i).getNombre());
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//consulta y recupera en una lista los contactos del telefono
    @SuppressLint("Range")
    private ArrayList<Contacto> llenarContactos() {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID,

        };
        //lista de contactos reales del telefono
        ArrayList<Contacto> lista_contactos = new ArrayList<>();
        //contenedor de resultados consulta a servicios de movil
        ContentResolver contentResolver = getContext().getContentResolver();
        //cursor para optener los datos basicos de contacto real
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                proyeccion, null, null, null);
        // Con el cursor, recorrer la lista de contactos extraída, agregando los elementos a un ArrayList
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Obtener el nombre del contacto
                String nombreContacto = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
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
                // Obtener el id de la foto del contacto
                String fotoId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                // Inicializar la URI de la foto
                String fotoUri = null;
                // Verificar si el contacto tiene foto
                if (fotoId != null) {
                    // Obtener la URI de la foto del contacto
                    Uri fotoUriContacto = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, Long.parseLong(fotoId));
                    // Convertir la URI en una cadena y almacenarla en fotoUri
                    fotoUri = fotoUriContacto.toString();
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
                //nombre,fecha,notificacion,foto,telefono,tipo;
                Contacto contacto = new Contacto();
                contacto.setFoto(fotoUri);
                contacto.setFecha(fechaNacimiento);
                contacto.setNombre(nombreContacto);
                contacto.setTelefono(telefono);


                //uri del contacto para poder acceder al editor de detalles del contacto de android
                Uri uriContacto = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactoId));
                contacto.setUri(uriContacto);

                lista_contactos.add(contacto);
                // Si tiene teléfono lo agregamos a la lista de contactos
//
            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos;
    }
}