package com.example.webemailmap;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class VentanaWeb extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Intent intencio = new Intent();
        Intent choose = null;

        intencio.setAction(Intent.ACTION_VIEW);
        intencio.setData(Uri.parse("https://www.google.es"));
        choose = intencio.createChooser(intencio,"Elige Navegador");
        startActivity(intencio);

        return super.onCreateDialog(savedInstanceState);
    }
}
