package com.example.androidfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidfirebase.databinding.ActivityMainBinding;
import com.example.androidfirebase.modelo.Fruta;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botonCrearFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruta fruta = new Fruta();
                try {
                    fruta.setNombre(binding.editNombreFruta.getText().toString());
                    fruta.setPrecio(Double.parseDouble(binding.editPrecio.getText().toString()));
                    if (binding.editNombreFruta != null || binding.editPrecio != null) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("FRUTAS");//Nombre de la raiz de la base de datos
                        reference.push().setValue(fruta);//push("nombre del id") si no se pone nada se genera automaticamente id de Fruta
                        limpiarCampos();
                    }else{
                        Toast.makeText(MainActivity.this, "Inserte los datos ", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }
                }catch ( java.lang.NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Inserte los datos ", Toast.LENGTH_SHORT).show();
                    Log.d("Fallo","Fallo insertar numeros nulos");
                }


            }
        });


    }

    private void limpiarCampos() {
        binding.editPrecio.setText("");
        binding.editNombreFruta.setText("");
    }

}