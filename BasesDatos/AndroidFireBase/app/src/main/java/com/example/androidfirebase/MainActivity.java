package com.example.androidfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfirebase.adapter.FrutaAdapter;
import com.example.androidfirebase.databinding.ActivityMainBinding;
import com.example.androidfirebase.modelo.Fruta;
import com.example.androidfirebase.modelo.FrutaService;
import com.google.firebase.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //boton agregar
        binding.botonCrearFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabarDatosFireBase();
            }
        });
        //recycler
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerFrutas.setLayoutManager(lm);
        FrutaAdapter adapter = new FrutaAdapter(FrutaService.frutas
                , R.layout.item, this);
        binding.recyclerFrutas.setAdapter(adapter);
        cargarDatosFireBase();
    }

    private void grabarDatosFireBase() {
        Fruta fruta = new Fruta();
        try {
            fruta.setNombre(binding.editNombreFruta.getText().toString());
            fruta.setPrecio(Double.parseDouble(binding.editPrecio.getText().toString()));
            if (binding.editNombreFruta != null || binding.editPrecio != null) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("FRUTAS");//Nombre de la raiz de la base de datos
                reference.push().setValue(fruta);//push("nombre del id") si no se pone nada se genera automaticamente id de Fruta
                limpiarCampos();
            } else {
                Toast.makeText(MainActivity.this, "Inserte los datos ", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }
        } catch (java.lang.NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Inserte los datos ", Toast.LENGTH_SHORT).show();
            Log.d("Fallo", "Fallo insertar numeros nulos");
        }
    }

    public void cargarDatosFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("FRUTAS");//Nombre de la raiz de la base de datos
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //añadir dato
                Fruta fruta = snapshot.getValue(Fruta.class);
                //añadimos el id generaro por firebase automaticamente al objeto Fruta
                fruta.setId(snapshot.getKey());
                //validamos los datos de la Fruta para evitar Duplicidades
                if (!FrutaService.frutas.contains(fruta)) {//comprobamos con el metodo equal de Fruta que no hay dos iguales
                    //añadimos el resto de datos de la fruta a la lista del recycler
                    FrutaService.agregarFruta(fruta);
                }
                //actualizar el recycler
                binding.recyclerFrutas.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void limpiarCampos() {
        binding.editPrecio.setText("");
        binding.editNombreFruta.setText("");
    }

}