package co.edu.poli.catalogo.usuario.infrastructure.persistence;

import co.edu.poli.catalogo.usuario.domain.Usuario;

public final class UsuarioMapper {
    private UsuarioMapper() {}
    public static Usuario desde(UsuarioEntity e) { return new Usuario(e.id, e.nombre, e.correo, e.contrasenaHash, e.direccionEnvio); }
}
