package com.example.entrenadormultiplicar.ui.entrenar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.entrenadormultiplicar.R;
import com.example.entrenadormultiplicar.databinding.FragmentEntrenarBinding;

import java.util.Random;

public class EntrenarFragment extends Fragment {

    private FragmentEntrenarBinding binding;
    private String fallos = "";
    private int i = 1;
    private int e = 10;
    private int indiceActualImagen = 0;
    private int indiceActualBarra = 1;
    int[] imagen;
    Random random = new Random();
    private int[] imagen_pokecuatro = {R.drawable.pokecuatro};
    private int[] imagen_pokecinco = {R.drawable.pokecinco};
    private int[] imagen_poketres = {R.drawable.poketres};
    private int[] imagen_pokedos = {R.drawable.pokedos};
    private int[] imagen_pokeuno = {R.drawable.pokeuno};

    private int primer, respuestaEsperada;
    private String tabla, dificultad, heroe, fecha, aleatorio, usuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EntrenarViewModel homeViewModel =
                new ViewModelProvider(this).get(EntrenarViewModel.class);

        binding = FragmentEntrenarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cargarPreferencias();
        iniciarTablaDificil(dificultad, tabla);
        binding.botonUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonUno.getText().toString());
                }
            }
        });
        binding.botonDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonDos.getText().toString());
                }
            }
        });
        binding.botonTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonTres.getText().toString());
                }
            }
        });
        binding.botonCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonCuatro.getText().toString());
                }
            }
        });
        binding.botonCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonCinco.getText().toString());
                }
            }
        });
        binding.botonSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonSeis.getText().toString());
                }
            }
        });
        binding.botonSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonSiete.getText().toString());
                }
            }
        });
        binding.botonOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonOcho.getText().toString());
                }
            }
        });
        binding.botonNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonNueve.getText().toString());
                }
            }
        });
        binding.botonDiez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Limita la longitud de la respuesta a dos dígitos
                if (respuestaString.length() < 2) {
                    binding.areaRespuestaUsuario.setText(respuestaString + binding.botonDiez.getText().toString());
                }
            }
        }); binding.botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuestaString = binding.areaRespuestaUsuario.getText().toString();
                // Verifica si hay al menos un dígito antes de intentar borrar
                if (!respuestaString.isEmpty()) {
                    binding.areaRespuestaUsuario.setText(respuestaString.substring(0, respuestaString.length() - 1));
                }
            }
        });
        binding.botonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.areaRespuestaUsuario.getText().toString().equals("")) {//si la respuesta del usuario no esta vacia
                    if (indiceActualBarra < 10) {//si no a respondido 10 veces
                        chekearRespuesta();
                        binding.areaRespuestaUsuario.setText("");
                        iniciarTablaDificil(dificultad, tabla);
                    } else {
                        chekearRespuesta();
                        binding.areaRespuestaUsuario.setText("");
                        //finalizada la tabla llama al fragmento estadisticas
                        //llamarFragmentoEsta();
                    }
                }
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void iniciarTablaDificil(String dificultad, String tabla) {
        if (tabla.equals("aleatorio")) {
            primer = Integer.parseInt(aleatorio);
        } else {
            try {
                primer = Integer.parseInt(tabla);
            } catch (Exception e) {
                Toast.makeText(getContext(), "No selecciono numero de tabla por defecto tabla del 1", Toast.LENGTH_SHORT).show();
                primer = 1;
            }
        }
        switch (dificultad) {
            case "Facil"://tabla de multiplicar en orden ascendente
//                primer = numeroTablaAleter(tabla);
                // Muestra la pregunta en el formato "número X número"
                binding.areaPregunta.setText(primer + " x " + i + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * i;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                binding.areaRespuestaCorrecta.setTag(respuestaEsperada);
                // Limpia el área de respuesta del usuario
                binding.areaRespuestaUsuario.setText("");
                i++;

                break;
            case "Normal"://tabla de multiplicar en orden descendente
//                primer = Integer.parseInt(tabla);
                // Muestra la pregunta en el formato "número X número"
                binding.areaPregunta.setText(primer + " x " + e + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * e;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                binding.areaRespuestaCorrecta.setTag(respuestaEsperada);
                // Limpia el área de respuesta del usuario
                binding.areaRespuestaUsuario.setText("");
                e--;
                break;
            case "Dificil"://tabla de multiplicar en orden aleatorio
                // Genera dos números aleatorios para la pregunta
                int numero1 = random.nextInt(10) + 1;
//                primer = Integer.parseInt(tabla);
                // Muestra la pregunta en el formato "número X número"
                binding.areaPregunta.setText(primer + " x " + numero1 + " = ");
                // Establece la respuesta esperada para la multiplicación de los dos números
                respuestaEsperada = primer * numero1;
                // Guarda la respuesta esperada para usarla en el método chekearRespuesta
                binding.areaRespuestaCorrecta.setTag(respuestaEsperada);
                binding.areaRespuestaUsuario.setText("");
                break;
        }
    }

    private void mostrarBarra() {
        ProgressBar bar = binding.progressBar;
        indiceActualBarra++;
        bar.setProgress(indiceActualBarra);

    }

    private void mostrarImagen(String heroe) {
        switch (heroe) {
            case "poke1":
                imagen = imagen_pokecuatro;
                break;
            case "poke2":
                imagen = imagen_poketres;
                break;
            case "poke3":
                imagen = imagen_pokedos;
                break;
            case "poke4":
                imagen = imagen_pokeuno;
                break;
        }


        if (indiceActualImagen < imagen.length - 1) {
            indiceActualImagen++;
        }
        binding.imageViewHeroe.setImageResource(imagen[indiceActualImagen]);
    }

    private void chekearRespuesta() {

        // Obtén la respuesta del usuario como una cadena
        String respuestaUsuarioStr = binding.areaRespuestaUsuario.getText().toString();
        // Obtiene la respuesta esperada del Tag de la vista respuesta
        int respuestaEsperada = (int) binding.areaRespuestaCorrecta.getTag();

        // Verifica si la respuesta del usuario no está vacía
        if (!respuestaUsuarioStr.isEmpty()) {
            // Convierte la respuesta del usuario a un número entero
            int respuestaUsuarioInt = Integer.parseInt(respuestaUsuarioStr);
            // Compara la respuesta del usuario con la respuesta esperada
            if (respuestaUsuarioInt == respuestaEsperada) {
                // La respuesta es correcta
                binding.areaRespuestaCorrecta.setText("");
                binding.areaRespuestaCorrecta2.setText("");
                mostrarImagen(heroe);

            } else {
                // La respuesta es incorrecta
                binding.areaRespuestaCorrecta2.setTextColor(Color.RED);
                binding.areaRespuestaCorrecta2.setText(binding.areaPregunta.getText() + binding.areaRespuestaUsuario.getText().toString());
//                fallos.add(pregunta.getText() + respuestaUsuario.getText().toString());
                binding.areaRespuestaCorrecta.setTextColor(Color.GREEN);
                binding.areaRespuestaCorrecta.setText(binding.areaPregunta.getText().toString() + respuestaEsperada);
                fallos += binding.areaRespuestaCorrecta2.getText().toString() + " \n";
                System.out.println(fallos);
//                fallos.add(respuestaIncorecta.getText().toString());
            }
            mostrarBarra();
        } else {
            // Agrega esta lógica para manejar el caso cuando la respuesta del usuario está vacía
            binding.areaRespuestaCorrecta2.setTextColor(Color.RED);
            binding.areaRespuestaCorrecta2.setText("Debes ingresar una respuesta");
        }
    }



    //    metodo que carga los datos previamente almacenados en un XML de preferencias
    private void cargarPreferencias() {
//        SharedPreferences preferencias = getActivity().getSharedPreferences
//                ("credenciales", Context.MODE_PRIVATE);
//
//        tabla = preferencias.getString("tabla", " 1");
//        dificultad = preferencias.getString("dificultad", "Por defecto Facil");
//        heroe = preferencias.getString("heroe", "Capitan America");
//        fecha = preferencias.getString("fecha", "Sin información");
//        aleatorio = preferencias.getString("aleatorio", "Sin información");
//        usuario = preferencias.getString("usuario", "sin informacion");
        tabla = "1";
        dificultad = "Normal";
        heroe = "poke1";
        fecha = "fecha";
        aleatorio = "";
        usuario = "";
    }
//    private void guardarEstadisticasPartida() {
//
//        //crea la base de datos
//        ConexionSqlLite conn = new ConexionSqlLite(getActivity(),
//                "BaseDatosTarea4", null, 1);
//        SQLiteDatabase db = conn.getWritableDatabase();
//        try {
//            String insert = "INSERT INTO " + Utilidades.TABLA_PARTIDAS
//                    + " ( "
//                    + Utilidades.USUARIO
//                    + ","
//                    + Utilidades.NUMERO_TABLA + ","
//                    + Utilidades.HEROE + ","
//                    + Utilidades.DIFICULTAD + ","
//                    + Utilidades.FALLOS + ","
//                    + Utilidades.FECHA
//                    + ")"
//                    + "VALUES ( '"+ usuario
//                    + "','"
//                    + tabla + "','"
//                    + heroe + "','"
//                    + dificultad + "','"
//                    + fallos + "','"
//                    + fecha
//                    + "')";
//
//            db.execSQL(insert);
//            db.close();
//        } catch (Exception e) {
//            Toast.makeText(getActivity(), "Fallo al registrar partida.", Toast.LENGTH_SHORT).show();
//
//        }
//    }
}