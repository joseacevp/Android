package com.example.tarea5.appprincipal.ui.equipo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea5.R;
import com.example.tarea5.appprincipal.AppPrincipal;
import com.example.tarea5.appprincipal.ui.convocados.ConvocadosViewModel;
import com.example.tarea5.databinding.FragmentEquipoBinding;

import java.util.ArrayList;
import java.util.List;

public class EquipoFragment extends Fragment {
    private EquipoViewModel equipoViewModel;
    private ArrayList<Jugador> lista;
    private ConvocadosViewModel convocadosViewModel;
    private List<Jugador> judadoresSeleccionados;
    AdaptadorRecyclerMultipl adaptadorRecyclerMultipl;
    RecyclerView recyclerView;
    private FragmentEquipoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEquipoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inicializar el ViewModel compartido
        equipoViewModel = new ViewModelProvider(requireActivity()).get(EquipoViewModel.class);

        // Observar cambios en el ViewModel compartido

        equipoViewModel.getJugadoresSeleccionados().observe(getViewLifecycleOwner(), new Observer<List<Jugador>>() {
            @Override
            public void onChanged(List<Jugador> jugadores) {
                // Actualizar el RecyclerView con la lista de jugadores seleccionados
                adaptadorRecyclerMultipl.setListaJugadores(jugadores);
                adaptadorRecyclerMultipl.notifyDataSetChanged();
            }
        });
        lista = new ArrayList<>();

        construirRecycleView();
        llenarPersonajes();


        return view;
    }

    private void construirRecycleView() {

        recyclerView = binding.reciclerJugadores;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        iniciarRecyclerJugadorMulti();
    }

    private void iniciarRecyclerJugadorMulti() {
        adaptadorRecyclerMultipl = new AdaptadorRecyclerMultipl(lista);
        recyclerView.setAdapter(adaptadorRecyclerMultipl);

    }

    private void llenarPersonajes() {

        lista.add(new Jugador("Enrrique Similo", "Delantero Centro", R.drawable.carita01, R.drawable.imagen_estrella));
        lista.add(new Jugador("Juan Bueno", "Delantero Centro", R.drawable.carita02, R.drawable.imagen_estrella));
        lista.add(new Jugador("Lucas Disorio", "Delantero Centro", R.drawable.carita03, R.drawable.imagen_estrella));
        lista.add(new Jugador("Ernesto Zifuentes", "Delantero Derecho", R.drawable.carita04, R.drawable.imagen_estrella));
        lista.add(new Jugador("Lolo Anoeta", "Delantero Derecho", R.drawable.carita05, R.drawable.imagen_estrella));
        lista.add(new Jugador("Lucas Jacinto", "Delantero Derecho", R.drawable.carita06, R.drawable.imagen_estrella));
        lista.add(new Jugador("Luis Paulino", "Delantero Izquierdo", R.drawable.carita07, R.drawable.imagen_estrella));
        lista.add(new Jugador("Gregorio Antuano", "Delantero Izquierdo", R.drawable.carita08, R.drawable.imagen_estrella));
        lista.add(new Jugador("Daviz Salinas", "Central", R.drawable.carita09, R.drawable.imagen_estrella));
        lista.add(new Jugador("Eustaquio Loindiuo", "Portero", R.drawable.carita10, R.drawable.imagen_estrella));
        lista.add(new Jugador("Felipa Garcia", "Central Derecho", R.drawable.carita12, R.drawable.imagen_estrella));
        lista.add(new Jugador("Noel Sal", "Cetral Derechio", R.drawable.carita13, R.drawable.imagen_estrella));
        lista.add(new Jugador("Laureano Andujar", "Central Izquierdo", R.drawable.carita14, R.drawable.imagen_estrella));
        lista.add(new Jugador("Santiago Robledano", "Cetral Izquierdo", R.drawable.carita15, R.drawable.imagen_estrella));
        lista.add(new Jugador("Raul Luisiana", "Defensa Cetral", R.drawable.carita11, R.drawable.imagen_estrella));
        lista.add(new Jugador("Tote Litunia", "Defensa Derecha", R.drawable.carita16, R.drawable.imagen_estrella));
        lista.add(new Jugador("Fran Frances", "Defensa Derecha", R.drawable.carita17, R.drawable.imagen_estrella));
        lista.add(new Jugador("Koke Ponpidu", "Defensa Izquierda", R.drawable.carita18, R.drawable.imagen_estrella));
        lista.add(new Jugador("Rick Whilson", "Defensa Izquierda", R.drawable.carita19, R.drawable.imagen_estrella));
        lista.add(new Jugador("Chipian Lilione", "Protero", R.drawable.carita20, R.drawable.imagen_estrella));
    }

    @Override
    public void onPause() {
        super.onPause();
        grabarListaSeleccionados();
        // Guardar el estado de los jugadores seleccionados en el ViewModel
        equipoViewModel.setJugadoresSeleccionados(adaptadorRecyclerMultipl.getListaJugadores());
    }

    private void grabarListaSeleccionados() {
        //envia la lista de jugadores seleccionados al cambiar de fragmento.
        judadoresSeleccionados = adaptadorRecyclerMultipl.getListaJugadoresSeleccionados();
        convocadosViewModel = new ViewModelProvider(requireActivity()).get(ConvocadosViewModel.class);
        convocadosViewModel.setListaConvocados((ArrayList<Jugador>) judadoresSeleccionados);
        //para visualizar datos
        StringBuilder nombresSeleccionados = new StringBuilder();
        for (int i = 0; i < judadoresSeleccionados.size(); i++) {
            if (i == 0) {
                nombresSeleccionados.append(judadoresSeleccionados.get(i).getNombre());

            } else {
                nombresSeleccionados.append("\n").append(judadoresSeleccionados.get(i).getNombre());
            }
        }
//        Toast.makeText(getContext(), "Jugadores Seleccionados:\n" + nombresSeleccionados.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}