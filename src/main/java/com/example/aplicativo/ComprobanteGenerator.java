package com.example.aplicativo;
public class ComprobanteGenerator {
    public static ComprobanteTransferencia generarComprobante(String mensaje, String correoElectronico) {
        // Lógica para generar el comprobante de la transferencia
        return new ComprobanteTransferencia(mensaje, correoElectronico);
    }
}
