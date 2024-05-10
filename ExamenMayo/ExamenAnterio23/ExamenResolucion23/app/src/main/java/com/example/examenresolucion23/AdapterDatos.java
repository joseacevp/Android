package com.example.examenresolucion23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private ArrayList<ContactoReal> listaContactos;
    private Context context;

    public AdapterDatos(ArrayList<ContactoReal> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre, fecha, telefono;
        ImageView fotoContacto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.texto_nombre);
            fecha = itemView.findViewById(R.id.texto_fecha);
            telefono = itemView.findViewById(R.id.texto_telefono);
            fotoContacto = itemView.findViewById(R.id.fotoContactoReal);
        }

        public void setAsignarDatos(ContactoReal contacto) {
            nombre.setText(contacto.getNombre());
            fecha.setText("Fecha de Nacimiento: " + contacto.getFecha());
            telefono.setText("Teléfono: " + contacto.getTelefono());
            if (contacto.getFotoUri() != null) {
                fotoContacto.setImageURI(contacto.getFotoUri());
            } else {
                fotoContacto.setImageResource(R.drawable.ic_menu_gallery);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_contacto, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.setAsignarDatos(listaContactos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }


}

