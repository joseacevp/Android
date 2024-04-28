package com.example.dialogos;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MensajeLista extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.opciones_difi, null);
        AlertDialog.Builder construptor = new AlertDialog.Builder(getActivity());
        construptor.setView(dialogView).setTitle("Titulo Mensaje Lista Seleccionable");
        AlertDialog dialog = construptor.create();
//
//        dialog.show();
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
                    Toast.makeText(getActivity(), "es facil", Toast.LENGTH_SHORT).show();
                }
                if (isNormalSelected) {
                    Toast.makeText(getActivity(), "es facil", Toast.LENGTH_SHORT).show();
                }
                if (isDificilSelected) {
                    Toast.makeText(getActivity(), "es facil", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
