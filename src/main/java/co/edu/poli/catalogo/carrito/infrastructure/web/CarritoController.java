package co.edu.poli.catalogo.carrito.infrastructure.web;

import co.edu.poli.catalogo.carrito.application.port.in.GestionarCarritoUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Operaciones del carrito asociado a la sesión autenticada. */
@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final GestionarCarritoUseCase gestionarCarrito;

    public CarritoController(GestionarCarritoUseCase gestionarCarrito) {
        this.gestionarCarrito = gestionarCarrito;
    }

    @GetMapping
    public CarritoResponse ver(@RequestAttribute("usuarioId") Long usuarioId) {
        return CarritoResponse.desde(gestionarCarrito.verCarrito(usuarioId));
    }

    @PostMapping("/items")
    public CarritoResponse agregar(@RequestAttribute("usuarioId") Long usuarioId,
                           @Valid @RequestBody ItemCarritoRequest req) {
        return CarritoResponse.desde(gestionarCarrito.agregarItem(usuarioId, req.prendaId(), req.talla(), req.cantidad()));
    }

    @PutMapping("/items/{itemId}")
    public CarritoResponse actualizar(@RequestAttribute("usuarioId") Long usuarioId,
                              @PathVariable Long itemId,
                              @Valid @RequestBody CantidadRequest req) {
        return CarritoResponse.desde(gestionarCarrito.actualizarCantidad(usuarioId, itemId, req.cantidad()));
    }

    @DeleteMapping("/items/{itemId}")
    public CarritoResponse eliminar(@RequestAttribute("usuarioId") Long usuarioId,
                            @PathVariable Long itemId) {
        return CarritoResponse.desde(gestionarCarrito.eliminarItem(usuarioId, itemId));
    }
}
