package com.example.firebaseimagenes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    private Button mBotonSubir;
    private ImageView imageView;

    private StorageReference mStorageReference;
    private ProgressBar progressBar;
    private static final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mBotonSubir = findViewById(R.id.botonSubir);
        imageView = findViewById(R.id.imagenRecu);
        progressBar = findViewById(R.id.progressBar);


        mBotonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abre la galeria del telefono para seleccionar foto
                Intent intent = new Intent(Intent.ACTION_PICK);
                //TIPO DE IMAGENES A RECUPERAR
                intent.setType("image/*");//todas las imagenes con cualquier formato de archivo
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    //Obtiene la foto de la galeria seleccionada y la guarda en un URI para alacenarla en FireBase

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            Uri uri = data.getData();
            //carpeta detro de firebase Store
            StorageReference filePath = mStorageReference.child("fotos").child(uri.getLastPathSegment());//añade la foro al storege
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {//almacena la foto y lo confirma
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "Foto Almacenada en FireBase.", Toast.LENGTH_SHORT).show();

                    cargarFotoDesdeFireBase(taskSnapshot);
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    progressBar.setProgress((int) progress);
                }
            }) .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Error al subir la imagen.", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void cargarFotoDesdeFireBase(UploadTask.TaskSnapshot taskSnapshot) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Ocultar la barra de progreso cuando la imagen se cargue
                progressBar.setVisibility(View.GONE);
                Glide.with(getApplicationContext())
                        .load(uri)
                        .fitCenter()
                        .centerCrop()
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        });
        //recupera la direccion donde se almaceno la foto
        //agregamos la libreria que maneja la url de la imagen alamcenada en fireBase y la inserta en el imagenView de la
        //app hay que añadir la linea al grabel.app
        //compilegradle = { group = "com.github.bumptech.glide", name = "glide", version.ref = "compilegradle" }
        //compilegradle = "3.7.0"



    }
}