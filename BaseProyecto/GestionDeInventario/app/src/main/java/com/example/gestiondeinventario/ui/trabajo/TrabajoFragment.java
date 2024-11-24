package com.example.gestiondeinventario.ui.trabajo;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.gestiondeinventario.databinding.FragmentTrabajoBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrabajoFragment extends Fragment {
    private FragmentTrabajoBinding binding;
    private FirebaseAuth mAuth;
    private TextView textViewCodigoUsuarioTrabajo;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private String userName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrabajoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(TrabajoViewModel.class);

        binding = FragmentTrabajoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configura Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1048903263472-oovr5tjnles1cthfi3d05a7emu551atf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Crea un GoogleSignInClient con las opciones especificadas
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();

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

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Resultado devuelto al iniciar la Intent desde GoogleSignInClient.getSignInIntent(...)
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Sign-in exitoso, muestra la UI del usuario autenticado
            userName = account.getDisplayName();
            textViewCodigoUsuarioTrabajo.setText(userName);
            Toast.makeText(getContext(), "Usuario autenticado: " + userName, Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // El sign-in falló, maneja el error
            Toast.makeText(getContext(), "Falló la autenticación: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
