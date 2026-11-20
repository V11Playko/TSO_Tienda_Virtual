package co.edu.poli.catalogo.prenda.infrastructure.persistence;

import co.edu.poli.catalogo.prenda.domain.Prenda;

public final class PrendaMapper {
    private PrendaMapper() {}
    public static Prenda desde(PrendaEntity e) {
        return new Prenda(e.id, e.nombre, e.categoria.nombre, e.genero, e.talla, e.color, e.material,
                e.marca, e.precio, e.stock, e.imagenUrl, e.descripcion);
    }
}
