package com.example.servicios.modelos;

public class CentroRecreacional {
    private int costoPublico, costoHabilitados;

    public CentroRecreacional(int costoPublico, int costoHabilitados) {
        this.costoPublico = costoPublico;
        this.costoHabilitados = costoHabilitados;
    }

    public int getCostoPublico() {
        return costoPublico;
    }

    public void setCostoPublico(int costoPublico) {
        this.costoPublico = costoPublico;
    }

    public int getCostoHabilitados() {
        return costoHabilitados;
    }

    public void setCostoHabilitados(int costoHabilitados) {
        this.costoHabilitados = costoHabilitados;
    }
}
