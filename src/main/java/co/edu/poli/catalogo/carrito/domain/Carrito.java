package co.edu.poli.catalogo.carrito.domain;

import java.util.List;

/**
 * Carrito de un usuario.
 * TODO: calcular la cantidad de prendas y el precio total a pagar (módulo 6).
 */
public record Carrito(Long id, Long usuarioId, List<ItemCarrito> items) {
}
