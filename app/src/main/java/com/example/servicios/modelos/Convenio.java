package com.example.servicios.modelos;

import java.io.Serializable;

public class Convenio implements Serializable {
    private int id;
    private String empresa;
    private String fecha;
    private String link;
    private String img;


    public Convenio(int id, String empresa, String fecha, String link, String img) {
        this.id = id;
        this.empresa = empresa;
        this.fecha = fecha;
        this.link = link;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
