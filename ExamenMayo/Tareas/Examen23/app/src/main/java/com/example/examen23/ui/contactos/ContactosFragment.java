package com.example.examen23.ui.contactos;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen23.ConexionSqlLite;
import com.example.examen23.Contacto;
import com.example.examen23.Utilidades;
import com.example.examen23.databinding.FragmentContactosBinding;

import java.util.ArrayList;

public class ContactosFragment extends Fragment {

    private FragmentContactosBinding binding;
    private ArrayList<Contacto> listaContactos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentContactosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // optenemos la lista de contactos del telefono
        listaContactos = llenarContactos();
        for (int i = 0 ; i<listaContactos.size(); i++){
            //creamos la base de datos con la lista de contactos del telefono
            crearTablaBaseDatos(listaContactos.get(i));
        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void crearTablaBaseDatos(Contacto contacto) {
        ConexionSqlLite conn = new ConexionSqlLite(getContext(),
                "BaseDatosMisCumples", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        //
        //    //CREATE TABLE IF NOT EXISTS miscumples(
        //    // ID integer,
        //    // TipoNotif char(1),
        //    // Mensaje VARCHAR(160),
        //    // Telefono VARCHAR(15),
        //    // FechaNacimiento VARCHAR(15),
        //    // Nombre VARCHAR(128)
        //    //);
        try {
            String insert = "INSERT INTO " + Utilidades.TABLA_MISCUMPLES
                    + " ( "
                    + Utilidades.TIPONOTIF + ","
                    + Utilidades.MENSAJE + ","
                    + Utilidades.TELEFONO + ","
                    + Utilidades.FECHANACIMIENTO + ","
                    + Utilidades.TELEFONO + ","
                    + Utilidades.NOMBRE
                    + ")"
                    + "VALUES ( '" + contacto.getTipo()+ "','"
                    + contacto.getNotificacion() + "','"
                    + contacto.getTelefono() + "','"
                    + contacto.getFecha() + "','"
                    + contacto.getTelefono() + "','"
                    + contacto.getNombre() + "')";

            db.execSQL(insert);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getContext().getApplicationContext(), "Fallo al registrar partida.", Toast.LENGTH_SHORT).show();

        }

    }

    @SuppressLint("Range")
    private ArrayList<Contacto> llenarContactos() {
        String proyeccion[] = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID,

        };
        //lista de contactos reales del telefono
        ArrayList<Contacto> lista_contactos= new ArrayList<>();
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
                lista_contactos.add(contacto);
                // Si tiene teléfono lo agregamos a la lista de contactos

            } // FIN while
        } // FIN if

        cursor.close();
        return lista_contactos;
    }

}