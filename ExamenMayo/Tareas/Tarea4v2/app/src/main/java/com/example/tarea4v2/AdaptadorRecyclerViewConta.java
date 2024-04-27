package com.example.tarea4v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecyclerViewConta extends RecyclerView.Adapter<AdaptadorRecyclerViewConta.ViewHolderContactos> {
    ArrayList<Contacto> listaContactos;
    private OnItemClickListener mListener;

    // Interfaz para manejar clics en los elementos de la lista
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdaptadorRecyclerViewConta(ArrayList<Contacto> listaContactos, OnItemClickListener listener) {
        this.listaContactos = listaContactos;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderContactos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.intem_list_contactos, viewGroup, false);

        return new ViewHolderContactos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContactos viewHolderContactos, int i) {
        Contacto contacto = listaContactos.get(i);
        viewHolderContactos.etiNombre.setText(contacto.getNombre());
        viewHolderContactos.etiEmail.setText(contacto.getEmail());
        viewHolderContactos.imagenPerfil.setImageBitmap(contacto.getFotoPerfil());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ViewHolderContactos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView etiNombre;
        TextView etiEmail;
        ImageView imagenPerfil;

        public ViewHolderContactos(@NonNull View itemView) {
            super(itemView);

            etiNombre = itemView.findViewById(R.id.textoNombreContacto);
            etiEmail = itemView.findViewById(R.id.textoEmailcontacto);
            imagenPerfil = itemView.findViewById(R.id.imagenPerfil);
            // Establecer el clic en el elemento de la lista
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }
    }


}
