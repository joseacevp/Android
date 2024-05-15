package com.example.examen23;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ViewHolderContactos> {

    private ArrayList<Contacto> listaContactos;
    private Context context;

    public AdaptadorContactos(ArrayList<Contacto> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorContactos.ViewHolderContactos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorContactos.ViewHolderContactos holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderContactos extends RecyclerView.ViewHolder {
//private String nombre,fecha,notificacion,foto,telefono;
        TextView nombre, fecha, notificacion,telefono;
        ImageView fotoContacto;
        public ViewHolderContactos(@NonNull View itemView) {
            super(itemView);
//            nombre = itemView.findViewById(R.id.texto_nombre);
//            fecha = itemView.findViewById(R.id.texto_fecha);
//            notificacion = itemView.findViewById(R.id.texto_notificacion);
//            telefono = itemView.findViewById(R.id.texto_telefono);
//            fotoContacto = itemView.findViewById(R.id.fotoContactoReal);
        }
    }
}
