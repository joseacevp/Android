package com.example.intentimplicitos;

import android.content.Intent;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botweb,botmapa,botmail;

    EditText texturl,textlongi,textlati,textemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botmail = findViewById(R.id.boton_enviarmail);
        botmail.setOnClickListener(this);

        botmapa = findViewById(R.id.boton_irmapa);
        botmapa.setOnClickListener(this);

        botweb = findViewById(R.id.boton_irweb);
        botweb.setOnClickListener(this);

        textemail = findViewById(R.id.text_email);
        textlati = findViewById(R.id.text_latitud);
        textlongi = findViewById(R.id.text_longitud);
        texturl = findViewById(R.id.text_url);

    }

    @Override
    public void onClick(View view) {
        Intent intencio = new Intent();
        Intent choose = null;
        switch (view.getId()){
            case R.id.boton_irweb:
                intencio.setAction(Intent.ACTION_VIEW);
                intencio.setData(Uri.parse(texturl.getText().toString()));
                choose = intencio.createChooser(intencio,"Elige Navegador");
                startActivity(intencio);
                break;
            case R.id.boton_irmapa:
                intencio.setAction(Intent.ACTION_VIEW);
                intencio.setData(Uri.parse("geo: "+textlati.getText().toString()+ "," + textlongi.getText().toString()));
                choose = intencio.createChooser(intencio, "Lanzar Mapa");
                startActivity(intencio);
                break;
            case R.id.boton_enviarmail:
                intencio.setAction(Intent.ACTION_SEND);
                intencio.setData(Uri.parse("mailto:"));
                String para [] = { textemail.getText().toString(),"ihosuag@gmail.com"};//posible enviar a varios email y uno por defecto
                intencio.putExtra(Intent.EXTRA_EMAIL,para);
                intencio.putExtra(Intent.EXTRA_SUBJECT, "Saludos desde un ejemplo de aplicación Android");
                intencio.putExtra(Intent.EXTRA_TEXT, "Hola!!, ¿Qué tal ?");
                intencio.setType("message/rfc822");
                choose = intencio.createChooser(intencio,"Enviar Email");
                startActivity(intencio);

                break;
        }
    }
}