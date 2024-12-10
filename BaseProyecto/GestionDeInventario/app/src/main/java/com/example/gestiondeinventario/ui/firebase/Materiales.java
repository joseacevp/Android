package com.example.gestiondeinventario.ui.firebase;

public class Materiales {
    private String nombre, codigo, localizacion, uso, fotoUri;
    private Boolean isSelected = false;

    public Materiales() {
    }

    public Materiales(String nombre, String codigo, String localizacion, String uso, String fotoUri, Boolean isSelected) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.localizacion = localizacion;
        this.uso = uso;
        this.fotoUri = fotoUri;
        this.isSelected = isSelected;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}