package co.edu.poli.catalogo.comun.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SesionAutenticadaInterceptor implements HandlerInterceptor {
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        var session=request.getSession(false);
        Object id=session==null?null:session.getAttribute("usuarioId");
        if(!(id instanceof Long)) throw new co.edu.poli.catalogo.comun.AutenticacionException("Debe iniciar sesión");
        request.setAttribute("usuarioId",id);
        return true;
    }
}
