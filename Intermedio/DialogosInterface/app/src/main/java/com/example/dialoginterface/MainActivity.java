package com.example.dialoginterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView = findViewById(R.id.textView2);
    }

    public void mensageEmerg(View view) {

//      mensage emergente
        AlertDialog.Builder constructor = new AlertDialog.Builder(MainActivity.this);
        constructor.setMessage(R.string.mensage).setTitle(R.string.titulo_mensaje);
        AlertDialog dialogo = constructor.create();
        dialogo.show();
    }

    public void mensageSelec(View view) {
        AlertDialog.Builder constructor = new AlertDialog.Builder(MainActivity.this);
        constructor.setMessage(R.string.mensage_2).setTitle(R.string.titulo_mensaje_2)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulso Aceptar", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Pulso Cancelar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialogo = constructor.create();
        dialogo.show();
    }

    public void mensageLista(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.opciones_difi, null);
        AlertDialog.Builder construptor = new AlertDialog.Builder(this);
        construptor.setView(dialogView).setTitle(R.string.titulo_mensaje_3);
        AlertDialog dialog = construptor.create();
        dialog.show();
        Button confirmar = dialogView.findViewById(R.id.buttonConf);
        //      referencias de los botones de selección
        CheckBox checkBoxFacil = dialogView.findViewById(R.id.radioButton);
        CheckBox checkBoxNormal = dialogView.findViewById(R.id.radioButton2);
        CheckBox checkBoxDificil = dialogView.findViewById(R.id.radioButton3);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //      manejo del estado del checkBox
                boolean isFacilSelected = checkBoxFacil.isChecked();
                boolean isNormalSelected = checkBoxNormal.isChecked();
                boolean isDificilSelected = checkBoxDificil.isChecked();

                if (isFacilSelected) {
                    Toast.makeText(MainActivity.this, "es facil", Toast.LENGTH_SHORT).show();
                }
                if (isNormalSelected) {
                    Toast.makeText(MainActivity.this, "es facil", Toast.LENGTH_SHORT).show();
                }
                if (isDificilSelected) {
                    Toast.makeText(MainActivity.this, "es facil", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }


}