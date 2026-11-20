package co.edu.poli.catalogo.carrito.application.port.out;

import co.edu.poli.catalogo.carrito.domain.Carrito;

public interface CarritoRepositoryPort {
    Carrito obtener(Long usuarioId);
    Carrito agregar(Long usuarioId, Long prendaId, String talla, int cantidad);
    Carrito actualizar(Long usuarioId, Long itemId, int cantidad);
    Carrito eliminar(Long usuarioId, Long itemId);
}
