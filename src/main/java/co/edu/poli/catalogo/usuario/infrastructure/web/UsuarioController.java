package co.edu.poli.catalogo.usuario.infrastructure.web;

import co.edu.poli.catalogo.usuario.application.port.in.IniciarSesionUseCase;
import co.edu.poli.catalogo.usuario.application.port.in.RegistrarUsuarioUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** ESQUELETO: los endpoints responden 501 hasta implementar UsuarioService. */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final RegistrarUsuarioUseCase registrarUsuario;
    private final IniciarSesionUseCase iniciarSesion;

    public UsuarioController(RegistrarUsuarioUseCase registrarUsuario, IniciarSesionUseCase iniciarSesion) {
        this.registrarUsuario = registrarUsuario;
        this.iniciarSesion = iniciarSesion;
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse registrar(@Valid @RequestBody RegistroRequest req) {
        return UsuarioResponse.desde(
                registrarUsuario.registrar(req.nombre(), req.correo(), req.contrasena(), req.direccionEnvio()));
    }

    // TODO: decidir qué devuelve el login (por ahora los datos del usuario; más adelante sesión o token)
    @PostMapping("/login")
    public UsuarioResponse login(@Valid @RequestBody LoginRequest req) {
        return UsuarioResponse.desde(iniciarSesion.iniciarSesion(req.correo(), req.contrasena()));
    }
}
