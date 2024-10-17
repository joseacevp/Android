package com.example.entrenadormultiplicar.gestionentrenador;

public class Entrenador {
    private int foto;
    private String nombre;

    public Entrenador() {
    }

    public Entrenador(int foto, String nombre) {
        this.foto = foto;
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
