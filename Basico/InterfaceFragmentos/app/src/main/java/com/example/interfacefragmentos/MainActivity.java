package com.example.interfacefragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
//3.Implementa esta interfaz en la actividad.
public class MainActivity extends AppCompatActivity implements PrimerFragmento.PasarDatos{

    PrimerFragmento primerFragmento ;
    SegundoFragmento segundoFragmento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarFragmentos(primerFragmento);

    }
    public void cargarFragmentos(Fragment f) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, new PrimerFragmento())
                .commit();
    }
//4. Método de la interfaz que se llama desde el PrimerFragmento
    @Override
    public void onPasaDatos(String datos) {
        // Aquí puedes hacer lo que necesites con los datos recibidos
        // 5.Por ejemplo, puedes pasarlos al segundo fragmento
        new SegundoFragmento().recivirDatos(datos);
    }
}