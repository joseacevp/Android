package com.example.examenmayo24.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenmayo24.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerMultipl extends RecyclerView.Adapter<AdaptadorRecyclerMultipl.AdaptadorViewHolder> {
    private List<Jugador> listaJugadores;
    private OnItemClickListener onItemClickListener;
    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }



    public AdaptadorRecyclerMultipl(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;

    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list_jugadores, parent, false
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

    public List<Jugador> getListaJugadoresSeleccionados() {
        List<Jugador> listaJudadoresSelect = new ArrayList<>();
        for (Jugador jugador : listaJugadores) {
            if (jugador.isSelected) {
                listaJudadoresSelect.add(jugador);
            }
        }
        return listaJudadoresSelect;
    }
    public interface OnItemClickListener {
        void onItemClick(Jugador jugador);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setListaJugadores(List<Jugador> jugadores) {
        this.listaJugadores = jugadores;
    }

    class AdaptadorViewHolder extends RecyclerView.ViewHolder {

        TextView etiNombre, etDes;
        ImageView foto, favorito;
        CardView cardjudador;
        View view;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            cardjudador = itemView.findViewById(R.id.carJugador);
            etiNombre = itemView.findViewById(R.id.textNombre);
            etDes = itemView.findViewById(R.id.textDesempe);
            foto = itemView.findViewById(R.id.imageView);
            favorito = itemView.findViewById(R.id.imageViewFavorito);
        }

        void bindAdaptador(final Jugador jugador) {
            etiNombre.setText(jugador.getNombre());
            etDes.setText(jugador.getDesempe());
            foto.setImageResource(jugador.getFoto());
            if (jugador.isSelected) {
                favorito.setImageResource(R.drawable.imagen_estrella);
            } else {
                favorito.setImageResource(R.drawable.imagen_favorito);
            }
            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (jugador.isSelected) {
                        favorito.setImageResource(R.drawable.imagen_favorito);
                        jugador.isSelected = false;
                    } else {
                        favorito.setImageResource(R.drawable.imagen_estrella);
                        jugador.isSelected = true;
                    }
                }
            });
            cardjudador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(jugador);
                    }
                }
            });
        }
    }
}
