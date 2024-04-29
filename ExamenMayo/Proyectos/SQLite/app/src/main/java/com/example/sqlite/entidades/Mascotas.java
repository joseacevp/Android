package com.example.sqlite.entidades;

import java.io.Serializable;

public class Mascotas implements Serializable {
    private Integer idDuenio,idMascota;
    private String nombreMascato,raza;

    public Mascotas() {
    }

    public Mascotas(Integer idDueño, Integer idMascota, String nombreMascato, String raza) {
        this.idDuenio = idDueño;
        this.idMascota = idMascota;
        this.nombreMascato = nombreMascato;
        this.raza = raza;
    }

    public Integer getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(Integer idDuenio) {
        this.idDuenio = idDuenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascato() {
        return nombreMascato;
    }

    public void setNombreMascato(String nombreMascato) {
        this.nombreMascato = nombreMascato;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
