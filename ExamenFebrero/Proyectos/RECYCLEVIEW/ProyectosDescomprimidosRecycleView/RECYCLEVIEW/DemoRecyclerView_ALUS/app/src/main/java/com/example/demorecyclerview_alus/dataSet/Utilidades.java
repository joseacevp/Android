package com.example.demorecyclerview_alus.dataSet;

import java.util.Random;

public class Utilidades {
    /*
     * DECLARACION DEL DNI
     */
    public String declararDni(){

        Random aleato = new Random();//declaracion de objeto de la clase Random
        /*
         * este string lo usare para hacer un charAt
         * y sacar la letra verdadera de cada dni y asi poder compararla
         * con la letra introducida
         */
        String caracteresDni ="TRWAGMYFPDXBNJZSQVHLCKE";
        /*
         * para la declaracion del dni primero "int dni" saca el numero aleatorio sin letra
         * despues con string letra obtenemos la letra adecuada para el numero de dni
         * con un charat de la variable caracteresDni que declaramos al principio
         * dniCompleto es un string con la suma de dni y letra que es lo que devolvemos en el return
         */

        /*
         * la aleatoridad la empiezo en 1000000 para que ningun dni tenga menos de 7
         * caracteres ya que no existe un dni que empiece por dos o mas ceros
         */
        int dni = 1000000 + aleato.nextInt(99999999);
        int x = dni % 23;
        String letra = (caracteresDni.charAt((dni%23))) + "";
        String dniCompleto = dni + letra + "";
        /*
         * con este if controlo que los dni que solo tengan siete caracteres
         * sin contar la letra se les añada un cero al principio
         * dicho de otra manera controlo los que empiezan por cero
         */
        if (dniCompleto.length() < 9){
            dniCompleto = 0 + dniCompleto;
        }

        return dniCompleto;
    }
    /*
     * DECLARACION NOMBRE
     * Tenemos un array con los nombres, asignamos uno aleatoriamente a un string
     * y lo retornamos
     */
    public String declararNombre(){
        String [] nombres = {"Luis", "Pedro", "Ramón", "Francisco", "Ana", "Teresa", "María", "Juan", "Manuel",
            "Jeremías", "Zacarías", "Nicolás", "Noé", "Sonia", "Mónica", "Társilo", "Isabel", "Mariana", "Amparo", "Carmen"};
        String nombre = (nombres[(int)(Math.random()*nombres.length)]);
        return nombre;
    }
    /*
     * DECLARACION APELLIDOS
     * Tenemos un array con los nombre, asignamos uno aleatoriamente a un string
     * y lo retornamos
     */
    public String declararApellidos(){
        String [] apellidos = {"Duque","Izquierdo","Aroco","Ocaña","Martínez","Villanueva","Ortega","Escribano",
            "Zoido", "Morales","Cabañas", "Roche del Oro", "Rodenas", "Carbonell", "García", "Gómez", "Moreno", "Tejero"};
        String apellido = (apellidos[(int)(Math.random()*apellidos.length)]);
        return apellido;
    }
}
