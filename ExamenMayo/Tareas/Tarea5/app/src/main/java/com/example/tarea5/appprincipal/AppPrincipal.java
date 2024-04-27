package com.example.tarea5.appprincipal;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;
import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.convocados.ConvocadosViewModel;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;
import com.example.tarea5.appprincipal.ui.fechas.FechaViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.example.tarea5.databinding.ActivityAppPrincipalBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class AppPrincipal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseReference mDataBase;
    private String fechaSeleccionada;
    private ArrayList<Jugador> jugadoresSeleccionados;
    private boolean hayfechaSeleccionada = false;
    private boolean haylistaConvocados = false;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Menu menu;
    private FechaViewModel fechaViewModel;
    private ConvocadosViewModel convocadosViewModel;
    private ActivityAppPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //evita el giro de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //inicia metodo comprobar datos fecha y lista jugadores
        comprobarDatos();
        //instancia de la barra de herramientas
        setSupportActionBar(binding.appBarAppPrincipal.toolbar);
        //instancia de menu desplegable Navegation Drawer
        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        //para que respete los colores del los iconos
        navigationView.setItemIconTintList(null);
        //cambia el tamaño de los iconos
        navigationView.setItemIconSize(150);

        //indica que icono se incluyen en la navegacion del navegation drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_convocados, R.id.nav_equipo, R.id.nav_borrar_convocados,
                R.id.nav_seleccionar_fecha,
                R.id.nav_historico)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_app_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    // Configurar listener para las opciones del Navigation Drawer
        Menu menu = navigationView.getMenu();

        menu.findItem(R.id.nav_guardar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if (hayfechaSeleccionada && haylistaConvocados) {
                    guardarDatos();
                } else {
                    Toast toast = Toast.makeText(AppPrincipal.this, "Faltan Datos para poder Guardar", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            }
        });
    }

    private void comprobarDatos() {
        fechaViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        fechaViewModel.getSelectedDateString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    hayfechaSeleccionada = true;
                } else {
                    hayfechaSeleccionada = false;
                }
            }
        });
        convocadosViewModel = new ViewModelProvider(this).get(ConvocadosViewModel.class);
        convocadosViewModel.getListaConvocados().observe(this, new Observer<ArrayList<Jugador>>() {
            @Override
            public void onChanged(ArrayList<Jugador> jugadors) {
                if (jugadors != null) {
                    haylistaConvocados = true;
                } else {
                    haylistaConvocados = false;
                }
            }
        });
    }

    private void guardarDatos() {
        //optiene los datos de fecha y lista de jugadores de los fragmentos
        fechaViewModel = new ViewModelProvider(this).get(FechaViewModel.class);
        fechaViewModel.getSelectedDateString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    fechaSeleccionada = s;
                }
            }
        });
        convocadosViewModel = new ViewModelProvider(this).get(ConvocadosViewModel.class);
        convocadosViewModel.getListaConvocados().observe(this, new Observer<ArrayList<Jugador>>() {
            @Override
            public void onChanged(ArrayList<Jugador> jugadors) {
                if (jugadors != null) {
                    jugadoresSeleccionados = jugadors;
                }
            }
        });
        //optiene la referencia de la base de datos
        mDataBase = FirebaseDatabase.getInstance().getReference();

        // Crear un nodo para la fecha seleccionada
        DatabaseReference fechaRef = mDataBase.child("Partidos").child(fechaSeleccionada);

        // Agregar cada jugador bajo la fecha seleccionada
        for (Jugador jugador : jugadoresSeleccionados) {
            // Generar una clave única para cada jugador
            String jugadorKey = fechaRef.push().getKey();

            // Guardar el jugador en la base de datos bajo la clave única
            fechaRef.child(jugadorKey).setValue(jugador)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Éxito al guardar el jugador
                            System.out.println("Jugador guardado exitosamente");
//                            Toast.makeText(AppPrincipal.this, "Jugador guardado exitosamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error al guardar el jugador
                            Toast.makeText(AppPrincipal.this, "Error al guardar el jugador: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        Toast.makeText(AppPrincipal.this, "Jugadores guardado exitosamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_app_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}