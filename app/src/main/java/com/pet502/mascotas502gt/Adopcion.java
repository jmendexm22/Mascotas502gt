package com.pet502.mascotas502gt;

public class Adopcion {

    private int idMAdopcion;
    private String raza;
    private String sexo;
    private String edad;
    private String contacto;
    private String img;
    private String Usuario_idUsuario;

    public Adopcion(int idMAdopcion, String raza, String sexo, String edad, String contacto, String img, String usuario_idUsuario) {
        this.idMAdopcion = idMAdopcion;
        this.raza = raza;
        this.sexo = sexo;
        this.edad = edad;
        this.contacto = contacto;
        this.img = img;
        Usuario_idUsuario = usuario_idUsuario;
    }

    public int getIdMAdopcion() {
        return idMAdopcion;
    }

    public void setIdMAdopcion(int idMAdopcion) {
        this.idMAdopcion = idMAdopcion;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(String usuario_idUsuario) {
        Usuario_idUsuario = usuario_idUsuario;
    }
}
