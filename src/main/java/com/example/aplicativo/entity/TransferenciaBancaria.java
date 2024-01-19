package com.example.aplicativo.entity;
import com.example.aplicativo.ComprobanteGenerator;
import com.example.aplicativo.ComprobanteTransferencia;
import com.example.aplicativo.MetodoPago;
import com.example.aplicativo.Usuario;
import jakarta.websocket.Session;
import org.apache.logging.log4j.message.Message;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;

public class TransferenciaBancaria extends MetodoPago {
    private String nombreBanco;
    private String numeroCuenta;
    private static final String CORREO_ENVIO = "tucorreoelectronico@gmail.com";
    private static final String CONTRASENA_CORREO = "tucontrasenia";
    private static final String API_KEY_SMS = "tuApiKey"; // Tengo que hacer el cambio con la clave de API real
    private static final String SMS_API_URL = "https://api.serviciomensajesms.com/enviar";
    public TransferenciaBancaria() {
    }
    public TransferenciaBancaria(String nombreBanco, String numeroCuenta, Usuario usuario) {
        super("Transferencia Bancaria", usuario);
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public ComprobanteTransferencia realizarTransferencia() {
        enviarCorreoElectronico();
        enviarMensajeTexto();
        return generarComprobante();
    }

    private void enviarCorreoElectronico() {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CORREO_ENVIO, CONTRASENA_CORREO);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getUsuario().getEmail()));
            mensaje.setSubject("Confirmación de Transferencia");
            mensaje.setText("Se ha realizado una transferencia con éxito. Detalles: ...");

            Transport.send(mensaje);

            System.out.println("Correo electrónico enviado con éxito a " + usuario.getEmail());

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void enviarMensajeTexto() {
        // Lógica para enviar mensaje de texto utilizando una API de mensajería SMS
        String numeroTelefono = "1132967779";
        String mensaje = "Se ha realizado una transferencia con éxito. Detalles: ...";

        try {
            // Construir la URL para la API de mensajería SMS
            String urlCompleta = SMS_API_URL + "?api_key=" + API_KEY_SMS + "&numero=" + numeroTelefono + "&mensaje=" + mensaje;

            // Realizar la solicitud HTTP
            URL url = new URL(urlCompleta);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            StringBuilder respuesta = new StringBuilder();

            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            // Imprimir la respuesta
            System.out.println("Respuesta de la API de SMS: " + respuesta.toString());

        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje de texto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private ComprobanteTransferencia generarComprobante() {
        // Lógica para generar el comprobante utilizando la clase ComprobanteGenerator
        return ComprobanteGenerator.generarComprobante("Transferencia realizada con éxito", usuario.getEmail());
    }
}
