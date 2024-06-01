package com.example.examenmayo24.ui.gallery;

public class Jugador {
    private String nombre,desempe;
    private int foto,favorito;
    Boolean isSelected = false;

    public Jugador() {
    }

    public Jugador(String nombre, String desempe, int foto, int favorito) {
        this.nombre = nombre;
        this.desempe = desempe;
        this.foto = foto;
        this.favorito = favorito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesempe() {
        return desempe;
    }

    public void setDesempe(String posicion) {
        this.desempe = posicion;
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
