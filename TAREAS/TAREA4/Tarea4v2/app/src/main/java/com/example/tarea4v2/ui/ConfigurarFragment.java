package com.example.tarea4v2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea4v2.MainActivity;
import com.example.tarea4v2.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tarea4v2.Partida;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class ConfigurarFragment extends Fragment {

    //variables
    View view;
    static String heroe, dificultad, fechaSeleccionada;
    TextView fecha;
    EditText numeroTabla ;
    Partida partida;
    Button botonAvatar;

    public ConfigurarFragment() {
        // Required empty public constructor
    }

    public static ConfigurarFragment newInstance(String param1, String param2) {
        ConfigurarFragment fragment = new ConfigurarFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onPause() {
        guardarPreferencias();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comfigurar, container, false);
        //carga la configuracion cargada incluye el heroe seleccionado
        cargarPreferencias();

        //boton avatar
        botonAvatar = view.findViewById(R.id.boton_avatar);
        botonAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accion para llamar an fragmento contenedor del recyclerView avatar
                Navigation.findNavController(view).navigate(R.id.action_nav_configurar_to_avatarFragment);
            }
        });
        //fecha
        fecha = view.findViewById(R.id.texto_fecha);
        fecha.setText(recuperarFechaActual());
        fechaSeleccionada = fecha.getText().toString();

        //numero de tabla
        numeroTabla = view.findViewById(R.id.edit_numero_tabla);
        // Crear un filtro para limitar la longitud del texto a 1 carácter
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(1);
        // Establecer el filtro en el EditText
        numeroTabla.setFilters(filters);

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
        return view;
    }

    private String recuperarFechaActual() {
        // Obtener la instancia de Calendar y establecer la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Crear un formato de fecha deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Formatear la fecha actual
        String fechaActual = dateFormat.format(calendar.getTime());

        return fechaActual;
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
        if (numeroTabla.getText().toString().isEmpty()){
            editor.putString("tabla", "1");
        }else{
            editor.putString("tabla", numeroTabla.getText().toString());
        }
        editor.putString("aleatorio", String.valueOf(numeroAleatorio));

        editor.commit();
    }

    //metodo que carga los datos previamente almacenados en un XML de preferencias
    private void cargarPreferencias() {
        SharedPreferences preferencias = getActivity().getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);
        heroe = preferencias.getString("heroe", "Capitan America");
    }
}