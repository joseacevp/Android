package com.example.androidfirebase.modelo;

import java.util.ArrayList;
import java.util.List;

public class FrutaService {
    public static List<Fruta> frutas = new ArrayList<>();

    public static void agregarFruta(Fruta fruta) {
        frutas.add(fruta);
    }
    

}
