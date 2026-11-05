package co.edu.poli.catalogo.usuario.application;

import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import co.edu.poli.catalogo.usuario.application.port.in.IniciarSesionUseCase;
import co.edu.poli.catalogo.usuario.application.port.in.RegistrarUsuarioUseCase;
import co.edu.poli.catalogo.usuario.domain.Usuario;
import org.springframework.stereotype.Service;

/**
 * ESQUELETO del módulo de registro e inicio de sesión.
 * TODO (Ronald):
 *  - Implementar UsuarioRepositoryPort con JPA en infrastructure/persistence e inyectarlo aquí.
 *  - Registrar: validar correo único y guardar la contraseña con BCryptPasswordEncoder.
 *  - Login: buscar por correo y comparar con passwordEncoder.matches(...).
 */
@Service
public class UsuarioService implements RegistrarUsuarioUseCase, IniciarSesionUseCase {

    @Override
    public Usuario registrar(String nombre, String correo, String contrasena, String direccionEnvio) {
        throw new FuncionalidadNoImplementadaException("registro de usuarios");
    }

    @Override
    public Usuario iniciarSesion(String correo, String contrasena) {
        throw new FuncionalidadNoImplementadaException("inicio de sesión");
    }
}
