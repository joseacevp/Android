package com.example.examen23;

import android.net.Uri;

import java.net.URI;

public class Contacto {
    private String nombre, fecha, notificacion, foto, telefono, tipo;
    private Uri uri;

    public Contacto() {
    }

    public Contacto(String nombre, String fecha, String notificacion, String foto, String telefono, String tipo, Uri uri) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.notificacion = notificacion;
        this.foto = foto;
        this.telefono = telefono;
        this.tipo = tipo;
        this.uri = uri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}

