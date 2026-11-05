package co.edu.poli.catalogo.usuario.infrastructure.web;

import co.edu.poli.catalogo.usuario.domain.Usuario;

/** Datos públicos del usuario (nunca incluye el hash de la contraseña). */
public record UsuarioResponse(Long id, String nombre, String correo, String direccionEnvio) {

    public static UsuarioResponse desde(Usuario u) {
        return new UsuarioResponse(u.id(), u.nombre(), u.correo(), u.direccionEnvio());
    }
}
