// Generated by view binder compiler. Do not edit!
package com.example.tarea4v2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.tarea4v2.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentComfigurarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button botonAvatar;

  @NonNull
  public final View divider;

  @NonNull
  public final View divider3;

  @NonNull
  public final View divider4;

  @NonNull
  public final EditText editNumeroTabla;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final Spinner spinnerDificultad;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textoFecha;

  private FragmentComfigurarBinding(@NonNull ConstraintLayout rootView, @NonNull Button botonAvatar,
      @NonNull View divider, @NonNull View divider3, @NonNull View divider4,
      @NonNull EditText editNumeroTabla, @NonNull LinearLayout linearLayout3,
      @NonNull Spinner spinnerDificultad, @NonNull TextView textView4, @NonNull TextView textView5,
      @NonNull TextView textView6, @NonNull TextView textoFecha) {
    this.rootView = rootView;
    this.botonAvatar = botonAvatar;
    this.divider = divider;
    this.divider3 = divider3;
    this.divider4 = divider4;
    this.editNumeroTabla = editNumeroTabla;
    this.linearLayout3 = linearLayout3;
    this.spinnerDificultad = spinnerDificultad;
    this.textView4 = textView4;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textoFecha = textoFecha;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentComfigurarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentComfigurarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_comfigurar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentComfigurarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.boton_avatar;
      Button botonAvatar = ViewBindings.findChildViewById(rootView, id);
      if (botonAvatar == null) {
        break missingId;
      }

      id = R.id.divider;
      View divider = ViewBindings.findChildViewById(rootView, id);
      if (divider == null) {
        break missingId;
      }

      id = R.id.divider3;
      View divider3 = ViewBindings.findChildViewById(rootView, id);
      if (divider3 == null) {
        break missingId;
      }

      id = R.id.divider4;
      View divider4 = ViewBindings.findChildViewById(rootView, id);
      if (divider4 == null) {
        break missingId;
      }

      id = R.id.edit_numero_tabla;
      EditText editNumeroTabla = ViewBindings.findChildViewById(rootView, id);
      if (editNumeroTabla == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.spinner_dificultad;
      Spinner spinnerDificultad = ViewBindings.findChildViewById(rootView, id);
      if (spinnerDificultad == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.texto_fecha;
      TextView textoFecha = ViewBindings.findChildViewById(rootView, id);
      if (textoFecha == null) {
        break missingId;
      }

      return new FragmentComfigurarBinding((ConstraintLayout) rootView, botonAvatar, divider,
          divider3, divider4, editNumeroTabla, linearLayout3, spinnerDificultad, textView4,
          textView5, textView6, textoFecha);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
