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

public class TrabajoFragment extends Fragment {
    private FragmentTrabajoBinding binding;
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
                        accesoFirebaseTrabajos.guardarDatoTrabajos(trabajos,getContext());
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

    private void limpiarCampos() {
        binding.ediTextCantidadMaterialTrabajo.setText("");
        binding.ediTextCodigoMaterialTrabajo.setText("");
        binding.ediTextOrdenTrabajoTrabajo.setText("");
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
