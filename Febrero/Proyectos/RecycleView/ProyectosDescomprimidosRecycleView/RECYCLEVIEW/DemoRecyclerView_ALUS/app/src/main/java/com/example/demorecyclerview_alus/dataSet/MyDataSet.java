package com.example.demorecyclerview_alus.dataSet;

import java.util.ArrayList;

public class MyDataSet {
    public static final ArrayList<Contacto> ITEMS = new ArrayList<Contacto>();

    public MyDataSet(int cantidad){
        Utilidades navajaSuiza = new Utilidades();
        Contacto contacto;
        for (int i=0 ; i<cantidad ; i++){
            contacto = new Contacto();
            contacto.setId("" + (i+1));
            contacto.setNombre(navajaSuiza.declararNombre());
            contacto.setApellidos(navajaSuiza.declararApellidos());
            contacto.setDni(navajaSuiza.declararDni());

            ITEMS.add(contacto);
        }
    }
}
