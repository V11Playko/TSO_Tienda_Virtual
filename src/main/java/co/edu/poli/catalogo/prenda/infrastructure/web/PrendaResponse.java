package co.edu.poli.catalogo.prenda.infrastructure.web;

import co.edu.poli.catalogo.prenda.domain.Prenda;

import java.math.BigDecimal;

/** DTO de salida de una prenda. También lo usa el módulo de búsqueda. */
public record PrendaResponse(
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

    public static PrendaResponse desde(Prenda p) {
        return new PrendaResponse(p.id(), p.nombre(), p.categoria(), p.genero(), p.talla(), p.color(),
                p.material(), p.marca(), p.precio(), p.stock(), p.imagenUrl(), p.descripcion());
    }
}
