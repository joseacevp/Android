package com.example.juegomatematicas.ui;

public class Jugadores {
    private String nombre;
    private int foto;
    private Boolean isSelected = false;

    public Jugadores() {
    }

    public Jugadores(String nombre, int foto, Boolean isSelected) {
        this.nombre = nombre;
        this.foto = foto;
        this.isSelected = isSelected;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
