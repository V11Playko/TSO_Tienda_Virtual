package co.edu.poli.catalogo.prenda.application.port.in;

import co.edu.poli.catalogo.prenda.domain.Prenda;

import java.util.List;

/** Casos de uso de consulta del catálogo de prendas. */
public interface ConsultarPrendasUseCase {

    List<Prenda> listar();

    /** Lanza RecursoNoEncontradoException si la prenda no existe. */
    Prenda obtenerPorId(Long id);
}
