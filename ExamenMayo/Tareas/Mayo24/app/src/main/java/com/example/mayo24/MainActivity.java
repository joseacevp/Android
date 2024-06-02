package com.example.mayo24;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.example.mayo24.ui.home.DialogoFecha;
import com.example.mayo24.ui.home.DialogoFechaViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mayo24.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements DialogoFecha.OnFechaSeleccionada {

    private AppBarConfiguration mAppBarConfiguration;
    private DialogoFechaViewModel dialogoFechaViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogoFechaViewModel = new ViewModelProvider(this).get(DialogoFechaViewModel.class);
//   Forma de recuperar los datos guardados de la fecha en el view model desde una actividad
//        dialogoFechaViewModel.getSelecFechaData().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Toast.makeText(MainActivity.this, "Fecha seleccionada: "+ s, Toast.LENGTH_SHORT).show();
//            }
//        });

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.setItemIconTintList(null);
        navigationView.setItemIconSize(200);
        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_home).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                llamarVentanaFecha();
                return true;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void llamarVentanaFecha() {
        DialogoFecha fecha = new DialogoFecha();
        // Establece ConfiguracionFragment como el oyente para los eventos de fecha
        fecha.setFechaSeleccionadaListener(this);
        fecha.show(getSupportFragmentManager(), "fecha");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onResultadoFecha(GregorianCalendar fecha) {
        //resultado de la fecha seleccionada
        String fechaString = fecha.get(Calendar.DAY_OF_MONTH) + " del " + fecha.get(Calendar.MONTH) + " del " + fecha.get(Calendar.YEAR);
        dialogoFechaViewModel.setSelecFechaData(fechaString);
    }
}