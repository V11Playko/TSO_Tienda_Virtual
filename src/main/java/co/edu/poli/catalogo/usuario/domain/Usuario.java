package co.edu.poli.catalogo.usuario.domain;

/**
 * Cliente registrado. Para comprar se debe estar registrado.
 * La contraseña solo se guarda como hash BCrypt.
 */
public record Usuario(Long id, String nombre, String correo, String contrasenaHash, String direccionEnvio) {
}
