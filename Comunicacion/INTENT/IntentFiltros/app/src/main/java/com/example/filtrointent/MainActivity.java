package com.example.filtrointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

/*
Este filtro de intent, indicaría que nuestra App está preparada para recibir datos, es decir, tratar
 un ACTION_SEND, formateado en texto plano (mimeType=”text/plain”). La categoría DEFAULT es la
 categoría que se debe usar para que tu App pueda ser candidata a tratar un intent implícito de
 este tipo de acción.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //declaracion del intent para recibir datos
        Intent intent = getIntent();
        String action = intent.getAction();
        String textoRecibido;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        2º Modifica la función onCreate de tu App. Recuerda que el intent que se envía, solicita
        la creación de una actividad, por tanto, cuando recibes el intent, se ejecutará la función
        onCreate. Con el método getIntent()  puedes obtener el objeto intent que creó la App y
        tratar los parámetros Acción, Categoría, Datos y tipo que aporta.  El siguiente código es
        muy sencillo, se obtiene una referencia al Intent y se compara con la acción. Si es la
        esperada  (ACTION_SEND) se actualiza el contenido de la caja de texto con el string
        extra EXTRA_TEXT:
         */
        EditText ed=(EditText)findViewById(R.id.edRecibido);
        //Si es tipo de comando es ACTION_SEND
        if (action.equals(Intent.ACTION_SEND)) {
            //obtenemos la información que nos han compartido
            textoRecibido= intent.getStringExtra(Intent.EXTRA_TEXT);
            if (textoRecibido != null) {
                ed.setText(textoRecibido); //actualizamos caja de texto
            }
        }

    }
}