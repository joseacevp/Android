package com.example.juegomatematicas.ui.secectorentrenador;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegomatematicas.InicioGestionUsuarios;
import com.example.juegomatematicas.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorEntrenadores extends RecyclerView.Adapter<AdaptadorEntrenadores.AdaptadorViewHolder>{

    private List<Entrenador> listaEntrenadores;
    public AdaptadorEntrenadores(List<Entrenador> listaEntrenadores){
        this.listaEntrenadores = listaEntrenadores;
    }
    @NonNull
    @Override
    public AdaptadorEntrenadores.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.intem_lista_entrenadores,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEntrenadores.AdaptadorViewHolder holder, int position) {
        holder.bindAdaptador(listaEntrenadores.get(position));
    }

    @Override
    public int getItemCount() {
        return listaEntrenadores.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imagen_entrenador);
        }

        public void bindAdaptador(Entrenador entrenador) {
            foto.setImageResource(entrenador.getFoto());
            foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent miIntent = new Intent(context, InicioGestionUsuarios.class);
                    // Lanzamos la intención para abrir la nueva actividad
                    context.startActivity(miIntent);
                }
            });
        }
    }


}
