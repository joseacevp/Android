package com.example.sqlite.utilidades;

public class Utilidades {
    //constante campos tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID_PROPIETARIO = "id";
    public static final String CAMPO_NOMBRE_PROPIETARIO = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";

    //Scrip con los comandos SQL para crear la tabla
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +
            TABLA_USUARIO + " (" +
            CAMPO_ID_PROPIETARIO + " INTEGER, " +
            CAMPO_NOMBRE_PROPIETARIO + " TEXT, " +
            CAMPO_TELEFONO + " TEXT)";

    public static final String TABLA_MASCOTA = "mascota";
    public static final String CAMPO_ID_MASCOTA = "id";
    public static final String CAMPO_MOMBRE_MASCOTA = "nombreMascota";
    public static final String CAMPO_RAZA = "raza";
    public static final String CAMPO_ID_DUENIO = "idDuenio";

    public static final String CREAR_TABLA_MASCOTA = "CREATE TABLE " +
            TABLA_MASCOTA + "(" +
            CAMPO_ID_MASCOTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_MOMBRE_MASCOTA + " TEXT, " +
            CAMPO_RAZA + " TEXT," +
            CAMPO_ID_DUENIO + " INTEGER)";


}
