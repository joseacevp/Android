package com.example.tarea5.appprincipal.ui.convocados;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.ui.equipo.Jugador;

import java.util.ArrayList;

public class AdaptadorConvocados extends RecyclerView.Adapter<AdaptadorConvocados.ViewHolderConvocados> {

   ArrayList<Jugador> listaConvocados;

    public AdaptadorConvocados(ArrayList<Jugador> listaConvocados) {
        this.listaConvocados = listaConvocados;
    }

    @NonNull
    @Override
    public AdaptadorConvocados.ViewHolderConvocados onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()
        ).inflate(R.layout.item_list_convocados, null, false);
        return new ViewHolderConvocados(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorConvocados.ViewHolderConvocados holder, int position) {
            holder.nombreConvocado.setText(listaConvocados.get(position).getNombre());
            holder.posicionConvocado.setText(listaConvocados.get(position).getPosicion());
            holder.imagenConvocado.setImageResource(listaConvocados.get(position).getFoto());
    }

    @Override
    public int getItemCount() {

            return listaConvocados.size();

    }

    public class ViewHolderConvocados extends RecyclerView.ViewHolder {
        TextView nombreConvocado,posicionConvocado;
        ImageView imagenConvocado;
        public ViewHolderConvocados(@NonNull View itemView) {
            super(itemView);
            nombreConvocado = itemView.findViewById(R.id.textNombreConvo);
            posicionConvocado = itemView.findViewById(R.id.textDescripcionConvo);
            imagenConvocado = itemView.findViewById(R.id.imageViewConvo);

        }
    }
}
