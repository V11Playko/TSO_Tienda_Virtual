package co.edu.poli.catalogo.busqueda.application.port.in;

import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.prenda.domain.Prenda;

import java.util.List;

/** Casos de uso de búsqueda de prendas. */
public interface BuscarPrendasUseCase {

    /** Búsqueda básica: el nombre contiene el texto, sin distinguir mayúsculas. */
    List<Prenda> buscarPorNombre(String nombre);

    /** Búsqueda avanzada con filtros opcionales. */
    List<Prenda> buscarAvanzada(FiltroBusqueda filtro);
}
