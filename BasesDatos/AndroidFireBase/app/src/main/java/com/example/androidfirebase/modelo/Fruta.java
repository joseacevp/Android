package com.example.androidfirebase.modelo;

import androidx.annotation.Nullable;

public class Fruta {
    private String id;
    private String nombre;

    private double precio;

    public Fruta() {
    }

    public Fruta(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        //metodo para evitar que se repitan id para evitar repeticion de frutas el la lista
        //evita que se repitan id
        return id.equals(((Fruta) obj).id);
    }
}
