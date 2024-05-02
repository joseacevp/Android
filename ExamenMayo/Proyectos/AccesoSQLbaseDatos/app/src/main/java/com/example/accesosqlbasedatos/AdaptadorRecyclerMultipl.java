package com.example.accesosqlbasedatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorRecyclerMultipl extends RecyclerView.Adapter<AdaptadorRecyclerMultipl.AdaptadorViewHolder> {
    private List<Objeto> objetoList;

    public List<Objeto> getObjetoList() {
        return objetoList;
    }

    public AdaptadorRecyclerMultipl(List<Objeto> listaObjetos) {
        this.objetoList = listaObjetos;

    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.linea_recycler, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorViewHolder holder, int position) {
        holder.bindAdaptador(objetoList.get(position));
    }

    @Override
    public int getItemCount() {
        return objetoList.size();
    }

//    public List<Objeto> getListaJugadoresSeleccionados() {
//        List<Objeto> listaJudadoresSelect = new ArrayList<>();
//        for (Objeto jugador : objetoList) {
//            if (jugador.isSelected) {
//                listaJudadoresSelect.add(jugador);
//            }
//        }
//        return listaJudadoresSelect;
//    }

//    public void setObjetoList(List<Objeto> jugadores) {
//        this.objetoList = jugadores;
//    }

    class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        TextView etiNombre, etialbum;
//        ImageView foto, favorito;
        CardView cardObjeto;
        View view;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            cardObjeto = itemView.findViewById(R.id.cardListado);
            etiNombre = itemView.findViewById(R.id.textMombre);
            etialbum = itemView.findViewById(R.id.textTitulo);
//            foto = itemView.findViewById(R.id.imageView);
//            favorito = itemView.findViewById(R.id.imageViewFavorito);
        }

        void bindAdaptador(final Objeto objeto) {
            etiNombre.setText(objeto.getNombreArtista());
            etialbum.setText(objeto.getTituloAlbum());
            cardObjeto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
//            foto.setImageResource(objeto.getFoto());
//            if (objeto.isSelected) {
//                favorito.setImageResource(R.drawable.imagen_estrella);
//            } else {
//                favorito.setImageResource(R.drawable.imagen_favorito);
//            }
//            favorito.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (objeto.isSelected) {
//                        favorito.setImageResource(R.drawable.imagen_favorito);
//                        objeto.isSelected = false;
//                    } else {
//                        favorito.setImageResource(R.drawable.imagen_estrella);
//                        objeto.isSelected = true;
//                    }
//                }
//            });

        }
    }
}
