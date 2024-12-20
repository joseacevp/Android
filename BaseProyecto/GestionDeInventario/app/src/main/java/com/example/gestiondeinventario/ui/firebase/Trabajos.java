package com.example.gestiondeinventario.ui.firebase;

public class Trabajos {
    private String ordenTrabajo,nombreOperario,
            codigoMaterial,unidades;

    public Trabajos() {
    }

    public Trabajos(String ordenTrabajo, String nombreOperario, String codigoMaterial, String unidades) {
        this.ordenTrabajo = ordenTrabajo;
        this.nombreOperario = nombreOperario;
        this.codigoMaterial = codigoMaterial;
        this.unidades = unidades;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }
}
