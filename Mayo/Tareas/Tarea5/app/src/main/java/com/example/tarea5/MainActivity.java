package com.example.tarea5;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tarea5.appprincipal.AppPrincipal;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 21;
    Button signOut, signIn;
    TextView titulo;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //evita el giro de la pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //instancia del titulo
        titulo = findViewById(R.id.tituloMain);
        // Cargar la fuente desde el directorio assets
        Typeface typeface = Typeface.createFromAsset(getAssets(), "anton.ttf");
        // Establecer la fuente en el TextView
        titulo.setTypeface(typeface);
        //instancia de los botones login
        signIn = findViewById(R.id.signInButton);
        signOut = findViewById(R.id.signoutButton);

        //instancia de identificador usuario, Carga el identificador de usario por defecto de
        //la base de datos como String no implementado en fichero Values/String
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("892188126945-ju53st8e70m7qfpaeqool3bets9mevtu.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Registrar el listener para ambos botones y programar el método onClick:
        signOut = findViewById(R.id.signoutButton);
        signIn = findViewById(R.id.signInButton);
        signIn.setOnClickListener(this);
        signOut.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        //si click en boton login lanza metodo inicio sesión Google
        //si click en boton cerrar sesion lanza metodo cerrar sesión
        if (i == R.id.signInButton) {
            signIn();
        } else if (i == R.id.signoutButton) {
            signOut();
        }
    }

    //metodo inicio sesión Google lanza dialogo para identificar usuario cuenta Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //metodo listener del dialogo seleccion usuario cuenta Google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //devuelve el resultado del login
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In valido
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                //inicia metodo identificador usuario Google pasa como parametro el resultado
                //del listener dialogo seleccion usuario Google
                firebaseAuthWithGoogle(account.getIdToken());

                //inicia metodo lanzar actividad AppPrincipal
                lanzarActividad();

            } catch (ApiException e) {
                // Google Sign In fallo
                Log.w(TAG, "Google sign FALLO", e);

            }
        }
    }

    //metodo identificador usuario FireBase recibe como parametro el identificador del dialogo
    //seleccion de usuario Google
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Identificación correcta usuario añadido a FireBase
                            Log.d(TAG, "Identificado Correctamente");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Mensage bienvenida
                            Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido " + user.getDisplayName(), Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            // Identificación incorrecta .
                            Log.w(TAG, "Identificación no Valida", task.getException());
                        }
                    }
                });
    }

    //metodo cerrar sesion
    private void signOut() {
        // Cierra la sesion de Firebase
        mAuth.signOut();
        // cierra sesion selector usuario Google
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Sesion Cerrada", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //inicia la actividad principal de la Aplicacion despuese de identificarse correctamente con
    //usuario Google.
    private void lanzarActividad() {
        Intent intent = new Intent(MainActivity.this, AppPrincipal.class);
        startActivity(intent);
    }
}