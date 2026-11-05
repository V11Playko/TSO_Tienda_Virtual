package co.edu.poli.catalogo.prenda.application;

import co.edu.poli.catalogo.comun.FuncionalidadNoImplementadaException;
import co.edu.poli.catalogo.prenda.application.port.in.ConsultarPrendasUseCase;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ESQUELETO del módulo 4: artículos y características.
 * TODO (Isabel):
 *  - Implementar PrendaRepositoryPort con JPA (tablas prenda y categoria) e inyectarlo aquí.
 *  - obtenerPorId: si no existe, lanzar RecursoNoEncontradoException (responde 404).
 */
@Service
public class PrendaService implements ConsultarPrendasUseCase {

    private static final String FUNCIONALIDAD = "artículos y características";

    @Override
    public List<Prenda> listar() {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }

    @Override
    public Prenda obtenerPorId(Long id) {
        throw new FuncionalidadNoImplementadaException(FUNCIONALIDAD);
    }
}
