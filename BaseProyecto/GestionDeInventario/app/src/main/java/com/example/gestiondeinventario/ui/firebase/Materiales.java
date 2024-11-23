package com.example.gestiondeinventario.ui.firebase;

public class Materiales {
    private String nombre,codigo,localizacion,uso;
    private int foto;
    private Boolean isSelected = false;

    public Materiales(String nombre, String codigo, String localizacion, String uso, int foto, Boolean isSelected) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.localizacion = localizacion;
        this.uso = uso;
        this.foto = foto;
        this.isSelected = isSelected;
    }

    public Materiales() {
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
