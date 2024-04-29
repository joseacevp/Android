package com.example.dialogosfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dialogosfragment.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements DialogoSexo.RespuestaDialogoSexo, View.OnClickListener {

    ActivityMainBinding binding;
    DialogoSexo dialogoSexo;
    Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = findViewById(R.id.botonDialogo1);
        boton1.setOnClickListener(this);
    }

    //implemetacion de la interface RespuestaDialogoSexo lanza tostada desde DialogoSexo
    @Override
    public void onRespuesta(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.botonDialogo1:
                    dialogoSexo = new DialogoSexo();
                    dialogoSexo.show(getSupportFragmentManager(),"Mi dialogo");
                    break;
            }
    }
}