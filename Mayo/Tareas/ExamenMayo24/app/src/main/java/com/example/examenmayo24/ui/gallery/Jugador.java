package com.example.examenmayo24.ui.gallery;

public class Jugador {
    private String nombre,desempe,fecha;
    private int foto,favorito;
    Boolean isSelected = false;

    public Jugador() {
    }

    public Jugador(String nombre, String desempe, String fecha, int foto, int favorito) {
        this.nombre = nombre;
        this.desempe = desempe;
        this.fecha = fecha;
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

    public void setDesempe(String desempe) {
        this.desempe = desempe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
