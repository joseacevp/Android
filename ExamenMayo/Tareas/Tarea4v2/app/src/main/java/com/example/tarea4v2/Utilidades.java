package com.example.tarea4v2;

public class Utilidades {
    //constantes campos tablas usuarios
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";

    //almacena constantes repersentando los campos y las tablas de la base de datos
    //para evitar repetir los mismos parametros muchas veces
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "
            + TABLA_USUARIOS
            + " ( "
            + NOMBRE
            + " TEXT,"
            + PASSWORD
            + "  TEXT)";

    //constantes campos tabla partidas
    public static final String TABLA_PARTIDAS = "partidas";
    public static final String USUARIO = "usuario";
    public static final String HEROE = "heroe";
    public static final String DIFICULTAD = "dificultad";
    public static final String NUMERO_TABLA = "numero_tabla";
    public static final String FECHA = "fecha";
    public static final String FALLOS = "fallos";
    //    String usuario,heroe,dificultad,nuero_tabla;

    public static final String CREAR_TABLA_PARTIDAS = "CREATE TABLE "
            + TABLA_PARTIDAS
            + " ( "
            + USUARIO
            + " TEXT,"
            + HEROE
            + "  TEXT,"
            + DIFICULTAD
            + "  TEXT,"
            + FECHA
            + "  TEXT,"
            + FALLOS
            + "  TEXT,"
            + NUMERO_TABLA
            + "  TEXT )";

}
