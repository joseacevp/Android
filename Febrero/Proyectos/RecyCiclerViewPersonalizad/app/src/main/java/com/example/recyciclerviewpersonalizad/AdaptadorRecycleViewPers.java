package com.example.recyciclerviewpersonalizad;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//implementacion del evento de pulsar o click en los elementos del RecycleView
//para esto implementamos OnClickListener
public class AdaptadorRecycleViewPers
        extends RecyclerView.Adapter<AdaptadorRecycleViewPers.ViewHolderPersonajes>
        implements View.OnClickListener {

    ArrayList<Personaje> listaPersonajes;

    //propiedad escuchador para implementar onClick
    private View.OnClickListener listener;

    public AdaptadorRecycleViewPers(ArrayList<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @NonNull
    @Override
    public AdaptadorRecycleViewPers.ViewHolderPersonajes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()
        ).inflate(R.layout.intem_list_personajes, null, false);

        //escuchador de onClick a la escucha
        view.setOnClickListener(this);

        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecycleViewPers.ViewHolderPersonajes viewHolderPersonajes, int i) {
        //2.
        viewHolderPersonajes.etiNombre.setText(listaPersonajes.get(i).getNombre());
        viewHolderPersonajes.etiInfo.setText(listaPersonajes.get(i).getInfo());
        viewHolderPersonajes.imagen.setImageResource(listaPersonajes.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }


    //metodo para implementar onClick
    public  void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
    }
    //implementacion de onClick
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView etiNombre, etiInfo;
        ImageView imagen;

        public ViewHolderPersonajes(@NonNull View itemView) {
            super(itemView);

            etiNombre = itemView.findViewById(R.id.textNombre);
            etiInfo = itemView.findViewById(R.id.textDescripcion);
            imagen = itemView.findViewById(R.id.imageView);
            //1. cambia apartir de aqui
        }
    }
}
