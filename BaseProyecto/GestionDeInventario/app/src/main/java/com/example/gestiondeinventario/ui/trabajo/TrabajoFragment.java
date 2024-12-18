package com.example.gestiondeinventario.ui.trabajo;

import static android.content.ContentValues.TAG;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gestiondeinventario.databinding.FragmentTrabajoBinding;
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
    private FirebaseAuth mAuth;
    private TextView textViewCodigoUsuarioTrabajo;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private String userName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrabajoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(TrabajoViewModel.class);

        binding = FragmentTrabajoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
            textViewCodigoUsuarioTrabajo.setText(userName);
        } else {
            textViewCodigoUsuarioTrabajo.setText("Usuario no autenticado");
            signIn();
        }

        return root;
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
