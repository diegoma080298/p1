package com.example.servicios.modelos;

public class AlquilerHistorial {
    private String nombreAmbiente;
    private String fechaPago;
    private String fechaAlq;
    private String horaInicio;
    private String monto;
    private String horas;

    public AlquilerHistorial(String nombreAmbiente, String fechaPago, String fechaAlq,String horaInicio, String monto, String horas) {
        this.nombreAmbiente = nombreAmbiente;
        this.fechaPago = fechaPago;
        this.fechaAlq = fechaAlq;
        this.horaInicio=horaInicio;
        this.monto = monto;
        this.horas = horas;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getNombreAmbiente() {
        return nombreAmbiente;
    }

    public void setNombreAmbiente(String nombreAmbiente) {
        this.nombreAmbiente = nombreAmbiente;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaAlq() {
        return fechaAlq;
    }

    public void setFechaAlq(String fechaAlq) {
        this.fechaAlq = fechaAlq;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}
