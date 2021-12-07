/*
 * Created by Nicanor Orlando.
 * Copyright (c) 7/12/21 09:27.
 */

package example.com.contactos;

public class Contacto {
    private String nombre;
    private String telefono;
    private String email;
    private int foto;

    //  ctrl + N ---> "Constructor" :
    public Contacto(int foto, String nombre, String telefono, String email) {
        this.foto = foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    //  ctrl + N ---> "Getter and Setter" :
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
