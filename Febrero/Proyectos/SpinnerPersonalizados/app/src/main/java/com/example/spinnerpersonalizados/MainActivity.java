package com.example.spinnerpersonalizados;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

//crea un Spinner personalizado en la actividad main encontramos un spinner el cual carga los
//datos desde otro fichero xml llamado linearspinner que sirbe de plantilla para visualizar los datos
//del spinner el cula costa de titulo imagen y descripcion.
public class MainActivity extends AppCompatActivity {

    String[] ciudades = {"Toledo",
            "Ciudad Real",
            "Albacete",
            "Cuenca",
            "Guadalajara"};

    String[] descripcion = {"La ciudad Imperial",
            "Qué gran ciudad",
            "Ciudad gastronomica",
            "Ciudad Encantada",
            "Ciudad colgante"};

    int imagenes[] = {R.drawable.toledo,
            R.drawable.ciudad_real,
            R.drawable.albacete,
            R.drawable.cuenca,
            R.drawable.guadalajara};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner selectorCiudades = findViewById(R.id.spinner);
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(this,
                R.layout.lineaspinner, ciudades);
        selectorCiudades.setAdapter(a);

        selectorCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //codigo para gestionar lo que ocurre cuando seleccionas una opcion del Spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public class AdaptadorPersonalizado extends ArrayAdapter {


        public AdaptadorPersonalizado(@NonNull Context context,
                                      int textViewResourceId,
                                      @NonNull Object[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position,
                                    @Nullable View convertView,
                                    @NonNull ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }
        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            return crearFilaPersonalizada(position, convertView, parent);
        }

        private View crearFilaPersonalizada(int position,
                                            View convertView,
                                            ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.lineaspinner,
                    parent, false);

            TextView nombre = miFila.findViewById(R.id.nombre);
            nombre.setText(ciudades[position]);

            TextView descipcion = miFila.findViewById(R.id.descripcion);
            descipcion.setText(descripcion[position]);

            ImageView imagen = miFila.findViewById(R.id.imagenCiudad);
            imagen.setImageResource(imagenes[position]);
            return miFila;
        }
    }


}