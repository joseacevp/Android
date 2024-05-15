package com.example.examenresolucion23.ui.mostrar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.examenresolucion23.AdaptadorAleatorios;
import com.example.examenresolucion23.AdapterDatos;
import com.example.examenresolucion23.ConexionSqlLite;
import com.example.examenresolucion23.Contacto;
import com.example.examenresolucion23.ContactoReal;
import com.example.examenresolucion23.Utilidades;
import com.example.examenresolucion23.databinding.FragmentMostrarBinding;

import java.util.ArrayList;

public class MostrarFragment extends Fragment {

    private FragmentMostrarBinding binding;
    private ArrayList<ContactoReal> listaContactosReal;
    private ArrayList<Contacto> listaAleatoriso;
    private ConexionSqlLite conexion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MostrarViewModel slideshowViewModel =
                new ViewModelProvider(this).get(MostrarViewModel.class);

        binding = FragmentMostrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        conexion = new ConexionSqlLite(getContext().getApplicationContext(), "BaseDatos23", null, 1);
        //contactos reales
        consultaBDContactoReal();

        binding.recyclerReal.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        //15. enviamos la lista con los datos al adaptador
        AdapterDatos adapter = new AdapterDatos(listaContactosReal, getContext());
        //16. indicamos al recycleView que
        binding.recyclerReal.setAdapter(adapter);

        //contactos aleatorios
        consultaBDContactoAleatorio();

        binding.recyclerAle.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL,false));
        AdaptadorAleatorios adaptadorAleatorios = new AdaptadorAleatorios(listaAleatoriso,getContext());
        binding.recyclerAle.setAdapter(adaptadorAleatorios);

        return root;
    }

    private void consultaBDContactoAleatorio() {
        //abre la base de datos para consultar
        SQLiteDatabase db = conexion.getReadableDatabase();
        Log.i("info", "Apertura base de datos para consultar");

        //instancia de usuario para recibir los datos de la base de datos
        Contacto contacto = null;
        //instancia de la lista de usuarios para recibir los datos de los usuarios de la base datos
        listaAleatoriso = new ArrayList<Contacto>();

        //select * from usuarios
        //Cursor para consultar la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                Utilidades.TABLA_ALEATORIOS, null);

        //recorremos el cursor para optener los datos obtenidos
        while (cursor.moveToNext()) {
            contacto = new Contacto();
            contacto.setId(cursor.getString(0));
            contacto.setNombre(cursor.getString(1));
            contacto.setApellidos(cursor.getString(2));
            contacto.setDni(cursor.getString(3));

            //control de datos por consola

            Log.i("info", contacto.getNombre().toString());
            Log.i("info", contacto.getApellidos().toString());
            Log.i("info", contacto.getDni().toString());

            //añadimos los usuarios a la lista de usuarios
            listaAleatoriso.add(contacto);

        }
        db.close();
        //llamamos al metodo para pasar los datos de la lista de usuarios obtendia de la consulta
        //a la lista de String que usaremos para rellenar el Spinner
    }

    private void consultaBDContactoReal() {
        //abre la base de datos para consultar
        SQLiteDatabase db = conexion.getReadableDatabase();
        Log.i("info", "Apertura base de datos para consultar");

        //instancia de usuario para recibir los datos de la base de datos
        ContactoReal contactoReal = null;
        //instancia de la lista de usuarios para recibir los datos de los usuarios de la base datos
        listaContactosReal = new ArrayList<ContactoReal>();

        //select * from usuarios
        //Cursor para consultar la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                Utilidades.TABLA_CONTACTOS, null);

        //recorremos el cursor para optener los datos obtenidos
        while (cursor.moveToNext()) {
            contactoReal = new ContactoReal();

            contactoReal.setNombre(cursor.getString(0));
            contactoReal.setTelefono(cursor.getString(1));
            contactoReal.setFecha(cursor.getString(2));



            //control de datos por consola

            Log.i("info", contactoReal.getNombre().toString());
            Log.i("info", contactoReal.getTelefono().toString());
            Log.i("info", contactoReal.getFecha().toString());
            

            //añadimos los usuarios a la lista de usuarios
            listaContactosReal.add(contactoReal);

        }
        db.close();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}