package com.pet502.mascotas502gt;

import org.json.*;
import java.util.*;

public class Puntos {

    private String descripcionLugar;
    private String id;
    private String imagenLugar;
    private String latitud;
    private String longitud;
    private String nombreLugar;

    public Puntos() {

    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagenLugar() {
        return imagenLugar;
    }

    public void setImagenLugar(String imagenLugar) {
        this.imagenLugar = imagenLugar;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

/**
 * Instantiate the instance using the passed jsonObject to set the properties values
 */
    public Puntos(JSONObject jsonObject){
        if(jsonObject == null){
            return;
        }
        id= jsonObject.optString("idMascota");
        nombreLugar = jsonObject.optString("nombre");
        descripcionLugar = jsonObject.optString("direccion");
        imagenLugar=jsonObject.optString("imagen");
        latitud = jsonObject.optString("lat");
        longitud = jsonObject.optString("lon");

    }
/**
 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
 */

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("idMascota",id);
            jsonObject.put("mombre", nombreLugar);
            jsonObject.put("descripcionLugar", descripcionLugar);
            jsonObject.put("imagen",imagenLugar);
            jsonObject.put("lat", latitud);
            jsonObject.put("lon", longitud);


        }catch (JSONException e){
            e.printStackTrace();
        }
        return  jsonObject;
    }



}
