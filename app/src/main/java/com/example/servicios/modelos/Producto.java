package com.example.servicios.modelos;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private int costo;
    private int stock;
    private String img;


    public Producto(int id, String nombre, int costo, int stock, String img) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.stock = stock;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
