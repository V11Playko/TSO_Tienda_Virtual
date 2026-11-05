package co.edu.poli.catalogo.carrito.infrastructure.web;

import co.edu.poli.catalogo.carrito.application.port.in.GestionarCarritoUseCase;
import co.edu.poli.catalogo.carrito.domain.Carrito;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ESQUELETO: los endpoints responden 501 hasta implementar CarritoService.
 * Mientras no exista sesión, el usuario se identifica con el encabezado X-Usuario-Id.
 * TODO: devolver un DTO con los items, la cantidad de prendas y el total a pagar.
 */
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final GestionarCarritoUseCase gestionarCarrito;

    public CarritoController(GestionarCarritoUseCase gestionarCarrito) {
        this.gestionarCarrito = gestionarCarrito;
    }

    @GetMapping
    public Carrito ver(@RequestHeader(value = "X-Usuario-Id", required = false) Long usuarioId) {
        return gestionarCarrito.verCarrito(usuarioId);
    }

    @PostMapping("/items")
    public Carrito agregar(@RequestHeader(value = "X-Usuario-Id", required = false) Long usuarioId,
                           @Valid @RequestBody ItemCarritoRequest req) {
        return gestionarCarrito.agregarItem(usuarioId, req.prendaId(), req.talla(), req.cantidad());
    }

    @PutMapping("/items/{itemId}")
    public Carrito actualizar(@RequestHeader(value = "X-Usuario-Id", required = false) Long usuarioId,
                              @PathVariable Long itemId,
                              @Valid @RequestBody CantidadRequest req) {
        return gestionarCarrito.actualizarCantidad(usuarioId, itemId, req.cantidad());
    }

    @DeleteMapping("/items/{itemId}")
    public Carrito eliminar(@RequestHeader(value = "X-Usuario-Id", required = false) Long usuarioId,
                            @PathVariable Long itemId) {
        return gestionarCarrito.eliminarItem(usuarioId, itemId);
    }
}
