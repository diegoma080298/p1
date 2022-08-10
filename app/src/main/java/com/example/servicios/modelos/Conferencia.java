package com.example.servicios.modelos;

import java.io.Serializable;

public class Conferencia implements Serializable {
    private int id;
    private  String name;
    private  String fecha;
    private  String link;
    private  String url;
    private  String idReu;
    private  String odAcceso;

    public Conferencia(int id, String name, String fecha, String link, String url, String idReu, String odAcceso) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.link = link;
        this.url = url;
        this.idReu = idReu;
        this.odAcceso = odAcceso;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdReu() {
        return idReu;
    }

    public void setIdReu(String idReu) {
        this.idReu = idReu;
    }

    public String getOdAcceso() {
        return odAcceso;
    }

    public void setOdAcceso(String odAcceso) {
        this.odAcceso = odAcceso;
    }
}
