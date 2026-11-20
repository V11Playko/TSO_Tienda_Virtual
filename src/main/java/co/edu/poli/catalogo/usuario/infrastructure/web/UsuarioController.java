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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/** Registro, inicio y cierre de sesión. */
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

    @PostMapping("/login")
    public UsuarioResponse login(@Valid @RequestBody LoginRequest req, HttpServletRequest request) {
        var usuario=iniciarSesion.iniciarSesion(req.correo(), req.contrasena());
        HttpSession sesion=request.getSession(true);
        request.changeSessionId();
        sesion.setAttribute("usuarioId", usuario.id());
        return UsuarioResponse.desde(usuario);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        if(session!=null) session.invalidate();
    }
}
