package com.example.juegomultiplicar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class EstadisticasFragment extends Fragment {

    View view;

    TextView numeroTabla, fecha, heroe, fallos, aciertos;

    public EstadisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        EditText areaEmail = view.findViewById(R.id.areaEmailEsta);
        ImageButton botonEnviar = view.findViewById(R.id.botonEmailEsta);


        Bundle datosRecividos = getArguments();
        numeroTabla = view.findViewById(R.id.areaTablaEsta);
        fecha = view.findViewById(R.id.areaFechaEsta);
        heroe = view.findViewById(R.id.areaHeroeEst);
        fallos = view.findViewById(R.id.areaFallosEsta);
        aciertos = view.findViewById(R.id.areaPorcenEsta);
        if (datosRecividos != null) {
            //inicio de registro de datos optenidos
            numeroTabla.setText("Tabla del: " + datosRecividos.getString("numeroTabla", "sin datos"));

            fecha.setText("Fecha de la Prueba: " + datosRecividos.getString("fecha", "sin datos"));

            heroe.setText("Heroe : " + datosRecividos.getString("heroe", "sin datos"));

            ArrayList<String> datosFallos = datosRecividos.getStringArrayList("fallos");

            aciertos.setText("Porcentaje de aciertos: " + datosRecividos.getString("aciertos", "sin datos") + "0%");

            if (datosFallos != null && !datosFallos.isEmpty()) {
                String fallosCometidos = "";
                for (String fallo : datosFallos) {
                    fallosCometidos += fallo + "\n";
                    fallos.setText("Fallos cometidos:\n" + fallosCometidos);
                }
            } else {
                fallos.setText("No hay fallos registrados.");
            }
        }

        //logica para el envio de los datos estadisticos por email
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Intent choose = null;
                intent.setAction(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String para[] = {areaEmail.getText().toString(), "ihosuag@gmail.com"};//posible enviar a varios email y uno por defecto
                intent.putExtra(Intent.EXTRA_EMAIL, para);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Esta son las estadisticas que has optenido. ");
                intent.putExtra(Intent.EXTRA_TEXT, numeroTabla.getText()
                        + "\n" + fecha.getText()
                        + "\n" + heroe.getText()
                        + "\n" + fallos.getText()
                        + "\n" + aciertos.getText());
                intent.setType("message/rfc822");
                choose = intent.createChooser(intent, "Enviar Email");
                startActivity(intent);
            }
        });
        return view;
    }

}