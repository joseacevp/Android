package com.example.entrenadormultiplicar.gestionusuarios;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.entrenadormultiplicar.MainActivity;
import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.gestionentrenador.SelectorEntrenador;

import java.util.List;

public class AdaptadorJugadoresInicio extends RecyclerView.Adapter<AdaptadorJugadoresInicio.AdaptadorViewHolder> {
    private List<Jugador> listaJugadores;
    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
    public void setListaJugadores(List<Jugador> jugadores) {
        this.listaJugadores = jugadores;
    }


    public AdaptadorJugadoresInicio(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;

    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.intem_lista_jugadores_inicio, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
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
                    if (jugadores.getNombre().equals("Nuevo")){
                        Context context = v.getContext();
                        Intent intent = new Intent(context, SelectorEntrenador.class);
                        context.startActivity(intent);

                    }else if(!jugadores.getNombre().equals("Nuevo")){
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}
