package com.example.gestiondeinventario.ui.altaMaterial;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.databinding.FragmentAltaMaterialBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebase;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AltaMaterialFragment extends Fragment {
    private AltaMaterialViewModel altaMaterialViewModel;
    private int PICK_IMAGE_REQUEST = 1000;
    private AccesoFirebase accesoFirebase;

    private FragmentAltaMaterialBinding binding;

    private String nombre, codigo, localizacion, uso, foto;
    private Materiales materiales;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AltaMaterialViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AltaMaterialViewModel.class);

        binding = FragmentAltaMaterialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        accesoFirebase = new AccesoFirebaseImpl();

        binding.imageViewAltaMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí implementamos la selección de una imagen (puedes usar un Intent para abrir la galería)
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        return root;
    }

    private void limpiarFormulario() {
        binding.ediTextLocalizacionAltaMaterial.setText("");
        binding.ediTextNombreAltaMaterial.setText("");
        binding.ediTextUsoAltaMaterial.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            nombre = binding.ediTextNombreAltaMaterial.getText().toString();
            codigo = binding.editTextCodigoAlta.getText().toString();
            localizacion = binding.ediTextLocalizacionAltaMaterial.getText().toString();
            uso = binding.ediTextUsoAltaMaterial.getText().toString();
            // Subir imagen a Firebase Storage
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("materiales/" + System.currentTimeMillis() + ".jpg");
            storageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    foto = uri.toString(); // Guardar la URL en el campo foto
                    // Crear y guardar el material en Firebase Database
                    materiales = new Materiales(nombre, codigo, localizacion, uso, foto, true);
                    accesoFirebase.guardarDato(materiales);
                    limpiarFormulario();
                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al obtener URL", Toast.LENGTH_SHORT).show();
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Error al subir imagen", Toast.LENGTH_SHORT).show();
            });
        }
    }
}