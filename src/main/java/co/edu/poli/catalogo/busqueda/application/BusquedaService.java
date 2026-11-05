package co.edu.poli.catalogo.busqueda.application;

import co.edu.poli.catalogo.busqueda.application.port.in.BuscarPrendasUseCase;
import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ESQUELETO de los módulos 2 y 3: búsqueda básica y avanzada.
 * TODO búsqueda básica (Heinner): nombre obligatorio; buscar si el nombre "contiene" el texto, sin distinguir mayúsculas.
 * TODO búsqueda avanzada (Ronald): aplicar solo los filtros que lleguen con valor; validar que precioMin <= precioMax.
 * Ambos usan BusquedaPrendasPort, que se implementa en infrastructure/persistence.
 */
@Service
public class BusquedaService implements BuscarPrendasUseCase {

    @Override
    public List<Prenda> buscarPorNombre(String nombre) {
        throw new FuncionalidadNoImplementadaException("búsqueda básica");
    }

    @Override
    public List<Prenda> buscarAvanzada(FiltroBusqueda filtro) {
        throw new FuncionalidadNoImplementadaException("búsqueda avanzada");
    }
}
