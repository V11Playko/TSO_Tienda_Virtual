package co.edu.poli.catalogo.usuario.application.port.in;

import co.edu.poli.catalogo.usuario.domain.Usuario;

public interface IniciarSesionUseCase {

    /** Valida correo y contraseña y devuelve el usuario autenticado. */
    Usuario iniciarSesion(String correo, String contrasena);
}
