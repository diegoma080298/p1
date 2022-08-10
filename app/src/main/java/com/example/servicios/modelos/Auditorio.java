package com.example.servicios.modelos;

public class Auditorio {
    private int costo;
    private int costoLimp;
    private int costoSonido;
    private int costoMulti;
    private String incluye;
    private String capacidad;

    public Auditorio(int costo, int costoLimp, int costoSonido, int costoMulti, String incluye, String capacidad) {
        this.costo = costo;
        this.costoLimp = costoLimp;
        this.costoSonido = costoSonido;
        this.costoMulti = costoMulti;
        this.incluye = incluye;
        this.capacidad = capacidad;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCostoLimp() {
        return costoLimp;
    }

    public void setCostoLimp(int costoLimp) {
        this.costoLimp = costoLimp;
    }

    public int getCostoSonido() {
        return costoSonido;
    }

    public void setCostoSonido(int costoSonido) {
        this.costoSonido = costoSonido;
    }

    public int getCostoMulti() {
        return costoMulti;
    }

    public void setCostoMulti(int costoMulti) {
        this.costoMulti = costoMulti;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}
