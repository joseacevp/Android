package com.example.interfacefragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SegundoFragmento extends Fragment {
    View view;
    Button botoVolver;
    //6.dato statico para manejar los datos recibidos desde la interface
    protected static String dato;

     public SegundoFragmento() {

    }

    public static SegundoFragmento newInstance(String param1, String param2) {
        SegundoFragmento fragment = new SegundoFragmento();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_segundo_fragmento, container, false);
        TextView textoSegun = view.findViewById(R.id.tituloSegundo);
        //8. refleja los datos en el textView si han sido recibidos
        if (dato != null) {
            System.out.println("Dato recibido en el segundo fragmento: " + dato);
            // Actualizar el TextView con el dato recibido
            textoSegun.setText(dato);
        } else {
            System.out.println("No se recibió ningún dato en el segundo fragmento");
        }
        botoVolver = view.findViewById(R.id.botonVolver);
        botoVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volver al primer fragmento
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new PrimerFragmento()).commit();
            }
        });

        return view;
    }
    //7.En la implementación de la interfaz en la actividad, obtén una referencia al segundo fragmento
    // y llama a un método público en el segundo fragmento para pasar los datos.
    public void recivirDatos(String datos) {
        dato = datos;

        System.out.println(dato);
    }

}