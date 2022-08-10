package com.example.servicios.modelos;

import java.io.Serializable;

public class Convocatoria implements Serializable {
    private int id;
    private String asunto;
    private String institucion;
    private String vigencia;
    private String img;
    private String link;

    public Convocatoria(int id, String asunto, String institucion, String vigencia, String img, String link) {
        this.id = id;
        this.asunto = asunto;
        this.institucion = institucion;
        this.vigencia = vigencia;
        this.img = img;
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
