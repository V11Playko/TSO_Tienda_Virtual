package co.edu.poli.catalogo.carrito.application.port.in;

import co.edu.poli.catalogo.carrito.domain.Carrito;

public interface GestionarCarritoUseCase {

    Carrito verCarrito(Long usuarioId);

    Carrito agregarItem(Long usuarioId, Long prendaId, String talla, int cantidad);

    Carrito actualizarCantidad(Long usuarioId, Long itemId, int cantidad);

    Carrito eliminarItem(Long usuarioId, Long itemId);
}
