package com.example.reproductormultimedia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LeerJson {

    public static List<AppMedia> ITEMS = new ArrayList<>();

    public static void clearLista() {
        ITEMS.clear();
    }

    public static void loadListaDesdeJSON(Context c) {
        String json = null;
        try {
            //leectura de archivo
            InputStream is =
                    c.getAssets().open("listaCanciones.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            //inicia la lectura con la raid del documento
            JSONObject jsonObject = new JSONObject(json);
            JSONArray couchList = jsonObject.getJSONArray("multimedia_list");
            //lee todos los objetos que hay en la raid del documeto
            for (int i = 0; i < couchList.length(); i++) {
                JSONObject jsonCouch = couchList.getJSONObject(i);
                String name = jsonCouch.getString("nombre");
                String category = jsonCouch.getString("descripcion");
                String tipo = jsonCouch.getString("tipo");
                String URI = jsonCouch.getString("uri");
                Bitmap photo = null;
                //relacciona la imagen de la carpeta imagenes con el nombre de la imagen
                //en el documento Json
                try {
                    photo = BitmapFactory.decodeStream(
                            c.getAssets().open("images/" +
                                    jsonCouch.getString("image")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap fotoTipo = null;
                try {
                    fotoTipo = BitmapFactory.decodeStream(
                            c.getAssets().open("images/" +
                                    jsonCouch.getString("tipo")+".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  public AppMedia(String nombre, String descripcion, String tipo, Bitmap typeimg, String uri, Bitmap photo) {
                ITEMS.add(new AppMedia(name, category, tipo, fotoTipo, URI, photo));
//String nombre, String descripcion, String tipo, String URI, String imagen, Bitmap photo, Bitmap typeimg
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}
