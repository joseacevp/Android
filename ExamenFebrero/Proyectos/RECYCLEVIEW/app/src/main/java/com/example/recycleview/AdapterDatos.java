package com.example.recycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    //1. lista de datos con los datos de los intem
    ArrayList<String> listDatos;

    //2. construptor de la lista de datos llega lista de datos y se
    //asignan al elemento listDatos
    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        //3. referencia de los componentes de la lista de datos
        TextView dato;
        public final View view;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            //4. instancia a la referencia de los datos de la lista de datos
            dato = itemView.findViewById(R.id.textView);
            view = itemView;
        }

    //8. asigna los datos del intem que toca de la lista y lo asigna
    //al componente del xml listaItem
        public void setAsignarDatos(String s) {
            dato.setText(s);
        }
    }

    /*
    enlaza este adaptador con el xml de los items
     */
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //5. infla la vista con la referencia del xml lista intem que contien la plantilla
        //de los datos que reflejaran los componentes del recycleView
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    //6.retorna el tamaño de la lista de datos recibida en el construptor
    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    //7. establece la comunicación entre el adaptador y la clase viewHolderDatos
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        //llamamos al metodo asignardatos pasando el dato del intem que
        //toca de la lista
        viewHolderDatos.setAsignarDatos(listDatos.get(i));
        //metodo viewHolderDatospare gestionar el item seleccionado
        viewHolderDatos.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.reenviarDatos(viewHolderDatos.dato);
            }
        });
    }


}

