package co.edu.poli.catalogo.carrito.domain;

import java.math.BigDecimal;

/** Línea del carrito: una prenda en una talla con su cantidad. */
public record ItemCarrito(Long id, Long prendaId, String nombrePrenda, String talla, int cantidad,
                          BigDecimal precioUnitario) {
}
