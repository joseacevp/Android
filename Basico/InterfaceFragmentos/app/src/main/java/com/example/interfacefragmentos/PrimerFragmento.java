package com.example.interfacefragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PrimerFragmento extends Fragment {
    View view;
    Button botonIrSegundo;
    SegundoFragmento segundoFragmento = SegundoFragmento.newInstance(null,null);
    EditText editTextPrimer;

    //1.Define una interfaz en el primer fragmento que
    // tenga un método para enviar los datos necesarios al segundo fragmento.///////////////////
    private PasarDatos escuchador;
    public interface PasarDatos {
        void onPasaDatos(String datos);
    }
    //almacena el contexto donde se llama
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            escuchador = (PasarDatos) context;
        } catch (Exception e) {
            Toast.makeText(context, "Fallo pasar Datos", Toast.LENGTH_SHORT).show();
        }
    }
    //Usar En algún punto donde necesitas enviar datos al segundo fragmento
    public void pasarDatosSegundoFragmento(String dato) {
        escuchador.onPasaDatos(dato);
    }
    //fin 1./////////////////////////////////////////////////////////////////////////////////////

    public PrimerFragmento() {
        // Required empty public constructor
    }

    public static PrimerFragmento newInstance(String param1, String param2) {
        PrimerFragmento fragment = new PrimerFragmento();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_primer_fragmento, container, false);
        editTextPrimer = view.findViewById(R.id.editPrimerFrag);
        botonIrSegundo = view.findViewById(R.id.botonIrSegundo);
        botonIrSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2. uso del metodo para pasar los datos atraves de una interface
                pasarDatosSegundoFragmento(editTextPrimer.getText().toString());
                irSegundoFragmento(segundoFragmento);

            }
        });


        return view;
    }
//metodo para llamar a segundo fragmento y sustituir el primero en le contenedor del MainActivity
    private void irSegundoFragmento(Fragment f) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, f).commit();
    }
}