package com.example.reproductormultimedia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppMedia {

    private String nombre;
    private String descripcion;
    private String tipo;
    private Bitmap typeimg;
    private String uri;

    private Bitmap photo;

    public AppMedia(String nombre, String descripcion, String tipo, Bitmap typeimg, String uri, Bitmap photo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.typeimg = typeimg;
        this.uri = uri;
        this.photo = photo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bitmap getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(Bitmap typeimg) {
        this.typeimg = typeimg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
