package com.example.aplicativo;
public class ComprobanteTransferencia {
    private String mensaje;
    private String correoElectronico;
    public ComprobanteTransferencia() {
    }
    public ComprobanteTransferencia(String mensaje, String correoElectronico) {
        this.mensaje = mensaje;
        this.correoElectronico = correoElectronico;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
