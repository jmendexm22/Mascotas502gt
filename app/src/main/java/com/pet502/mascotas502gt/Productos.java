package com.pet502.mascotas502gt;

public class Productos {


    public Productos(int idMascotas, String nombre, String direccionPerdida, String contacto,String imagen, String latitud, String longitud) {
        this.idMascotas = idMascotas;
        this.nombre = nombre;
        this.direccionPerdida = direccionPerdida;
        this.contacto = contacto;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdMascotas() {
        return idMascotas;
    }

    public void setIdMascotas(int idMascotas) {
        this.idMascotas = idMascotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionPerdida() {
        return direccionPerdida;
    }

    public void setDireccionPerdida(String direccionPerdida) {
        this.direccionPerdida = direccionPerdida;
    }

    public String getContacto() { return contacto; }

    public void setContacto(String contacto) { this.contacto = contacto; }



    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    private int idMascotas;
    private String nombre;
    private String direccionPerdida;
    private String contacto;
    private String latitud;
    private String longitud;
    private String imagen;
    private String especie;
    private String genero;




}
