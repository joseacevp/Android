package com.example.examen23.ui.detalles;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.examen23.ConexionSqlLite;
import com.example.examen23.Contacto;
import com.example.examen23.Utilidades;
import com.example.examen23.databinding.FragmentDetallesBinding;
import com.example.examen23.ui.contactos.ContactosViewModel;

import java.util.ArrayList;

public class DetallesFragment extends Fragment {

    private DetallesViewModel mViewModel;
    static final int SELECCIONAR_CONTACTO = 1;
    private FragmentDetallesBinding binding;
    ContactosViewModel contactosViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        contactosViewModel = new ViewModelProvider(requireActivity()).get(ContactosViewModel.class);

        Contacto contacto = contactosViewModel.getContacto();
        if (contacto != null) {
            // Aquí puedes usar el contacto como desees
            binding.textoFechaDetalle.setText("Fecha de Nacimiento: " + contacto.getFecha());
            binding.textoNombreDetalle.setText(contacto.getNombre());
            if (contacto.getFoto() != null) {
                binding.fotoDetalle.setImageURI(Uri.parse(contacto.getFoto()));
            } else {
                binding.fotoDetalle.setImageResource(android.R.drawable.ic_menu_camera);
            }

        } else {
            Toast.makeText(getContext(), "sin datos", Toast.LENGTH_SHORT).show();
        }

        binding.botonEditarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri contactUri = contacto.getUri();
                //ver el contacto -> el intent recibe además,
                // el URI del contacto seleccionado
                startActivity(new Intent(Intent.ACTION_VIEW, contactUri));
            }
        });

        //carga la lista de telefonos del contacto en un spinner
        //forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, obtenerTelefonos(contacto.getUri()));
        binding.spinnerTelefonos.setAdapter(adapter);
        binding.spinnerTelefonos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contacto.setTelefono(parent.getItemAtPosition(position).toString());
                contactosViewModel.setContacto(contacto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //boton grabar datos
        binding.botonGuardarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarBD();
            }
        });
        return root;
    }

    private void actualizarBD() {
        ConexionSqlLite   conexion = new ConexionSqlLite(getContext(), "BaseDatosMisCumples", null, 1);
        SQLiteDatabase bd = conexion.getReadableDatabase();
        Log.i("info", "Abierta base datos para actualizar");
        String[] consultaParametros = {
                binding.textoNombreDetalle.getText().toString()};//parametros de la consulta, pueden ser varios
        ContentValues values = new ContentValues();
        values.put(Utilidades.MENSAJE,binding.textMultiLineMensajeDetalle.getText().toString());

        if ( binding.checkSmsDetalle.isChecked()) {
            values.put(Utilidades.TIPONOTIF,"Enviar SMS");
        }else {
            values.put(Utilidades.TIPONOTIF,"Enviar Notificacion");
        }
             //sustituye a la consulta SQL select  nombre,telefono from usuario
        bd.update(Utilidades.TABLA_MISCUMPLES,values,Utilidades.NOMBRE +" =?",consultaParametros);
        Toast.makeText(getContext(),"Datos Actualizados",Toast.LENGTH_SHORT).show();
        bd.close();
    }

    private ArrayList<String> obtenerTelefonos(Uri contactUri) {
        ArrayList<String> listaTelefonos= new ArrayList<>();
        Cursor cursor = getContext().getContentResolver().query(contactUri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            cursor.close();

            Cursor phoneCursor = getContext().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{contactId},
                    null
            );

            if (phoneCursor != null) {
                while (phoneCursor.moveToNext()) {
                    String telefono = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    // carga los telefonos en una lista
                   listaTelefonos.add(telefono);
                }
                phoneCursor.close();
            }
        }
        return listaTelefonos;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECCIONAR_CONTACTO) {
            if (resultCode == RESULT_OK) {
                // El usuario ha seleccionado un contacto
                // Puedes manejar el contacto seleccionado aquí
                Toast.makeText(getContext(), "Contacto seleccionado", Toast.LENGTH_SHORT).show();
                Uri contactUri = data.getData();
                //ver el contacto -> el intent recibe además,
                // el URI del contacto seleccionado
                startActivity(new Intent(Intent.ACTION_VIEW, contactUri));
            } else {
                // El usuario ha cancelado la selección
                Toast.makeText(getContext(), "Selección cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}