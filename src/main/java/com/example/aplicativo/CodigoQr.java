package com.example.aplicativo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
public class CodigoQR {
    private String contenido;
    private int ancho;
    private int alto;
    private String rutaImagen;

    public CodigoQR() {
        // Constructor por defecto
    }

    public CodigoQR(String contenido, int ancho, int alto, String rutaImagen) {
        this.contenido = contenido;
        this.ancho = ancho;
        this.alto = alto;
        this.rutaImagen = rutaImagen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    // TODO: Método para generar un código QR y guardarlo como imagen
    public void generarCodigoQR() {
        try {
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.MARGIN, 1);

            BitMatrix matrix = new MultiFormatWriter().encode(contenido, com.google.zxing.BarcodeFormat.QR_CODE, ancho, alto, hintMap);

            BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < ancho; y++) {
                for (int x = 0; x < alto; x++) {
                    image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            File outputFile = new File(rutaImagen);
            ImageIO.write(image, "png", outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TODO: Método para leer un código QR desde una imagen
    public String leerCodigoQR() {
        try {
            BufferedImage image = ImageIO.read(new File(rutaImagen));

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Map<DecodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result result = new MultiFormatReader().decode(binaryBitmap, hintMap);
            return result.getText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al leer el código QR";
        }
    }
}