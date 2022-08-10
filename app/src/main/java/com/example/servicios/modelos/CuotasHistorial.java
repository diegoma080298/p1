package com.example.servicios.modelos;

public class CuotasHistorial {
    private String mes;
    private String periodo;
    private String fechaPago;
    private String monto;

    public CuotasHistorial(String mes, String periodo, String fechaPago, String monto) {
        this.mes = mes;
        this.periodo = periodo;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
