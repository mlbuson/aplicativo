package com.example.aplicativo;
import com.example.aplicativo.entity.TarjetaDeCredito;
import com.example.aplicativo.entity.TarjetaDeDebito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Iterable<Usuario> findByNombre(@Param("nombre") String nombre);
    @Query("SELECT u FROM Usuario u WHERE u.apellido = :apellido")
    Iterable<Usuario> findByApellido(@Param("apellido") String apellido);
    @Query("SELECT u FROM Usuario u WHERE u.direccion = :direccion")
    Iterable<Usuario> findByDireccion(@Param("direccion") String direccion);
    @Query("SELECT u FROM Usuario u WHERE u.documentoIdentidad = :documentoIdentidad")
    Iterable<Usuario> findByDocumentoIdentidad(@Param("documentoIdentidad") String documentoIdentidad);
    @Query("SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    Iterable<Usuario> findByTelefono(@Param("telefono") String telefono);
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Iterable<Usuario> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM Usuario u WHERE u.tarjetaDeDebito = :tarjetaDeDebito")
    Iterable<Usuario> findByTarjetaDeDebito(@Param("tarjetaDeDebito") TarjetaDeDebito tarjetaDeDebito);
    @Query("SELECT u FROM Usuario u WHERE u.tarjetaDeCredito = :tarjetaDeCredito")
    Iterable<Usuario> findByTarjetaDeCredito(@Param("tarjetaDeCredito") TarjetaDeCredito tarjetaDeCredito);
    @Query("SELECT u FROM Usuario u WHERE u.mercadoPago = :mercadoPago")
    Iterable<Usuario> findByMercadoPago(@Param("mercadoPago") boolean mercadoPago);
    @Query("SELECT u FROM Usuario u WHERE u.pagoFacil = :pagoFacil")
    Iterable<Usuario> findByPagoFacil(@Param("pagoFacil") boolean pagoFacil);
    @Query("SELECT u FROM Usuario u WHERE u.rapipago = :rapipago")
    Iterable<Usuario> findByRapipago(@Param("rapipago") boolean rapipago);
    @Query("SELECT u FROM Usuario u WHERE u.transferenciaBancaria = :transferenciaBancaria")
    Iterable<Usuario> findByTransferenciaBancaria(@Param("transferenciaBancaria") boolean transferenciaBancaria);
    @Query("SELECT u FROM Usuario u WHERE u.codigoQr = :codigoQr")
    Iterable<Usuario> findByCodigoQr(@Param("codigoQr") CodigoQr codigoQr);
}
