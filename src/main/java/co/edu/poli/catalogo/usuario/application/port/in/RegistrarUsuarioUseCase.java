package co.edu.poli.catalogo.usuario.application.port.in;

import co.edu.poli.catalogo.usuario.domain.Usuario;

public interface RegistrarUsuarioUseCase {

    /** Registra un cliente nuevo. El correo debe ser único. */
    Usuario registrar(String nombre, String correo, String contrasena, String direccionEnvio);
}
