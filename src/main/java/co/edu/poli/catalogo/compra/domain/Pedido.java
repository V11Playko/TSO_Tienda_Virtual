package co.edu.poli.catalogo.compra.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/** Pedido generado al comprar. Estado inicial: CREADO. */
public record Pedido(Long id, Long usuarioId, LocalDateTime fecha, BigDecimal total, String estado,
                     List<DetallePedido> detalles) {
}
