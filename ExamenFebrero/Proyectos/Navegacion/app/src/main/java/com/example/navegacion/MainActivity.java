package com.example.navegacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        seleccionarFragmetnoDefecto();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                boolean fragmentTransaction = false;

                switch (item.getItemId()) {
                    case R.id.configuracion:
                        fragment = new primerFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.entrenar:
                        fragment = new segundoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.estadisticas:
                        fragment = new TercerFragment();
                        fragmentTransaction = true;
                        break;
                }
                if (fragmentTransaction){
                    //cambia al fragmento seleccionado
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragment).commit();
                   // cheque la seleccion del menu
                    item.setChecked(true);
                    //pone el titulo en el toolBar
                    getSupportActionBar().setTitle(item.getTitle());
                    //cierra la barra del navegationDrawer
                    drawerLayout.closeDrawers();
                }


                return true;
            }
        });
    }

    private void seleccionarFragmetnoDefecto() {
        //cambia al fragmento seleccionado
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new primerFragment()).commit();
        //creamos un item de menu con la seleccion primera
        MenuItem item = navigationView.getMenu().getItem(0);

        // cheque la seleccion del menu
        item.setChecked(true);
        //pone el titulo en el toolBar
        getSupportActionBar().setTitle(item.getTitle());

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}