package com.example.examen23;

import java.util.Random;

public class Utilidades {
    //constantes campos tabla CONTACTOS
    //CREATE TABLE IF NOT EXISTS miscumples(
    // ID integer,
    // TipoNotif char(1),
    // Mensaje VARCHAR(160),
    // Telefono VARCHAR(15),
    // FechaNacimiento VARCHAR(15),
    // Nombre VARCHAR(128)
    //);
    public static final String TABLA_MISCUMPLES = "miscumples";

    public static final String TIPONOTIF = "tiponotificacion";
    public static final String MENSAJE = "mensaje";
    public static final String TELEFONO = "telefono";
    public static final String FECHANACIMIENTO = "fechanacimiento";
    public static final String NOMBRE = "nombre";

    public static final String CREAR_TABLA_MISCUMPLES = "CREATE TABLE "
            + TABLA_MISCUMPLES
            + " ( "
            + TIPONOTIF
            + " TEXT,"
            + MENSAJE
            + "  TEXT,"
            + TELEFONO
            + " TEXT,"
            + FECHANACIMIENTO
            + " TEXT,"
            + NOMBRE
            + "  TEXT )";

}
