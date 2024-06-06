package com.example.juegomultiplicar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;


public class ConfiguracionFragment extends Fragment implements View.OnClickListener, DialogoFecha.OnFechaSeleccionada {
    //variables
    View view;
    String heroe, dificultad, fechaSeleccionada, tabla, numero_tabla_selec;

    private String[] heroes = {"Batman",
            "Hulk",
            "Iron Man",
            "Capitan America"};

    private String[] descripcion = {"Matches Malone\u200B El caballero de la noche El caballero oscuro Zurdo Knox\u200B El señor de la noche\u200B",
            "Hulk, El Increíble Hulk,\u200B El Justiciero, El Hombre Increíble",
            "Tony Stark, Iron Man (El Hombre de Hierro)",
            "Nómada, El Capitán, Cap, Steve Rogers, Steve"
    };

    private int imagenes[] = {R.drawable.batman,
            R.drawable.huld,
            R.drawable.iron,
            R.drawable.capi
    };


    public ConfiguracionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //metodo que almacena los datos de los campos de texto en un archivo .XML para compartirlos
    //con otra actividad de la aplicación.
    private void guardarPreferencias() {

        Random random = new Random();
        int numeroAleatorio = random.nextInt(10) + 1;

        SharedPreferences preferencias = getContext().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("heroe", heroe);
        editor.putString("dificultad", dificultad);
        editor.putString("fecha", fechaSeleccionada);
        editor.putString("tabla", numero_tabla_selec);
        editor.putString("aleatorio", String.valueOf(numeroAleatorio));

        editor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        //spinner selector de abatar
        Spinner selectorAbatar = view.findViewById(R.id.spinner_avatar);
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(getContext(),
                R.layout.linea_personajes, heroes);
        selectorAbatar.setAdapter(a);

        selectorAbatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //codigo para gestionar lo que ocurre cuando seleccionas una opcion del Spinner
                heroe = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getContext(), heroe, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //spinner selector de dificultad
        Spinner selectorDificultad = view.findViewById(R.id.spinner_dificultad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.array_dificultad,
                android.R.layout.simple_spinner_item);
        selectorDificultad.setAdapter(adapter);
        selectorDificultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dificultad = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getContext(), dificultad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //botones numericos
        Button boton_diez = view.findViewById(R.id.boton_tabla_diez);
        Button boton_uno = view.findViewById(R.id.boton_tabla_uno);
        Button boton_dos = view.findViewById(R.id.boton_tabla_dos);
        Button boton_tres = view.findViewById(R.id.boton_tabla_tres);
        Button boton_cuatro = view.findViewById(R.id.boton_tabla_cuatro);
        Button boton_cinco = view.findViewById(R.id.boton_tabla_cinco);
        Button boton_seis = view.findViewById(R.id.boton_tabla_seis);
        Button boton_siete = view.findViewById(R.id.boton_tabla_siete);
        Button boton_ocho = view.findViewById(R.id.boton_tabla_ocho);
        Button boton_nueve = view.findViewById(R.id.boton_tabla_nueve);
        Button boton_ok = view.findViewById(R.id.boton_tabla_ok);
        Button boton_aleatorio = view.findViewById(R.id.boton_tabla_aleatorio);
        boton_diez.setOnClickListener(this);
        boton_uno.setOnClickListener(this);
        boton_dos.setOnClickListener(this);
        boton_tres.setOnClickListener(this);
        boton_cuatro.setOnClickListener(this);
        boton_cinco.setOnClickListener(this);
        boton_seis.setOnClickListener(this);
        boton_siete.setOnClickListener(this);
        boton_ocho.setOnClickListener(this);
        boton_nueve.setOnClickListener(this);
        boton_ok.setOnClickListener(this);
        boton_aleatorio.setOnClickListener(this);

        //boton seleccionar fecha
        Button boton = view.findViewById(R.id.boton_fecha_config);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogoFecha fecha = new DialogoFecha();
                // Establece ConfiguracionFragment como el oyente para los eventos de fecha
                fecha.setFechaSeleccionadaListener(ConfiguracionFragment.this);
                fecha.show(getFragmentManager(), "fecha");

            }
        });

        return view;

    }

    //graba la configuración en fichero XML
    //graba las preferencias de configuración al cambiar de fragmento.
    @Override
    public void onPause() {
        super.onPause();
        guardarPreferencias();
    }


    //botonera para seleccionar el numero de tabla multiplicar
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.boton_tabla_diez:
                tabla = "10";
                break;
            case R.id.boton_tabla_uno:
                tabla = "1";
                break;
            case R.id.boton_tabla_dos:
                tabla = "2";
                break;
            case R.id.boton_tabla_tres:
                tabla = "3";
                break;
            case R.id.boton_tabla_cuatro:
                tabla = "4";
                break;
            case R.id.boton_tabla_cinco:
                tabla = "5";
                break;
            case R.id.boton_tabla_seis:
                tabla = "6";
                break;
            case R.id.boton_tabla_siete:
                tabla = "7";
                break;
            case R.id.boton_tabla_ocho:
                tabla = "8";
                break;
            case R.id.boton_tabla_nueve:
                tabla = "9";
                break;
            case R.id.boton_tabla_aleatorio:
                tabla = "aleatorio";
                break;
            case R.id.boton_tabla_ok:
                numero_tabla_selec = tabla;
                System.out.println(numero_tabla_selec);
                break;
        }

    }

    //control de los datos optenidos en la selección de la fecha
    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        fechaSeleccionada = fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR);
//        Toast.makeText(getContext(), fechaSeleccionada, Toast.LENGTH_SHORT).show();
    }

    //adaptador personalizado para el spinner de los heroes
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
            View miFila = inflater.inflate(R.layout.linea_personajes,
                    parent, false);

            TextView nombre = miFila.findViewById(R.id.nombre);
            nombre.setText(heroes[position]);

            TextView descipcion = miFila.findViewById(R.id.descripcion);
            descipcion.setText(descripcion[position]);

            ImageView imagen = miFila.findViewById(R.id.imagen_heroe);
            imagen.setImageResource(imagenes[position]);
            return miFila;
        }


    }

}