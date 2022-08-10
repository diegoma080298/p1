package com.example.servicios.modelos;

import java.io.Serializable;

public class Cuotas implements Serializable {
    private String mes;
    private int año;
    private String cuota;

    public Cuotas(String mes, int año, String cuota) {
        this.mes = mes;
        this.año = año;
        this.cuota = cuota;
    }


    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
