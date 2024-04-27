package com.example.reproductormultimedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    //1 lista datos a introducir en el RecyclerView
    private List<AppMedia> listaDatos;
    //instancia de la ventana contenedora de RecycleView
    private MainActivity ventanaSelectorLo;

    //2. construptor de la lista de datos llega lista de datos y se
    //asignan al elemento listDatos
    public Adaptador(List<AppMedia> datosLocalesLista, MainActivity ventanaSelector) {
        listaDatos = datosLocalesLista;
        ventanaSelectorLo = ventanaSelector;
    }

    //construptor
    public class ViewHolder extends RecyclerView.ViewHolder {

        //2. construptor de la lista de datos llega lista de datos y se
        //asignan al elemento listDatos

        //3. referencia de los componentes de la lista de datos
        private ImageView imageView, imagenTipo, imagenPlay;
        private TextView nombre, descripcion;
        //vista
        public final View view;
        //instancia del objeto recuperado del archivo json
        public AppMedia objeto;

        public ViewHolder(View itemView) {
            super(itemView);
            //4. instancia a la referencia de los datos de la lista de datos
            view = itemView;
            imageView = itemView.findViewById(R.id.imagenDisco);
            imagenTipo = itemView.findViewById(R.id.imagenTipo);
            nombre = itemView.findViewById(R.id.textNombreArtista);
            descripcion = itemView.findViewById(R.id.textDescripVenta);
            imagenPlay = itemView.findViewById(R.id.imagenPlay);

        }
    }


    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //5. infla la vista con la referencia del xml lista intem que contiene la plantilla
        //de los datos que reflejaran los componentes del recycleView
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lineas, null, false);
        return new ViewHolder(view);
    }

    //7. establece la comunicación entre el adaptador y la clase viewHolder
    @Override
    public void onBindViewHolder(Adaptador.ViewHolder viewHolder, int i) {
        //llamamos al metodo asignardatos pasando el dato del intem que
        //toca de la lista
        //8. asigna los datos del intem que toca de la lista y lo asigna
        //al componente del xml listaItem
        viewHolder.objeto = listaDatos.get(i);
        viewHolder.imageView.setImageBitmap(listaDatos.get(i).getPhoto());
        switch (listaDatos.get(i).getTipo()) {
            case "0":
                viewHolder.imagenTipo.setImageBitmap(listaDatos.get(i).getTypeimg());
                break;
            case "1":
                viewHolder.imagenTipo.setImageBitmap(listaDatos.get(i).getTypeimg());
                break;
            case "2":
                viewHolder.imagenTipo.setImageBitmap(listaDatos.get(i).getTypeimg());
                break;
        }
        viewHolder.nombre.setText(listaDatos.get(i).getNombre());
        viewHolder.descripcion.setText(listaDatos.get(i).getDescripcion());

// metodo pare gestionar el item seleccionado
        viewHolder.imagenPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanaSelectorLo.reenviarDatos(viewHolder.objeto);

            }
        });
    }
   //6.retorna el tamaño de la lista de datos recibida en el construptor
    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

}
