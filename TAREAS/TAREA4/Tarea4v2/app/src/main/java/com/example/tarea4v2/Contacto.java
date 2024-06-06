package com.example.tarea4v2;

import android.graphics.Bitmap;

public class Contacto {
    private String nombre;
    private String email;
    private Bitmap fotoPerfil;

    public Contacto(String nombre, String email, Bitmap fotoPerfil) {
        this.nombre = nombre;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Bitmap getFotoPerfil() {
        return fotoPerfil;
    }
}
