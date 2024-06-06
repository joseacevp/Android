package com.example.tarea4v2;

import java.util.ArrayList;

public class Partida {
    String usuario;
    String heroe;
    String dificultad;
    String nuero_tabla;
    String fecha;
    String lista_fallos;


    public Partida(String usuario, String heroe, String dificultad, String nuero_tabla, String fecha, ArrayList<String> lista_fallos) {
        this.usuario = usuario;
        this.heroe = heroe;
        this.dificultad = dificultad;
        this.nuero_tabla = nuero_tabla;
        this.fecha = fecha;

    }

    public String getUsuario() {
        return usuario;
    }

    public String getLista_fallos() {
        return lista_fallos;
    }

    public void setLista_fallos(String lista_fallos) {
        this.lista_fallos = lista_fallos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHeroe() {
        return heroe;
    }

    public void setHeroe(String heroe) {
        this.heroe = heroe;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getNuero_tabla() {
        return nuero_tabla;
    }

    public void setNuero_tabla(String nuero_tabla) {
        this.nuero_tabla = nuero_tabla;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
