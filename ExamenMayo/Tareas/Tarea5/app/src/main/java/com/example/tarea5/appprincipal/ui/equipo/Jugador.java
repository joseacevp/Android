package com.example.tarea5.appprincipal.ui.equipo;

public class Jugador {
    private String nombre,posicion;
    private int foto,favorito;
    Boolean isSelected = false;

    public Jugador() {
    }

    public Jugador(String nombre, String posicion, int foto, int favorito) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.foto = foto;
        this.favorito = favorito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
