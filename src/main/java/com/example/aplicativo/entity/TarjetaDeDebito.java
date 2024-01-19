package com.example.aplicativo.entity;

import com.example.aplicativo.MetodoPago;
import com.example.aplicativo.Usuario;
import com.example.aplicativo.entity.Entity;

@Entity
public class TarjetaDeDebito extends MetodoPago {

    private String numeroTarjeta;
    private String nombreTitular;
    private String fechaExpiracion;
    private int codigoSeguridad;

    // Constructor por defecto necesario para JPA
    public TarjetaDeDebito() {
        // Constructor por defecto necesario para JPA
    }

    // Constructor con parámetros
    public TarjetaDeDebito(String numeroTarjeta, String nombreTitular, String fechaExpiracion, int codigoSeguridad, Usuario usuario) {
        super("Tarjeta de Débito", usuario);
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public String getFechaExpiracion() {
        return fechaExpiracion;
    }
    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }
    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
