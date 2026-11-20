package co.edu.poli.catalogo.usuario.application;

import co.edu.poli.catalogo.comun.RecursoExistenteException;
import co.edu.poli.catalogo.comun.AutenticacionException;
import co.edu.poli.catalogo.usuario.application.port.in.IniciarSesionUseCase;
import co.edu.poli.catalogo.usuario.application.port.in.RegistrarUsuarioUseCase;
import co.edu.poli.catalogo.usuario.application.port.out.UsuarioRepositoryPort;
import co.edu.poli.catalogo.usuario.domain.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Locale;

@Service
public class UsuarioService implements RegistrarUsuarioUseCase, IniciarSesionUseCase {
    private final UsuarioRepositoryPort usuarios;
    private final PasswordEncoder encoder;
    public UsuarioService(UsuarioRepositoryPort usuarios, PasswordEncoder encoder) { this.usuarios=usuarios; this.encoder=encoder; }
    @Override @Transactional
    public Usuario registrar(String nombre, String correo, String contrasena, String direccionEnvio) {
        String email=correo.trim().toLowerCase(Locale.ROOT);
        if (usuarios.existePorCorreo(email)) throw new RecursoExistenteException("Ya existe una cuenta con ese correo");
        return usuarios.guardar(nombre.trim(), email, encoder.encode(contrasena), direccionEnvio.trim());
    }
    @Override @Transactional(readOnly=true)
    public Usuario iniciarSesion(String correo, String contrasena) {
        Usuario usuario=usuarios.buscarPorCorreo(correo.trim().toLowerCase(Locale.ROOT))
                .orElseThrow(()->new AutenticacionException("Correo o contraseña incorrectos"));
        if (!encoder.matches(contrasena, usuario.contrasenaHash())) throw new AutenticacionException("Correo o contraseña incorrectos");
        return usuario;
    }
}
