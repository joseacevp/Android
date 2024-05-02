package com.example.accesosqlbasedatos;

public class Objeto {
    private String nombreArtista,tituloAlbum;

    public Objeto() {
    }

    public Objeto(String nombreArtista, String tituloAlbum) {
        this.nombreArtista = nombreArtista;
        this.tituloAlbum = tituloAlbum;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getTituloAlbum() {
        return tituloAlbum;
    }

    public void setTituloAlbum(String tituloAlbum) {
        this.tituloAlbum = tituloAlbum;
    }
}
