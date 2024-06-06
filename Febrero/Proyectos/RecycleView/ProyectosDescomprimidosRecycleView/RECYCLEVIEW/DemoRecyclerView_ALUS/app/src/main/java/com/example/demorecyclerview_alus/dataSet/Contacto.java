package com.example.demorecyclerview_alus.dataSet;

public class Contacto {
    private String id;
    private String nombre;
    private String apellidos;
    private String dni;

    public Contacto() {
    }

    public Contacto(String id, String nombre, String apellidos, String dni, String domicilio, String email, String fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Contacto{nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + '}';
    }
}
