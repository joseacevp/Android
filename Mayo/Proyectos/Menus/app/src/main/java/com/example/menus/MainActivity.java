package com.example.menus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView menu_flotante;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int seleccion = item.getItemId();

        switch (seleccion) {
            case R.id.confFactura:
                Toast.makeText(this, "Se pulso la Opción  Configurar Factura ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.confPedido:
                Toast.makeText(this, "Se pulso la Opción Configurar Pedido", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nueCliente:
                Toast.makeText(this, "Se pulso la Opción Nuevo Cliente ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.busCliente:
                Toast.makeText(this, "Se pulso la Opción Buscar Cliente", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nueFactura:
                Toast.makeText(this, "Se pulso la Opción Nueva Factura", Toast.LENGTH_SHORT).show();
                break;
            case R.id.busFactura:
                Toast.makeText(this, "Se pulso la Opción Buscar Factura", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}