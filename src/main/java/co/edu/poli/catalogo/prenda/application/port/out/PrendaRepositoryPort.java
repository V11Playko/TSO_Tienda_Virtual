package co.edu.poli.catalogo.prenda.application.port.out;

import co.edu.poli.catalogo.busqueda.domain.FiltroBusqueda;
import co.edu.poli.catalogo.prenda.domain.Prenda;
import java.util.List;
import java.util.Optional;

public interface PrendaRepositoryPort {
    List<Prenda> listar();
    Optional<Prenda> buscarPorId(Long id);
    List<Prenda> buscarPorNombre(String nombre);
    List<Prenda> buscar(FiltroBusqueda filtro);
}
