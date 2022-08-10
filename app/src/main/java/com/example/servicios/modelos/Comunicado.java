package com.example.servicios.modelos;

import java.io.Serializable;

public class Comunicado implements Serializable {
    private int id;
    private String asunto;
    private String fecha;
    private String link;

    public Comunicado(int id, String asunto, String fecha, String link) {
        this.id = id;
        this.asunto = asunto;
        this.fecha = fecha;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
