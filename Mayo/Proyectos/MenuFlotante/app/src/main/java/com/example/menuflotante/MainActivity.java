package com.example.menuflotante;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lista, lista2;
    ArrayList<String> listaStarts = new ArrayList<>();
    String[] listaLannisters = {"Jaime Lannister", "Cersev Lannister"};

    //Menú ActionMode
    ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_barra_accion, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.aniquilar:
                    //hay que crear un Aniquilar() para
                    //recorrer todos los elementos seleccionado (checked) en la listView
                    Toast.makeText(getApplicationContext(), "Hemos aniquilado a" +
                            " algún Lannister", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.encerrar:
                    Toast.makeText(getApplicationContext(), "Hemos encerrado a " +
                            "algún Lannister", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.salvar:
                    Toast.makeText(getApplicationContext(), "Hemos salvado a " +
                            "algún Lannister", Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;

            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };

    //crea o infla el menu flotante que se abre con una pulsacion larga
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_flotante, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //gestiona la selecciona de los intent de la lista
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.matar:
                Toast.makeText(getApplicationContext(), "Hemos matado a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            case R.id.sanar:
                Toast.makeText(getApplicationContext(), "Hemos sanado a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            case R.id.enviarmensaje:
                Toast.makeText(getApplicationContext(), "Le hemos enviado un mensaje a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Le hemos hecho otra cosa a " +
                        lista.getItemAtPosition(info.position), Toast.LENGTH_LONG).show();
                return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listView);
        //anadimos los datos del ArrayList
        listaStarts.add("Robb Stark");
        listaStarts.add("Ned Stark");
        listaStarts.add("Brandon Stark");
        listaStarts.add("Sansa Stark");
        listaStarts.add("Aria Stark");

        //crear adaptador menu flotante lista1
        ArrayAdapter adaptador = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaStarts);
        lista.setAdapter(adaptador);
        //evento de click en los elementos del ListView
        registerForContextMenu(lista);

        //lista de Lannister datos desde un xml values/array
        lista2 = findViewById(R.id.listView2);
        //crea adaptador menu barra de accion lista2
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_multiple_choice,
                getResources().getStringArray(R.array.listaLannister));
        lista2.setAdapter(adapter);
        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mActionMode = MainActivity.this.startActionMode(mActionModeCallback);
                view.setSelected(true);

            }
        });
    }
}