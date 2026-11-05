package co.edu.poli.catalogo.prenda.domain;

import java.math.BigDecimal;

/**
 * Prenda del catálogo. Modelo de negocio puro (sin Spring ni JPA).
 * La talla se guarda como texto con el rango disponible, por ejemplo "S-XL" o "28-36".
 */
public record Prenda(
        Long id,
        String nombre,
        String categoria,
        String genero,
        String talla,
        String color,
        String material,
        String marca,
        BigDecimal precio,
        int stock,
        String imagenUrl,
        String descripcion) {
}
