package com.example.aplicativo;
import com.example.aplicativo.entity.Entity;

import javax.persistence.*;
@Entity
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    // Otras propiedades relacionadas con el método de pago
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // no me deja crear getter

    public MetodoPago() {
        // Constructor por defecto necesario para JPA
    }

    public MetodoPago(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
