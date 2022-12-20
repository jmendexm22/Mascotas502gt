package com.pet502.mascotas502gt;

public class M_encontradas {

    public M_encontradas(int idMEncontradas, String genero, String sexo, String direccion, String contacto, String img, String usuario_idUsuario) {
        this.idMEncontradas = idMEncontradas;
        this.genero = genero;
        this.sexo = sexo;
        this.direccion = direccion;
        this.contacto = contacto;
         this.img = img;
        Usuario_idUsuario = usuario_idUsuario;
    }

    public int getIdMEncontradas() {
        return idMEncontradas;
    }

    public void setIdMEncontradas(int idMEncontradas) {
        this.idMEncontradas = idMEncontradas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    private int idMEncontradas;
    private String genero;
    private String sexo;
    private String direccion;
    private String contacto;
    private String img;
    private String Usuario_idUsuario;







}
