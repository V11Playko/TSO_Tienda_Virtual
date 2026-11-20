package co.edu.poli.catalogo.carrito.infrastructure.web;

import co.edu.poli.catalogo.carrito.domain.Carrito;
import java.math.BigDecimal;
import java.util.List;

public record CarritoResponse(Long id, Long usuarioId, List<ItemResponse> items, int cantidadTotal, BigDecimal total) {
    public CarritoResponse { items=List.copyOf(items); }
    public static CarritoResponse desde(Carrito c) {
        return new CarritoResponse(c.id(),c.usuarioId(),c.items().stream()
                .map(i->new ItemResponse(i.id(),i.prendaId(),i.nombrePrenda(),i.talla(),i.cantidad(),i.precioUnitario())).toList(),
                c.cantidadTotal(),c.total());
    }
    public record ItemResponse(Long id, Long prendaId, String nombre, String talla, int cantidad, BigDecimal precioUnitario) {}
}
