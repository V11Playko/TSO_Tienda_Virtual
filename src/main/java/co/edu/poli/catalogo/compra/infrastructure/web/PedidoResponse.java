package co.edu.poli.catalogo.compra.infrastructure.web;

import co.edu.poli.catalogo.compra.domain.Pedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, Long usuarioId, LocalDateTime fecha, BigDecimal total, String estado,
                             List<DetalleResponse> detalles) {
    public PedidoResponse { detalles=List.copyOf(detalles); }
    public static PedidoResponse desde(Pedido p) {
        return new PedidoResponse(p.id(),p.usuarioId(),p.fecha(),p.total(),p.estado(),p.detalles().stream()
                .map(d->new DetalleResponse(d.prendaId(),d.talla(),d.cantidad(),d.precioUnitario())).toList());
    }
    public record DetalleResponse(Long prendaId, String talla, int cantidad, BigDecimal precioUnitario) {}
}
