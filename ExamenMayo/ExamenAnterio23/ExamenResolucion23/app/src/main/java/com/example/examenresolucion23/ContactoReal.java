package com.example.examenresolucion23;

import android.net.Uri;

public class ContactoReal {
    private String nombre, telefono, fecha, fotoUri;
//    private Uri fotoUri;

    public ContactoReal() {
    }

    public ContactoReal(String nombre, String telefono, String fecha, String fotoUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha = fecha;
        this.fotoUri = fotoUri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }
}