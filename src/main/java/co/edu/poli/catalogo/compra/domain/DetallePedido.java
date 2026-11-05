package co.edu.poli.catalogo.compra.domain;

import java.math.BigDecimal;

/** Prenda comprada dentro de un pedido, con el precio que tenía al momento de la compra. */
public record DetallePedido(Long prendaId, String talla, int cantidad, BigDecimal precioUnitario) {
}
