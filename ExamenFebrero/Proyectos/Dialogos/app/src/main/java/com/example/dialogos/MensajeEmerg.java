package com.example.dialogos;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class MensajeEmerg extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


//      mensage emergente
        AlertDialog.Builder constructor = new AlertDialog.Builder(getActivity());
        constructor.setMessage("Mensaje emergente contenido").setTitle("Titulo del Mensaje Emergente");
        AlertDialog dialogo = constructor.create();
//        dialogo.show();
        return constructor.create();
    }



}
