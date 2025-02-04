package com.example.gestiondeinventario.ui.altaMaterial;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.databinding.FragmentAltaMaterialBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseMateriales;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;
import com.google.firebase.database.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AltaMaterialFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1000;
    private AccesoFirebaseMateriales accesoFirebaseMateriales;
    private FragmentAltaMaterialBinding binding;
    private String nombre, codigo, localizacion, uso, foto, cantidad;
    private Materiales materiales;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAltaMaterialBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        accesoFirebaseMateriales = new AccesoFirebaseImpl();
        binding.buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la galeria para seleccionar imagen
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
        binding.ediTextCantidadAltaMaterial.setText("");
        binding.editTextCodigoAlta.setText("");
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
            // Mostrar la ProgressBar
            binding.progressBar.setVisibility(View.VISIBLE);
            nombre = binding.ediTextNombreAltaMaterial.getText().toString();
            codigo = binding.editTextCodigoAlta.getText().toString();
            localizacion = binding.ediTextLocalizacionAltaMaterial.getText().toString();
            uso = binding.ediTextUsoAltaMaterial.getText().toString();
            cantidad = binding.ediTextCantidadAltaMaterial.getText().toString();
            // Subir imagen a Firebase Storage
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("materiales/" + System.currentTimeMillis() + ".jpg");
            storageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    foto = uri.toString(); // Guardar la URL en el campo foto
                    if (!(nombre.equals("") && codigo.equals("") && localizacion.equals("") && uso.equals("") && cantidad.equals(""))) {
                        // Crear y guardar el material en Firebase Database
                        materiales = new Materiales(nombre, codigo, localizacion, uso, foto, cantidad, true);
                        accesoFirebaseMateriales.guardarDatoMateriales(materiales, getContext());
                        limpiarFormulario();
                        binding.progressBar.setVisibility(View.GONE);
                        mostrarDialogo("DATOS GRABADOS", "Acepte para continuar", getContext());
                    } else {
                        mostrarDialogo("ERROR", "Indique datos en todos los Campos", getContext());
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al obtener URL", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(), "Error al subir imagen", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            });
        }
    }

    private void mostrarDialogo(String titulo, String mensaje, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // Configurar el título y el mensaje
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        // Configurar botón positivo (Aceptar)
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al presionar "Aceptar"
                dialog.dismiss(); // Cerrar el diálogo
            }
        });
        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}