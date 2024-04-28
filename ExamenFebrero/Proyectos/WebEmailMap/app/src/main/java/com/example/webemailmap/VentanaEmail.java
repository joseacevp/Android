package com.example.webemailmap;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class VentanaEmail extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Intent intencio = new Intent();
        Intent choose = null;
        intencio.setAction(Intent.ACTION_SEND);
        intencio.setData(Uri.parse("mailto:"));
        String para[] = {"otroEmail@gamail.com", "ihosuag@gmail.com"};//posible enviar a varios email y uno por defecto
        intencio.putExtra(Intent.EXTRA_EMAIL, para);
        intencio.putExtra(Intent.EXTRA_SUBJECT, "Saludos desde un ejemplo de aplicación Android");
        intencio.putExtra(Intent.EXTRA_TEXT, "Hola!!, ¿Qué tal ?");
        intencio.setType("message/rfc822");
        choose = intencio.createChooser(intencio, "Enviar Email");
        startActivity(intencio);

        return super.onCreateDialog(savedInstanceState);
    }


}
