package com.example.juegomultiplicar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity  {

    ConfiguracionFragment pantallaConfig = new ConfiguracionFragment();
    EntrenarFragment pantallaEntrenar = new EntrenarFragment();
    EstadisticasFragment pantallaEstadisticas = new EstadisticasFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.id_barra_navegador);

        //metodo mOnNavegationListener control de eventos al seleccionar las opciones del menu bar
        navigation.setOnNavigationItemSelectedListener(mOnNavegationListener);

        //carga la pantalla de configuracion por defecto
        loadFragment(pantallaEntrenar);

    }



    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavegationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.id_fragmento_configuracion:
                    //metodo loadFragment para lanzar las pantallas
                    loadFragment(pantallaConfig);
                    break;
                case R.id.id_fragmento_entrenar:
                    loadFragment(pantallaEntrenar);
                    break;
                case R.id.id_fragmento_estadisticas:
                    loadFragment(pantallaEstadisticas);
                    break;
            }

            return false;
        }
    };

    public void loadFragment(Fragment f) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //relaciona el fragment recibido "f" con el contenedor de fragment 'frame_container'
        transaction.replace(R.id.fragment_container, f);
        transaction.commit();
    }

}