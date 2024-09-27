package com.example.juegomatematicas.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegomatematicas.CrearUsuario;
import com.example.juegomatematicas.R;

import java.util.List;

public class AdaptadroJugadoresInicio extends RecyclerView.Adapter<AdaptadroJugadoresInicio.AdaptadorViewHolder> {
    private List<Jugador> listaJugadores;

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public AdaptadroJugadoresInicio(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;

    }

    @NonNull
    @Override
    public AdaptadroJugadoresInicio.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.intem_lista_jugadores_inicio, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadroJugadoresInicio.AdaptadorViewHolder holder, int position) {
        holder.bindAdaptador(listaJugadores.get(position));
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        ImageView foto;
        CardView cardViewJugador;

        View view;
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewJugador = itemView.findViewById(R.id.card_jugador_inicio);
            nombre = itemView.findViewById(R.id.textView_nombreJugadorInicio);
            foto = itemView.findViewById(R.id.imageView_jugador_inicio);

        }

        public void bindAdaptador(Jugador jugadores) {
            nombre.setText(jugadores.getNombre());
            foto.setImageResource(jugadores.getFoto());
            cardViewJugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("selecciono"+jugadores.getNombre());
                    if (jugadores.getNombre()=="Nuevo"){

                        Context context = v.getContext();
                        Intent miIntent = new Intent(context, CrearUsuario.class);
                        // Lanzamos la intención para abrir la nueva actividad
                        context.startActivity(miIntent);
                    }
                }
            });
        }
    }
}
