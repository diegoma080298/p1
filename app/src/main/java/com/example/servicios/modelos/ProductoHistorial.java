package com.example.servicios.modelos;

public class ProductoHistorial {
    private String nombre;
    private String monto;
    private String fechaPago;
    private String fueRecogido;

    public ProductoHistorial(String nombre , String monto, String fechaPago, String fueRecogido) {
        this.nombre = nombre;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.fueRecogido = fueRecogido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFueRecogido() {
        return fueRecogido;
    }

    public void setFueRecogido(String fueRecogido) {
        this.fueRecogido = fueRecogido;
    }
}
