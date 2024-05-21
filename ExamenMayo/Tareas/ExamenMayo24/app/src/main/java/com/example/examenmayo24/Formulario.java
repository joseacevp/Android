package com.example.examenmayo24;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examenmayo24.databinding.FragmentFormularioBinding;
import com.example.examenmayo24.ui.gallery.GalleryViewModel;
import com.example.examenmayo24.ui.gallery.Jugador;


public class Formulario extends Fragment {

    private FormularioViewModel mViewModel;
    GalleryViewModel galleryViewModel;

    private FragmentFormularioBinding binding;

    public static Formulario newInstance() {
        return new Formulario();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        galleryViewModel.getJugadorSeleccionado().observe(getViewLifecycleOwner(), new Observer<Jugador>() {
            @Override
            public void onChanged(Jugador jugador) {
                System.out.println(jugador.getNombre());

            }
        });
        binding.botonGuardarDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "guadada base datos", Toast.LENGTH_SHORT).show();
                Jugador jugadorDB = new  Jugador("Enrrique Similo", "Delantero Centro", R.drawable.carita01, R.drawable.imagen_estrella);


                crearTablaBaseDatos(jugadorDB);
            }
        });

      
        return view;
    }


    private void crearTablaBaseDatos(Jugador contacto) {
        ConexionSqlLite conn = new ConexionSqlLite(getContext(),
                "BaseDatosMisCumples", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        // Verificar si el contacto ya existe en la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ACTUACION +
                        " WHERE " + Utilidades.NOMBRE + " = ? OR " + Utilidades.NOMBRE + " = ?",
                new String[]{contacto.getNombre(), contacto.getNombre()});
        if (cursor.getCount() > 0) {
            // Si el cursor tiene resultados, el contacto ya existe en la base de datos
//            Toast.makeText(getContext(), "El contacto ya existe en la base de datos", Toast.LENGTH_SHORT).show();
        } else {
            // Si el cursor está vacío, el contacto no existe en la base de datos, por lo que se puede insertar
            try {
                String insert = "INSERT INTO " + Utilidades.TABLA_ACTUACION + " ( " +
                        Utilidades.NOMBRE + ", " +
                        Utilidades.NOMBRE + ", " +
                        Utilidades.NOMBRE + ", " +
                        Utilidades.NOMBRE + ", " +
                        Utilidades.NOMBRE +
                        ") VALUES ( '" +
                        contacto.getNombre() + "', '" +
                        contacto.getNombre() + "', '" +
                        contacto.getNombre() + "', '" +
                        contacto.getNombre() + "', '" +
                        contacto.getNombre() + "')";
                db.execSQL(insert);
                // Cerrar el cursor
                cursor.close();
//                Toast.makeText(getContext(), "Contacto insertado correctamente en la base de datos", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getContext().getApplicationContext(), "Fallo al registrar el contacto", Toast.LENGTH_SHORT).show();
            }
            db.close();
            conn.close();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormularioViewModel.class);
        // TODO: Use the ViewModel
    }

}