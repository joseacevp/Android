package com.example.menuactionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.menuactionbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //1
    ActionMode actionMode;
    ActivityMainBinding binding;
    //2
    private ActionMode.Callback mActionModelCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            //3
            // infla el menu crea el menu
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        //4
        // control de evento selección de un elemento del actionBar
        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.primero:
                    Toast.makeText(MainActivity.this, "primero", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.segundo:
                    Toast.makeText(MainActivity.this, "segundo"
                            , Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.tercero:
                    Toast.makeText(MainActivity.this, "tercero ", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //5
        // instancia del menu se crea en el evento OnClick de un elemento de la lista,
        // recyclerView, etc...
        actionMode = MainActivity.this.startActionMode(mActionModelCallback);

    }
}