package com.example.entrenadormultiplicar.gestionentrenador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.firebase.AccesoFirebase;
import com.example.entrenadormultiplicar.firebase.AccesoFirebaseImpl;
import com.example.entrenadormultiplicar.gestionusuarios.Jugador;

import java.util.List;

public class AdaptadorEntrenadores extends RecyclerView.Adapter<AdaptadorEntrenadores.AdaptadorViewHolder> {
    private DialogoEntrenadorViewModel dialogoEntrenadorViewModel;

    private AccesoFirebase accesoFirebase;

    private List<Jugador> listaEntrenadores;
//    EntrenadorViewModel entrenadorViewModel = new EntrenadorViewModel();

    private Jugador entrenadorSelecionado;

    public List<Jugador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(List<Jugador> entrenadores) {
        this.listaEntrenadores = entrenadores;
    }

    public Jugador getEntrenadorSeleccionado() {
        Jugador entrenadorSelect = new Jugador();
        entrenadorSelect = entrenadorSelecionado;
        return entrenadorSelect;
    }

    public AdaptadorEntrenadores(List<Jugador> listaEntrenadores, DialogoEntrenadorViewModel viewModel) {
        this.listaEntrenadores = listaEntrenadores;
        this.dialogoEntrenadorViewModel = viewModel;
    }


    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.intem_lista_entrenadores, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
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

        public void bindAdaptador(Jugador entrenador) {
            // Instanciar la clase que implementa la interfaz
            accesoFirebase = new AccesoFirebaseImpl();
            foto.setImageResource(entrenador.getFoto());
            foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    entrenadorSelecionado = entrenador;
                    Toast.makeText(context, "SELECCIONADO " + entrenador.getFoto() + entrenador.getNombre(), Toast.LENGTH_SHORT).show();
                    dialogoEntrenadorViewModel.setEntrenador(entrenadorSelecionado);
                    accesoFirebase.guardarDato(entrenadorSelecionado);

                }
            });
        }
    }


}
