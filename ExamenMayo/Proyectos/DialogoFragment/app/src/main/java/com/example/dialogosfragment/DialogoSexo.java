package com.example.dialogosfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class DialogoSexo extends DialogFragment {
    RespuestaDialogoSexo respuesta;

    /* Este método es llamado al hacer el show() de la clase DialogFragment()*/
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
// Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Escribimos el título
        builder.setTitle("Pregunta muy importante:");
        //Escribimos la pregunta
        builder.setMessage("¿Eres una chica?");
        //añadimos el botón de Si y su acción asociada
        builder.setPositiveButton("¡SI!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                respuesta.onRespuesta("Es una chica!");
            }
        });
        //añadimos el botón de No y su acción asociada
        builder.setNegativeButton("¡NO!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                respuesta.onRespuesta("Es un chico!");
            }
        });
        // Crear el AlertDialog y devolverlo
        return builder.create();
    }


    //interfaz para la comunicación entre la Actividad y el Fragmento
    public interface RespuestaDialogoSexo {
        public void onRespuesta(String s);
    }

    // Método para mostrar el diálogo
    private void show(FragmentManager fragmentManager, String mi_dialogo) {
        show(fragmentManager, mi_dialogo);
    }

    //Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        respuesta = (RespuestaDialogoSexo) context;
    }
}
 /*   La clase Builder de AlertDialog, tiene un montón de métodos que te serán de utilidad para
        crear tu diálogo. Por ejemplo, además de los que has visto en el código de ejemplo (setTitle,
        setMessage, setPositiveButton, setNegativeButton) tienes métodos como setItems() para
        coger de un array una lista de elementos a seleccionar, setSingleChoiceItems() si quieres
        mostrar una lista con RadioButtons y setMultiChoiceItems() si quieres mostrar una lista de
        elementos con Checkboxes. Esta clase, con estos métodos te da toda la libertad del mundo
        para construir casi cualquier diálogo, no obstante, siempre puedes construirte tu propio
        recurso de layout personalizado e inflar el diálogo a partir de este recurso personalizado.

*/