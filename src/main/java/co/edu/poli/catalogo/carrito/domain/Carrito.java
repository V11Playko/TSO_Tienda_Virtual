package co.edu.poli.catalogo.carrito.domain;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Carrito de un usuario. */
public record Carrito(Long id, Long usuarioId, List<ItemCarrito> items) {
    public Carrito { items = List.copyOf(items); }
    @JsonProperty("cantidadTotal") public int cantidadTotal() { return items.stream().mapToInt(ItemCarrito::cantidad).sum(); }
    @JsonProperty("total") public BigDecimal total() { return items.stream().map(i -> i.precioUnitario().multiply(BigDecimal.valueOf(i.cantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add); }
}
