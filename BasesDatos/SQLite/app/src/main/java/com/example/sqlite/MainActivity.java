package com.example.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botonRegistrarPersonas, botonConsultarPersonas, botonListaSpinner,
            botonListaListView, botonRegistrarMascotas, botonConsultarMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonConsultarMascotas = findViewById(R.id.botonConsultarMascota);
        botonConsultarMascotas.setOnClickListener(this);

        botonRegistrarPersonas = findViewById(R.id.botonRegistrarPersonas);
        botonRegistrarPersonas.setOnClickListener(this);

        botonConsultarPersonas = findViewById(R.id.botonConsultar);
        botonConsultarPersonas.setOnClickListener(this);

        botonListaListView = findViewById(R.id.botonListaListView);
        botonListaListView.setOnClickListener(this);

        botonListaSpinner = findViewById(R.id.botonListaSpinner);
        botonListaSpinner.setOnClickListener(this);

        botonRegistrarMascotas = findViewById(R.id.botonRegistrarMascotas);
        botonRegistrarMascotas.setOnClickListener(this);

        botonConsultarMascotas = findViewById(R.id.botonListaMascotas);
        botonConsultarMascotas.setOnClickListener(this);


        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,
                "db_usuarios", null, 1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonRegistrarPersonas:
                Intent evento = new Intent(getApplicationContext(), RegistroPersonas.class);
                startActivity(evento);
                break;
            case R.id.botonConsultar:
                Intent eventoBuscar = new Intent(getApplicationContext(), ConsultaUsuario.class);
                startActivity(eventoBuscar);
                break;
            case R.id.botonConsultarMascota:
                Intent eventoBuscarMascota = new Intent(getApplicationContext(), ConsultaMascota.class);
                startActivity(eventoBuscarMascota);
                break;
            case R.id.botonListaSpinner:
                Intent eventoListSp = new Intent(getApplicationContext(), ConsultaSpinner.class);
                startActivity(eventoListSp);

                break;
            case R.id.botonListaListView:
                Intent eventoListaView = new Intent(getApplicationContext(), ConsultaListView.class);
                startActivity(eventoListaView);
                break;
            case R.id.botonRegistrarMascotas:
                Intent eventoRegistroMascotas = new Intent(getApplicationContext(), RegistroMascotas.class);
                startActivity(eventoRegistroMascotas);
                break;
            case R.id.botonListaMascotas:
                Intent eventoListarMascotas = new Intent(getApplicationContext(),  ListaMascotas.class);
                startActivity(eventoListarMascotas);
                break;

        }
    }
}