package com.example.gestiondeinventario.ui.trabajo;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.databinding.FragmentTrabajoBinding;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseMateriales;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseTrabajos;
import com.example.gestiondeinventario.ui.firebase.Materiales;
import com.example.gestiondeinventario.ui.firebase.Trabajos;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrabajoFragment extends Fragment {
    private FragmentTrabajoBinding binding;
    private Materiales material;
    private AccesoFirebaseMateriales accesoFirebaseMateriales;
    private AccesoFirebaseTrabajos accesoFirebaseTrabajos;
    private FirebaseAuth mAuth;
    private TextView textViewCodigoUsuarioTrabajo;

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private String userName, unidades, codigo, orden;
    private Trabajos trabajos;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrabajoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(TrabajoViewModel.class);
        binding = FragmentTrabajoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        accesoFirebaseMateriales = new AccesoFirebaseImpl();
        accesoFirebaseTrabajos = new AccesoFirebaseImpl();
        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("962929591011-r2eunkhfod83ighe3h69q3556c0nd56b.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        // Encuentra el TextView
        textViewCodigoUsuarioTrabajo = binding.textViewCodigoUsuarioTrabajo;
        // Verifica si hay un usuario actual
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userName = currentUser.getDisplayName();
            textViewCodigoUsuarioTrabajo.setText("Usuario: " + userName);
        } else {
            textViewCodigoUsuarioTrabajo.setText("Usuario no autenticado");
            signIn();
        }
        unidades = binding.ediTextCantidadMaterialTrabajo.getText().toString();
        codigo = binding.ediTextCodigoMaterialTrabajo.getText().toString();
        orden = binding.ediTextOrdenTrabajoTrabajo.getText().toString();
        binding.ediTextCodigoMaterialTrabajo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //si indica un codigo de materia valido muestra la imagen del material al salir del campo
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!binding.ediTextCodigoMaterialTrabajo.getText().toString().equals("")) {
                        mostrarImagenMaterial();
                    }
                }
            }
        });

        binding.bottonGrabarTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(binding.ediTextCantidadMaterialTrabajo.getText().toString()) > 0) {
                        accesoFirebaseMateriales.actualizarCantidadMateriales("-" + binding.ediTextCantidadMaterialTrabajo.getText().toString()
                                , binding.ediTextCodigoMaterialTrabajo.getText().toString(), getContext());
                        // Crear y guardar el material en Firebase Database
                        //Trabajos(String ordenTrabajo, String nombreOperario, String codigoMaterial, String unidades)
                        trabajos = new Trabajos(binding.ediTextOrdenTrabajoTrabajo.getText().toString(), userName,
                                binding.ediTextCodigoMaterialTrabajo.getText().toString(), binding.ediTextCantidadMaterialTrabajo.getText().toString());
                        accesoFirebaseTrabajos.guardarDatoTrabajos(trabajos, getContext());
                        limpiarCampos();

                    } else {
                        mostrarDialogo("ERROR AL INDICAR DATOS", "Indique la cantidad correctamente", getContext());
                    }
                } catch (Exception e) {
                    mostrarDialogo("ERROR", "Faltan datos para poder realizar la grabación del Trabajo", getContext());
                }
            }
        });

        return root;
    }

    private void mostrarImagenMaterial() {
        binding.progressBarTrabajo.setVisibility(View.VISIBLE);
        accesoFirebaseMateriales.cargarDatosMateriales(new AccesoFirebaseMateriales.OnDataLoadedCallbackMateriales() {
            @Override
            public void onDataLoaded(List<Materiales> materiales) {
                boolean materialEncontrado = false; // Bandera para verificar si el material fue encontrado
                for (Materiales material : materiales) {
                    // Comparamos correctamente las cadenas
                    if (material.getCodigo().equals(binding.ediTextCodigoMaterialTrabajo.getText().toString())) {
                        // Si encuentra el material, carga la imagen
                        Glide.with(requireContext())
                                .load(material.getFotoUri()) // URL de Firebase Storage
                                .placeholder(null) // Imagen de carga
                                .error(android.R.drawable.ic_menu_camera) // Imagen de error
                                .into(binding.imageViewTrabajo);
                        binding.progressBarTrabajo.setVisibility(View.GONE);
                        materialEncontrado = true; // Cambiamos la bandera
                        break; // Salimos del bucle una vez encontrado
                    }
                }
                // Si no se encontró el material, muestra un mensaje
                if (!materialEncontrado) {
                    mostrarDialogo("Error", "No se encontró el material con el código indicado.", getContext());
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), "Fallo al cargar datos de Firebase: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private List<Materiales> cargarMaterialesDesdeFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Materiales");
        List<Materiales> listaMateriales = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMateriales.clear(); // Limpia la lista antes de agregar nuevos datos
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Materiales material = dataSnapshot.getValue(Materiales.class);
                    listaMateriales.add(material);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
        return listaMateriales;
    }

    private void limpiarCampos() {
        binding.ediTextCantidadMaterialTrabajo.setText("");
        binding.ediTextCodigoMaterialTrabajo.setText("");
        binding.ediTextOrdenTrabajoTrabajo.setText("");
        // Quitar la imagen cargada estableciendo una imagen predeterminada o dejándola vacía
        binding.imageViewTrabajo.setImageResource(android.R.drawable.ic_menu_camera); // Imagen predeterminada
        // O, para dejarla vacía, puedes usar:
        // binding.imageViewTrabajo.setImageDrawable(null);
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

    //llama al gestor de usuarios de google del dispositivo
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //gestiona el resultado de la llamada al gestor de usuarios de google del dispositivo
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Devuelve el resultado del login
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In válido
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                // Inicia método identificador usuario Google
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In falló
                Log.w(TAG, "Google sign FALLO", e);
                Toast.makeText(getContext(), "Falló la autenticación de Google: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //gestiona el usuario devuelto por el metodo sing in
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Identificación correcta usuario añadido a Firebase
                            Log.d(TAG, "Identificado Correctamente");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Mensaje bienvenida
                            userName = user.getDisplayName();
                            textViewCodigoUsuarioTrabajo.setText(userName);//Imprime en pantalla el nombre del usario de google
                            //graba el dato del usuario de google

                            Toast.makeText(requireContext(), "Bienvenido " + userName, Toast.LENGTH_SHORT).show();
                        } else {
                            // Identificación incorrecta
                            Log.w(TAG, "Identificación no Válida", task.getException());
                            Toast.makeText(requireContext(), "Fallo en la autenticación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
