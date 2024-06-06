package com.example.menuactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //objeto actionmodel y metodos del callback de este
    ActionMode mActionMode;
    private ActionMode.Callback mActionModelCallback = new ActionMode.Callback() {
        //crea el munu de acción
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.actionbarmanu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        //metodo para gestionar los eventos al seleccionar las opciones del menu
        //conceptual
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.aniquilar:
                    //hay que crear un Aniquilar() para
                    //recorrer todos los elementos seleccionado (checked)
                    // en la listView
                    Toast.makeText(MainActivity.this, "Liquidado un Lannister", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.encerrar:
                    Toast.makeText(MainActivity.this, "Hemos encerrado a algun" +
                                    " lannister"
                            , Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.salvar:
                    Toast.makeText(MainActivity.this, "Hemos salvado a algún " +
                            "Lannister", Toast.LENGTH_SHORT).show();
                    return true;
            }


            return false;
        }

        //metodo cuando se sale del menu acción
        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };

    //fin de metodos del callback del actionmodel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listaLannisters = findViewById(R.id.listalanisters);

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_selectable_list_item,
                getResources().getStringArray(R.array.lannesters));
        listaLannisters.setAdapter(adaptador);
        listaLannisters.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mActionMode = MainActivity.this.startActionMode(mActionModelCallback);
        view.setSelected(true);
    }
}