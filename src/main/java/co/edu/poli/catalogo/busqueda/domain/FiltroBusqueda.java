package co.edu.poli.catalogo.busqueda.domain;

import java.math.BigDecimal;

/**
 * Filtros de la búsqueda avanzada. Todos son opcionales: un valor nulo o vacío no filtra.
 */
public record FiltroBusqueda(
        String categoria,
        String genero,
        String talla,
        String color,
        String marca,
        BigDecimal precioMin,
        BigDecimal precioMax) {
}
