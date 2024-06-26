package com.example.firebaserecyclerviewfotos;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageReference;

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private Adaptador adaptador;
    Modelo modelo = new Modelo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cargarDatosFirebase();

    }

    private void cargarDatosFirebase() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mDatabase.child("Productos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Modelo> arrayList = new ArrayList<>();

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Modelo modelo2 = snapshot1.getValue(Modelo.class);
                    //optener datos desde FIREBASE
//                    String nombre = modelo2.getNombre();
//                    String descripcion = modelo2.getDescripcion();
//                    String nombreFoto = modelo2.getFoto();
//                    //set datos array
//                    modelo.setDescripcion(descripcion);
//                    modelo.setNombre(nombre);
//                    modelo.setFoto(nombreFoto);
                    //foto
                    arrayList.add(modelo2);
                    adaptador = new Adaptador(getApplicationContext(), R.layout.linea_modelo, arrayList);
                    recyclerView.setAdapter(adaptador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //        Modelo modelo = new Modelo("nombre","descripcion",null);
        //        arrayList.add(modelo);


    }

}