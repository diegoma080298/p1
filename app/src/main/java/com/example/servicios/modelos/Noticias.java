package com.example.servicios.modelos;

import java.io.Serializable;

public class Noticias implements Serializable {
    private int id;
    private  String name;
    private  String descripcion;
    private  String imagef;

    public Noticias(int id, String name, String descripcion, String imagef) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.imagef = imagef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagef() {
        return imagef;
    }

    public void setImagef(String imagef) {
        this.imagef = imagef;
    }
}
