package co.edu.poli.catalogo.usuario.application.port.out;

import co.edu.poli.catalogo.usuario.domain.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    boolean existePorCorreo(String correo);
    Optional<Usuario> buscarPorCorreo(String correo);
    Optional<Usuario> buscarPorId(Long id);
    Usuario guardar(String nombre, String correo, String hash, String direccion);
}
